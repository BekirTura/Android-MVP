package com.bekir.mvpbaseproject.base;

import androidx.annotation.Nullable;

import com.bekir.mvpbaseproject.network.RestClient;

public abstract class BasePresenter<T extends BaseView> implements Presenter<T> {

    protected RestClient restClient = RestClient.getInstance();

    @Nullable
    private T baseView;

    public BasePresenter(T baseView){
        this.baseView = baseView;
    }

    @Override
    public void detachView() {
        baseView = null;
    }

    public boolean isViewAttached() {
        return baseView != null;
    }

    @Nullable
    public T getBaseView() {
        return baseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }

}
