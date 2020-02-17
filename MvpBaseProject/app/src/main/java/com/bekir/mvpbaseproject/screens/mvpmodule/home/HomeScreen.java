package com.bekir.mvpbaseproject.screens.mvpmodule.home;


import com.bekir.mvpbaseproject.R;
import com.bekir.mvpbaseproject.base.BaseScreen;
import com.bekir.mvpbaseproject.model.response.bizclub.EventListResponse;

public class HomeScreen  extends BaseScreen<HomePresenter> implements HomeView {

    @Override
    public int getLayout() {
        return R.layout.screen_mvp_module_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public HomePresenter setPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void getUIData() {
        presenter.fetchEvent();
    }

    @Override
    public void bindEventListResponse(EventListResponse result) {

    }

    @Override
    public void errorFlag() {

    }


}
