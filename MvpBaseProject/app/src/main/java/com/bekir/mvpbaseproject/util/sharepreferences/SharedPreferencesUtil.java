package com.bekir.mvpbaseproject.util.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static final String APP_SHARED_PREFERENCES_UTIL = "YKB_IK_BASE_UI_PROJECT_SHARED";

    public static void addSharedValue(Context context,SharePreferencesEnum sharePreferencesEnum,String value){
        SharedPreferences preferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_UTIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(sharePreferencesEnum.getValue(), value);
        editor.commit();
    }

    public static String  getSharedValue(Context context, SharePreferencesEnum sharePreferencesEnum){
        SharedPreferences preferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_UTIL, Context.MODE_PRIVATE);
        return preferences.getString(sharePreferencesEnum.getValue(),null);
    }


}
