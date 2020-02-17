package com.bekir.mvpbaseproject.util.themamode;

import java.util.HashMap;
import java.util.Map;

public enum ThemaModeEnum {

    DARKMODE("Dark"),
    LIGHTMODE("Light");


    private String value;

    ThemaModeEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    //Lookup table
    private static final Map<String, ThemaModeEnum> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(ThemaModeEnum themaModeEnum : ThemaModeEnum.values())
        {
            lookup.put(themaModeEnum.getValue(), themaModeEnum);
        }
    }

    //This method can be used for reverse lookup purpose
    public static ThemaModeEnum get(String value)
    {
        return lookup.get(value);
    }
}
