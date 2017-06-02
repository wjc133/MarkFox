package com.elite.tools.markfox.common.utils;

import java.lang.annotation.*;

/**
 * Field别名
 *
 * @author wjc133
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Alias {
    String value();
}
