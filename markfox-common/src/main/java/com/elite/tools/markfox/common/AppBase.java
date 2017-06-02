package com.elite.tools.markfox.common;

import com.elite.tools.markfox.common.settings.Settings;
import com.google.gson.Gson;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 15:48
 */
public class AppBase {

    private static Settings conf;

    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    public static Settings getConf() {
        return conf;
    }

    public static void setConf(Settings conf) {
        AppBase.conf = conf;
    }
}
