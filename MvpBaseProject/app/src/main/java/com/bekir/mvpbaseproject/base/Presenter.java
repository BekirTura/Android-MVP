package com.bekir.mvpbaseproject.base;

public interface Presenter<T extends BaseView> {
    void detachView();
}