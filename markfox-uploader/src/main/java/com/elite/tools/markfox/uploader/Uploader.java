package com.elite.tools.markfox.uploader;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjc133
 * Date: 2016/6/15
 * Time: 20:40
 */
public interface Uploader<T> {
    String upload(T obj);

    String upload(T obj, int timeout, TimeUnit unit);

    List<String> batchUpload(List<T> objList);

    List<String> batchUpload(List<T> objList, int timeout, TimeUnit unit);
}
