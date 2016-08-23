package com.elite.tools.markfox.common.settings;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午8:37
 */
public class GeneralSettings {
    private boolean firstBoot;

    public boolean isFirstBoot() {
        return firstBoot;
    }

    public GeneralSettings setFirstBoot(boolean firstBoot) {
        this.firstBoot = firstBoot;
        return this;
    }

    @Override
    public String toString() {
        return "GeneralSettings{" +
                "firstBoot=" + firstBoot +
                '}';
    }
}
