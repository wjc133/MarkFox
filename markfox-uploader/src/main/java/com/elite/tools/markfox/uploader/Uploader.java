package com.elite.tools.markfox.uploader;

import com.elite.tools.markfox.uploader.constants.UploadError;

/**
 * Created by wjc133
 * Date: 2016/6/15
 * Time: 20:40
 */
public interface Uploader<T> {
    String upload(T obj);

    void asyncUpload(T obj, UploadListener listener);

    interface UploadListener {
        void onUploadBegin();

        void onUploadFinished(String result);

        void onUploadError(UploadError error);
    }
}
