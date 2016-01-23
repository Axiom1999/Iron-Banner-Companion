package com.bunk3r.destinywrapper.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Parcelable> T[] toArrayOf(Class<T> clazz, Parcelable[] parcelables) {
        if (parcelables == null || parcelables.length > 0) {
            return null;
        }

        T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, parcelables.length);
        // noinspection SuspiciousSystemArraycopy
        System.arraycopy(parcelables, 0, result, 0, parcelables.length);

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Parcelable> T[] readArray(Class<T> clazz, Parcel in) {
        if (in == null) {
            return null;
        }

        Parcelable[] parcelables = in.readParcelableArray(clazz.getClassLoader());
        if (parcelables == null) {
            return null;
        }

        T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, parcelables.length);
        // noinspection SuspiciousSystemArraycopy
        System.arraycopy(parcelables, 0, result, 0, parcelables.length);

        return result;
    }

    public static <T extends Parcelable> void write2dArray(T[][] array, Parcel dest, int flags) {
        if (dest == null || array == null) {
            return;
        }

        dest.writeInt(array.length);
        for (T[] innerArray : array) {
            dest.writeParcelableArray(innerArray, flags);
        }
    }

    public static <T extends Parcelable> T[][] read2dArray(Class<T> clazz, Parcel in) {
        if (in == null) {
            return null;
        }

        final int size = in.readInt();
        Parcelable[][] tmp = new Parcelable[size][];
        int[] sizes = new int[size];
        for (int idx = 0; idx < size; idx++) {
            tmp[idx] = in.readParcelableArray(clazz.getClassLoader());
            sizes[idx] = tmp[idx] != null ? tmp[idx].length : 0;
        }

        // noinspection unchecked
        T[][] result = (T[][]) java.lang.reflect.Array.newInstance(clazz, sizes);
        for (int idx = 0; idx < size; idx++) {
            // noinspection SuspiciousSystemArraycopy
            System.arraycopy(tmp[idx], 0,  result[idx], 0, sizes[idx]);
        }

        return result;
    }
}
