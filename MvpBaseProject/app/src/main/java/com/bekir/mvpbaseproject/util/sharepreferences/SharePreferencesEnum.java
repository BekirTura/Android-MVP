package com.bekir.mvpbaseproject.util.sharepreferences;

public enum  SharePreferencesEnum {

    THEMAMODE("themaMode");


    private String value;

    SharePreferencesEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
