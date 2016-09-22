package com.elite.tools.markfox.common.utils;

/**
 * Created by wjc133
 * Date: 2016/9/20
 * Time: 11:44
 */
public class ClassHelper {
    /**
     * get class loader
     *
     * @param cls
     * @return class loader
     */
    public static ClassLoader getClassLoader(Class<?> cls) {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = cls.getClassLoader();
        }
        return cl;
    }

    public static ClassLoader getClassLoader() {
        return getClassLoader(ClassHelper.class);
    }
}
