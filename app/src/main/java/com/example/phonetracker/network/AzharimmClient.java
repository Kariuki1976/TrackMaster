package com.example.phonetracker.network;


import com.example.phonetracker.BuildConfig;
import com.example.phonetracker.network.AzharimApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
//import okhttp3.Response;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AzharimmClient {
    private static final Object AZHARIMM_BASE_URL = "https://api-mobilespecs.azharimm.site/v2/";
    private static final String AZHARIMM_API_KEY = BuildConfig.AZHARIMM_API_KEY;
    private static Retrofit retrofit = null;
    public static AzharimApi getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", AZHARIMM_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl((String) AZHARIMM_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(AzharimApi.class);
    }
}
