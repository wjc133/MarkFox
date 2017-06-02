package com.elite.tools.markfox.uploader.converter;

import com.elite.tools.markfox.common.utils.BeanUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Converter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Form-Urlencoded转换器
 *
 * @author wjc133
 */
public class FormUrlencodedConverter<T> implements Converter<T, RequestBody> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormUrlencodedConverter.class);

    @Override
    public RequestBody convert(T value) throws IOException {
        String content = "";
        if (value != null) {
            try {
                Map<String, Object> map = BeanUtils.convertBean(value);
                content = encodeParameters(map, "UTF-8");
            } catch (Exception e) {
                LOGGER.error("get field value from {} object failed. object is {}.", value.getClass(), value);
            }
        }
        return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), content);
    }

    private String encodeParameters(Map<String, Object> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String str = entry.getValue().toString();
                if ("0".equals(str) || "".equals(str)) {
                    continue;
                }

                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(str, paramsEncoding));
                encodedParams.append('&');
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedParams.toString();
    }
}
