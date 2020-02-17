package com.bekir.mvpbaseproject.base;

public interface BaseView {
    void initPresenter();

    void errorPopup(String errorMsg);

    void startLoading();

    void endLoading();
}
