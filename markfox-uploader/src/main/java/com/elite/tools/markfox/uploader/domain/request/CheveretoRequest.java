package com.elite.tools.markfox.uploader.domain.request;

/**
 * @author wjc133
 */
public class CheveretoRequest {
    private String key;
    private String source;
    private String format = "txt";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return "CheveretoRequest{" +
                "key='" + key + '\'' +
                ", source='" + source + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
