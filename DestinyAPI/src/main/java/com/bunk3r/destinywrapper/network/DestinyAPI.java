package com.bunk3r.destinywrapper.network;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.Constants;
import com.bunk3r.destinywrapper.enums.ErrorCode;
import com.bunk3r.destinywrapper.models.Guardian;
import com.bunk3r.destinywrapper.models.Item;
import com.bunk3r.destinywrapper.models.User;
import com.bunk3r.destinywrapper.network.endpoints.InventoryEndpoint;
import com.bunk3r.destinywrapper.network.endpoints.UserEndpoint;
import com.bunk3r.destinywrapper.network.requests.EquipItemRequest;
import com.bunk3r.destinywrapper.network.requests.EquipItemsRequest;
import com.bunk3r.destinywrapper.network.requests.TransferItemRequest;
import com.bunk3r.destinywrapper.network.responses.AccountResponse;
import com.bunk3r.destinywrapper.network.responses.AccountSummaryResponse;
import com.bunk3r.destinywrapper.network.responses.EmptyResponse;
import com.bunk3r.destinywrapper.network.responses.EquipItemResponse;
import com.bunk3r.destinywrapper.network.responses.MembershipsResponse;
import com.bunk3r.destinywrapper.network.responses.TransferItemResponse;
import com.bunk3r.destinywrapper.utils.CookiesUtils;
import com.bunk3r.destinywrapper.utils.LocalizationUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DestinyAPI {
    private static final String BASE_URL = "https://www.bungie.net";
    private static final String HEADER_API_KEY_NAME = "x-api-key";
    private static final String HEADER_CROSS_REFERENCE_NAME = "x-csrf";
    private static final String HEADER_COOKIES_NAME = "Cookie";
    private static final String QUERY_PARAM_LANGUAGE = "lc";
    private static final Gson GSON = new Gson();

    private static DestinyAPI mInstance;

    private final Context mContext;
    private final String mDestinyApiKey;

    private DestinyAPI(@NonNull Context context, @NonNull String apiKey) {
        mContext = context;
        mDestinyApiKey = apiKey;
    }

    public static void initServer(@NonNull Context context, @NonNull String apiKey) {
        mInstance = new DestinyAPI(context, apiKey);
    }

    public static
    @NonNull
    DestinyAPI getInstance() {
        return mInstance;
    }

    private Retrofit getRestAdapter(final User user) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors()
                .add(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request newRequest;

                        String cookies = user.getCookies();
                        newRequest = request.newBuilder()
                                .url(request.httpUrl()
                                        .newBuilder()
                                        .addQueryParameter(QUERY_PARAM_LANGUAGE, LocalizationUtils.getLocale())
                                        .build())
                                .addHeader(HEADER_API_KEY_NAME, mDestinyApiKey)
                                .addHeader(HEADER_CROSS_REFERENCE_NAME, CookiesUtils.getCrossReferenceToken(cookies))
                                .addHeader(HEADER_COOKIES_NAME, cookies)
                                .build();


                        Response response;
                        int retries = 0;
                        boolean executeAgain = false;
                        do {
                            response = chain.proceed(newRequest);
                            if (response.isSuccessful()) {
                                executeAgain = false;
                                MediaType contentType = response.body().contentType();
                                String bodyString = response.body().string();
                                EmptyResponse result = GSON.fromJson(bodyString, EmptyResponse.class);

                                if (result.getErrorCode() == ErrorCode.NEED_LOGIN) {
                                    Intent loginRequired = new Intent();
                                    loginRequired.setAction(Constants.ACTION_NEEDS_LOGIN);
                                    loginRequired.putExtra(Constants.ARG_IN_PLATFORM, user.getPlatformId());
                                    mContext.sendBroadcast(loginRequired);
                                } else if (result.getErrorCode() == ErrorCode.WAIT_MORE) {
                                    try {
                                        int throttleSeconds = result.getThrottleSeconds();
                                        wait(TimeUnit.SECONDS.toMillis(throttleSeconds));
                                    } catch (Exception e) {
                                        break;
                                    }

                                    switch (retries) {
                                        case 1:
                                            executeAgain = false;
                                            break;
                                        case 0:
                                            retries = Constants.NETWORK_CALL_RETRIES;
                                        default:
                                            retries--;
                                            executeAgain = true;
                                    }
                                }

                                if (!executeAgain) {
                                    ResponseBody body = ResponseBody.create(contentType, bodyString);
                                    Response.Builder builder = response.newBuilder().body(body);

                                    if (result.getErrorCode() != ErrorCode.SUCCESS) {
                                        builder.message(result.getMessage())
                                                .code(result.getErrorCode());
                                    }

                                    response = builder.build();
                                }
                            }
                        } while (executeAgain);

                        return response;
                    }
                });
        okHttpClient.setReadTimeout(Constants.CONNECTION_TIMEOUT_IN_MINUTES, TimeUnit.MINUTES);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public void getAccountInformation(User user, Callback<AccountResponse> callback) {
        UserEndpoint userRest = getRestAdapter(user).create(UserEndpoint.class);
        userRest.getAccountInfo().enqueue(callback);
    }

    public void getMembershipId(User user, Callback<MembershipsResponse> callback) {
        UserEndpoint userRest = getRestAdapter(user).create(UserEndpoint.class);
        userRest.getMemberships().enqueue(callback);
    }

    public void getAccountSummary(final User user, Callback<AccountSummaryResponse> callback) {
        InventoryEndpoint userRest = getRestAdapter(user).create(InventoryEndpoint.class);
        userRest.getAccountSummary(user.getPlatformId(), user.getMembershipId()).enqueue(callback);
    }

    public void equipItemToGuardian(User user, Guardian guardian, Item item, Callback<EquipItemResponse> callback) {
        InventoryEndpoint inventoryRest = getRestAdapter(user).create(InventoryEndpoint.class);
        EquipItemRequest requestBody = new EquipItemRequest(user.getPlatformId(), guardian, item);
        inventoryRest.equipItem(requestBody).enqueue(callback);
    }

    public void equipItemsToGuardian(User user, Guardian guardian, List<Item> items, Callback<EquipItemResponse> callback) {
        InventoryEndpoint inventoryRest = getRestAdapter(user).create(InventoryEndpoint.class);
        EquipItemsRequest requestBody = new EquipItemsRequest(user.getPlatformId(), guardian, items);
        inventoryRest.equipItems(requestBody).enqueue(callback);
    }

    public void moveItemToVault(User user, Guardian guardian, Item item, int amount, Callback<TransferItemResponse> callback) {
        InventoryEndpoint inventoryRest = getRestAdapter(user).create(InventoryEndpoint.class);
        inventoryRest.transferItem(TransferItemRequest.toVault(user.getPlatformId(), guardian, item, amount))
                .enqueue(callback);
    }

    public void takeItemFromVault(User user, Guardian guardian, Item item, int amount, Callback<TransferItemResponse> callback) {
        InventoryEndpoint inventoryRest = getRestAdapter(user).create(InventoryEndpoint.class);
        inventoryRest.transferItem(TransferItemRequest.fromVault(user.getPlatformId(), guardian, item, amount))
                .enqueue(callback);
    }
}