package com.elite.tools.markfox.common;

import com.elite.tools.markfox.common.settings.Settings;
import com.elite.tools.soar.RequestQueue;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 15:48
 */
public class AppBase {
    private static RequestQueue mQueue;
    private static Settings conf;

    public static RequestQueue getQueue() {
        return mQueue;
    }

    public static void setQueue(RequestQueue queue) {
        mQueue = queue;
    }

    public static Settings getConf() {
        return conf;
    }

    public static void setConf(Settings conf) {
        AppBase.conf = conf;
    }
}
