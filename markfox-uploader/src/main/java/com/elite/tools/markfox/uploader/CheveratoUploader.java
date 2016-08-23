package com.elite.tools.markfox.uploader;

import com.elite.tools.markfox.common.AppBase;
import com.elite.tools.markfox.common.settings.PicSettings;
import com.elite.tools.soar.AuthFailureError;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.toolbox.RequestFuture;
import com.elite.tools.soar.toolbox.StringRequest;
import com.google.common.collect.Maps;
import org.apache.cxf.common.util.Base64Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 14:39
 */
public class CheveratoUploader implements PicUploader {
    private String url;
    private String website;

    public CheveratoUploader() {
        this.website = WebSites.INSTANCE.getDefaultWebsite();
        this.url = "http://" + website + "/api/1/upload";
    }

    public CheveratoUploader(String website) {
        this.website = website;
        url = "http://" + website + "/api/1/upload";
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String upload(Image image) {
        try {
            if (image instanceof BufferedImage) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) image, "png", output);
                byte[] bytes = output.toByteArray();
                final String file64 = Base64Utility.encode(bytes);
                RequestFuture<String> future = RequestFuture.newFuture();
                StringRequest request = new StringRequest(Request.Method.POST, url,
                        future, future) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = Maps.newHashMap();
                        params.put("key", WebSites.INSTANCE.getApiKey(website));
                        params.put("source", file64);
                        params.put("format", "txt");
                        return params;
                    }
                };
                AppBase.getQueue().add(request);
                return future.get(5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<String> batchUpload(List<Image> objList) {
        return null;
    }
}