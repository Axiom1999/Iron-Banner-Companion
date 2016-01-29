package me.axiom.aapp.ironbannercompanion.api;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

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

import me.axiom.aapp.ironbannercompanion.api.responses.AccountSummaryResponse;
import me.axiom.aapp.ironbannercompanion.api.responses.CharacterProgressionResponse;
import me.axiom.aapp.ironbannercompanion.api.responses.MembershipIdResponse;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DestinyAPI {

    private static final String BASE_URL = "https://www.bungie.net";
    private static final String HEADER_API_KEY_NAME = "x-api-key";

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

    public static @NonNull DestinyAPI getInstance() {
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

                        newRequest = request.newBuilder()
                                .url(request.httpUrl()
                                    .newBuilder()
                                    .build())
                                .addHeader(HEADER_API_KEY_NAME, mDestinyApiKey)
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
                                me.axiom.aapp.ironbannercompanion.api.responses.Response result = GSON.fromJson(bodyString, me.axiom.aapp.ironbannercompanion.api.responses.Response.class);

                                if (result.getErrorCode() == ErrorCode.WAIT_MORE) {
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
                                            retries = 2;
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
        okHttpClient.setReadTimeout(2, TimeUnit.MINUTES);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public void getAccountInformation(User user, Callback<AccountSummaryResponse> callback) {
        Endpoints userRest = getRestAdapter(user).create(Endpoints.class);
        userRest.getAccountInfo(user.getPlatformId(), user.getMembershipId()).enqueue(callback);
    }

    public void getMembershipId(User user, Callback<MembershipIdResponse> callback) {
        Endpoints userRest = getRestAdapter(user).create(Endpoints.class);
        userRest.getMembershipId(user.getPlatformId(), user.getGamerTag()).enqueue(callback);
    }

    public void getCharacterProgression(User user, String characterId, Callback<CharacterProgressionResponse> callback) {
        Endpoints userRest = getRestAdapter(user).create(Endpoints.class);
        userRest.getCharacterProgression(user.getPlatformId(), user.getMembershipId(), characterId).enqueue(callback);
    }

}
