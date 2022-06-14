package com.example.phonetracker.network;

import com.example.phonetracker.models.LatestPhoneSearch;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;




public interface AzharimApi {
    @GET("latest")
    Call<LatestPhoneSearch> getphonetracker();
}
