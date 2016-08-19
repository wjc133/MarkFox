package com.elite.tools.markfox.uploader;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 16:42
 */
public class Tools {
    private static WebSites webSites;

    public static WebSites getWebSiteTool() {
        if (webSites == null) {
            synchronized (WebSites.class) {
                if (webSites == null) {
                    webSites = new WebSites();
                }
            }
        }
        return webSites;
    }
}
