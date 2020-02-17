package com.bekir.mvpbaseproject.screens.mvpmodule.home;


import com.bekir.mvpbaseproject.base.BasePresenter;
import com.bekir.mvpbaseproject.model.response.bizclub.EventListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView mMvpView) {
        super(mMvpView);
    }


    public void fetchEvent(){
        getBaseView().startLoading();
        restClient.getEventList().enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {
                getBaseView().endLoading();
                if(response.isSuccessful()){
                    getBaseView().bindEventListResponse(response.body());
                }else{
                    getBaseView().errorPopup(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<EventListResponse> call, Throwable t) {
                getBaseView().endLoading();
                getBaseView().errorPopup(t.getLocalizedMessage());
            }
        });
    }





}
