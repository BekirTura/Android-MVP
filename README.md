# Android-MVP (Inprogres)
Android Mvp (Retrofit 2) Java
@Mvp @Android Development Architecture
@Mobile Architecture

# Base Mvp Layer 


* BaseScreen (Fragment)

```Java 
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
```

* BasePresenter
```Java  
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
```
* BaseView
```Java  
package com.bekir.mvpbaseproject.base;

public interface BaseView {
    void initPresenter();

    void errorPopup(String errorMsg);

    void startLoading();

    void endLoading();
}
```

# Mvp Layer Example Using (Using Base Mvp Layer)

* HomeView
```Java  
package com.bekir.mvpbaseproject.screens.mvpmodule.home;

import com.bekir.mvpbaseproject.base.BaseView;
import com.bekir.mvpbaseproject.model.response.bizclub.EventListResponse;

public interface HomeView extends BaseView {

    void getUIData();

    void bindEventListResponse(EventListResponse result);

    void errorFlag();

}
```

* HomePresenter

```Java  
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
```

* HomeScreen

```Java 
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
```
