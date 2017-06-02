package com.elite.tools.markfox.uploader;

import com.elite.tools.markfox.common.AppBase;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 15:54
 */
public enum WebSites {
    INSTANCE;

    private Map<String, WebSite> webSiteMap = Maps.newHashMap();
    private String defaultWebsite;

    WebSites() {
        init();
    }

    public void init() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("websites.json");
            byte[] src = new byte[1024];
            StringBuilder json = new StringBuilder();
            while (input.read(src) != -1) {
                json.append(new String(src));
            }
            List<WebSite> websites = AppBase.getGson().fromJson(json.toString().trim(), new TypeToken<List<WebSite>>() {
            }.getType());
            toMap(websites);

            for (WebSite website : websites) {
                if (website.isDefault()) {
                    defaultWebsite = website.getUrl();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void toMap(List<WebSite> websites) {
        for (WebSite website : websites) {
            webSiteMap.put(website.getUrl(), website);
        }
    }

    public String getApiKey(String website) {
        return webSiteMap.get(website).getApi();
    }

    public String getUrl(String website) {
        return webSiteMap.get(website).getUrl();
    }

    public Map<String, WebSite> getWebSiteMap() {
        return webSiteMap;
    }

    public String getDefaultWebsite() {
        return defaultWebsite;
    }
}
