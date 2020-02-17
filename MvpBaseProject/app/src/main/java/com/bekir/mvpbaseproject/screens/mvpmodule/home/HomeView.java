package com.bekir.mvpbaseproject.screens.mvpmodule.home;


import com.bekir.mvpbaseproject.base.BaseView;
import com.bekir.mvpbaseproject.model.response.bizclub.EventListResponse;

public interface HomeView extends BaseView {

    void getUIData();

    void bindEventListResponse(EventListResponse result);

    void errorFlag();

}
