package com.example.bottomnavigate.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 泡泡 on 2017/12/5.
 */

public class HttpUrl {
    public static void sendOkhttp(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
