package com.elite.tools.markfox.uploader;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午8:56
 */
public class Uploaders {
    private static PicUploader picUploader;
    public static PicUploader getDefaultPicUploader() {
        if (picUploader == null) {
            synchronized (Uploaders.class) {
                if (picUploader == null) {
                    picUploader = new CheveratoUploader();
                }
            }
        }
        return picUploader;
    }
}
