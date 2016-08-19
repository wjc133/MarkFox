package com.elite.tools.markfox.common;

import com.elite.tools.soar.RequestQueue;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 15:48
 */
public class AppBase {
    private static RequestQueue mQueue;

    public static RequestQueue getQueue() {
        return mQueue;
    }

    public static void setQueue(RequestQueue queue) {
        mQueue = queue;
    }
}
