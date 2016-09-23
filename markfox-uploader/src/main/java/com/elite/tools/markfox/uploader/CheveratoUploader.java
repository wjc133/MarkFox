package com.elite.tools.markfox.uploader;

import com.elite.tools.markfox.common.AppBase;
import com.elite.tools.markfox.uploader.constants.UploadError;
import com.elite.tools.soar.AuthFailureError;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.Response;
import com.elite.tools.soar.SoarError;
import com.elite.tools.soar.toolbox.RequestFuture;
import com.elite.tools.soar.toolbox.StringRequest;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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
    }

    public CheveratoUploader(String website) {
        this.website = website;
        url = "http://" + website + "/api/1/upload";
    }

    public void setWebsite(String website) {
        this.website = website;
        this.url = "http://" + website + "/api/1/upload";
    }

    public String upload(Image image, int timeout, TimeUnit unit) {
        if (StringUtils.isEmpty(website)) {
            return "";
        }
        if (StringUtils.isEmpty(url)) {
            this.url = "http://" + website + "/api/1/upload";
        }
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
                return future.get(timeout, unit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String upload(Image obj) {
        return this.upload(obj, 5, TimeUnit.SECONDS);
    }

    @Override
    public void asyncUpload(Image image, final UploadListener listener) {
        listener.onUploadBegin();
        if (StringUtils.isEmpty(website)) {
            listener.onUploadError(UploadError.CONTENT_EMPTY_ERROR);
            return;
        }
        if (StringUtils.isEmpty(url)) {
            this.url = "http://" + website + "/api/1/upload";
        }
        try {
            if (image instanceof BufferedImage) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) image, "png", output);
                byte[] bytes = output.toByteArray();
                final String file64 = Base64Utility.encode(bytes);
                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                listener.onUploadFinished(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(SoarError error) {
                                listener.onUploadError(UploadError.SERVER_ERROR);
                            }
                        }) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> batchUpload(List<Image> objList, int timeout, TimeUnit unit) {
        return null;
    }

    public List<String> batchUpload(List<Image> objList) {
        return null;
    }
}
