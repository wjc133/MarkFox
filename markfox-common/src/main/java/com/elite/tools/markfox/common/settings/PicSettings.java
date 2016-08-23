package com.elite.tools.markfox.common.settings;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午8:37
 */
public class PicSettings {
    private int timeout;
    private String website;

    public int getTimeout() {
        return timeout;
    }

    public PicSettings setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public PicSettings setWebsite(String website) {
        this.website = website;
        return this;
    }

    @Override
    public String toString() {
        return "PicSettings{" +
                "timeout=" + timeout +
                ", website='" + website + '\'' +
                '}';
    }
}
