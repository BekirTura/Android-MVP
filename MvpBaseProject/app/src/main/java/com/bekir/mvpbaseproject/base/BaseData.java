package com.bekir.mvpbaseproject.base;

class BaseData {
    private static final BaseData ourInstance = new BaseData();

    public static BaseData getInstance() {
        return ourInstance;
    }

    private BaseData() {
    }
}
