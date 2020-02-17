package com.bekir.mvpbaseproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseScreen <T extends BasePresenter> extends Fragment implements BaseView {

    protected  T  presenter;

    private BaseData baseData = BaseData.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        initView();
        initPresenter();
        return view;
    }

    @Override
    public void errorPopup(String errorMsg) {
        // Popup İnit
    }


    @Override
    public void startLoading() {
        // Loading Spinner Add
    }

    @Override
    public void endLoading() {
        // Loading Spinner Delete
    }

    public BaseData getBaseData(){
        return baseData;
    }

    @Override
    public void initPresenter() {
        this.presenter = setPresenter();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract T setPresenter();

}
