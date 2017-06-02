package com.elite.tools.markfox.uploader.api;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * @author wjc133
 */
public interface CunTuKuApi {
    String KEY = "584bf3b4398f4e01f695cc0c50253110";

    @POST("/api/1/upload")
    Call<String> upload(@QueryMap Map<String, String> params);
}
