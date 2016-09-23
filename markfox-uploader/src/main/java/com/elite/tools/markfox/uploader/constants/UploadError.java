package com.elite.tools.markfox.uploader.constants;

/**
 * Created by wjc133
 * Date: 2016/9/23
 * Time: 14:20
 */
public enum UploadError {
    CONTENT_EMPTY_ERROR(101, "上传内容为空"),
    SERVER_ERROR(102, "服务器错误");

    private Integer code;
    private String msg;

    UploadError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
