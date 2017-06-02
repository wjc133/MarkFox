package com.elite.tools.markfox.uploader;

import com.elite.tools.markfox.uploader.api.CunTuKuApi;
import com.elite.tools.markfox.uploader.constants.UploadError;
import com.elite.tools.markfox.uploader.converter.FormUrlencodedConverterFactory;
import com.google.common.collect.Maps;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.cxf.common.util.Base64Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 14:39
 */
public class CunTuKuUploader implements PicUploader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CunTuKuUploader.class);

    private final CunTuKuApi api;

    public CunTuKuUploader() {
        final Logger logger = LoggerFactory.getLogger(CunTuKuUploader.class.getPackage().getName());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (logger.isDebugEnabled()) {
                    logger.debug(message);
                }
            }
        });
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(FormUrlencodedConverterFactory.create())
                .baseUrl("http://cuntuku.com")
                .build();

        api = retrofit.create(CunTuKuApi.class);
    }

    public String upload(Image image) {
        try {
            if (image instanceof BufferedImage) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) image, "png", output);
                byte[] bytes = output.toByteArray();
                final String file64 = Base64Utility.encode(bytes);
                Map<String, String> params = Maps.newHashMap();
                params.put("key", CunTuKuApi.KEY);
                params.put("source", file64);
                params.put("format", "txt");
                Call<String> call = api.upload(params);
                Response<String> response = call.execute();
                if (!response.isSuccessful()) {
                    LOGGER.warn("upload img to cuntuku failed.");
                    return "";
                }
                return response.body();
            }
        } catch (Exception e) {
            LOGGER.error("upload img to cuntuku failed.", e);
        }
        return "";
    }

    @Override
    public void asyncUpload(Image image, final UploadListener listener) {
        listener.onUploadBegin();
        try {
            if (image instanceof BufferedImage) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) image, "png", output);
                byte[] bytes = output.toByteArray();
                final String file64 = Base64Utility.encode(bytes);
                Map<String, String> params = Maps.newHashMap();
                params.put("key", CunTuKuApi.KEY);
                params.put("source", file64);
                params.put("format", "txt");

                Call<String> call = api.upload(params);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.isSuccessful()) {
                            LOGGER.warn("upload img to cuntuku failed.");
                            listener.onUploadError(UploadError.SERVER_ERROR);
                            return;
                        }
                        listener.onUploadFinished(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        LOGGER.error("upload img to cuntuku failed.", t);
                        listener.onUploadError(UploadError.SERVER_ERROR);
                    }
                });
            }
        } catch (Exception e) {
            LOGGER.error("upload img to cuntuku failed.", e);
        }
    }
}
