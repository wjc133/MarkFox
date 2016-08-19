package com.elite.tools.markfox.uploader;

import java.util.List;

/**
 * Created by wjc133
 * Date: 2016/6/15
 * Time: 20:40
 */
public interface Uploader<T> {
    String upload(T obj);

    List<String> batchUpload(List<T> objList);
}
