package com.elite.tools.markfox.common.utils;

import java.io.File;

/**
 * Created by wjc133
 * Date: 2016/8/25
 * Time: 20:22
 */
public class FileUtils {
    /**
     * Java文件操作 获取文件扩展名
     * <p>
     * Created on: 2011-8-2
     * Author: blueeagle
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取带拓展名的文件名
     *
     * @param path 文件路径
     * @return 带拓展名的文件名
     */
    public static String getFileName(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.getName();
        }
        return null;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     * <p>
     * Created on: 2011-8-2
     * Author: blueeagle
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static String convertPathToUnix(String path) {
        if (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.contains("\\")) {
            path = path.replace("\\", "/");
        }
        return path;
    }
}
