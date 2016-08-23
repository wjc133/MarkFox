package com.elite.tools.markfox.common.settings;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午8:36
 */
public class Settings {
    private GeneralSettings general;
    private PicSettings pic;

//    private static Settings INSTANCE = new Settings();
//    private Settings() {
//
//    }
//
//    public static Settings getInstance() {
//        return INSTANCE;
//    }

    public GeneralSettings getGeneral() {
        return general;
    }

    public Settings setGeneral(GeneralSettings general) {
        this.general = general;
        return this;
    }

    public PicSettings getPic() {
        return pic;
    }

    public Settings setPic(PicSettings pic) {
        this.pic = pic;
        return this;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "general=" + general +
                ", pic=" + pic +
                '}';
    }
}
