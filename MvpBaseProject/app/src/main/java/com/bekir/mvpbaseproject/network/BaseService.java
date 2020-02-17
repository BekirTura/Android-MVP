package com.bekir.mvpbaseproject.network;



import com.bekir.mvpbaseproject.model.response.bizclub.EventListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseService {

    @GET("/api.json")
    Call<EventListResponse> getEventList();
}
