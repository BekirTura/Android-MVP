package com.bekir.mvpbaseproject.util.themamode;

import android.content.Context;

import com.bekir.mvpbaseproject.util.sharepreferences.SharePreferencesEnum;
import com.bekir.mvpbaseproject.util.sharepreferences.SharedPreferencesUtil;


public class ThemaModeUtil {

    private static final ThemaModeEnum DEFAULT_THEMA_MODE = ThemaModeEnum.LIGHTMODE;


    public static ThemaModeEnum getDarkModePref(Context context){
        String mode = SharedPreferencesUtil.getSharedValue(context, SharePreferencesEnum.THEMAMODE);
        if(mode!= null){
            ThemaModeEnum themaModeEnum = ThemaModeEnum.get(SharedPreferencesUtil.getSharedValue(context,SharePreferencesEnum.THEMAMODE));
            return themaModeEnum;
        }else{
            return DEFAULT_THEMA_MODE;
        }
    }


    public static void setDarkModePref(Context context,ThemaModeEnum themaModeEnum){
        SharedPreferencesUtil.addSharedValue(context,SharePreferencesEnum.THEMAMODE,themaModeEnum.getValue());
    }

}
