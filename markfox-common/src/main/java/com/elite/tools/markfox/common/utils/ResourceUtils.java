package com.elite.tools.markfox.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.net.URL;

/**
 * Created by wjc133.
 * Date: 16/8/23
 * Time: 下午11:37
 */
public class ResourceUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceUtils.class);
    private static ClassLoader classLoader = ResourceUtils.class.getClassLoader();

    public static String getContent(String path) {
        InputStream input = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                return null;
            }
            input = new FileInputStream(file);
            byte[] src = new byte[1024];
            StringBuilder builder = new StringBuilder();
            while (input.read(src) != -1) {
                builder.append(new String(src));
            }
            return builder.toString();
        } catch (Exception e) {
            LOG.error("has an error:{}", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("close stream error:{}", e);
                }
            }
        }
        return null;
    }

    public static ImageIcon getIcon(URL resource) {
        if (resource == null) {
            return null;
        }
        return new ImageIcon(resource);
    }

    public static ImageIcon getIcon(String classpath) {
        URL resource = classLoader.getResource(classpath);
        return getIcon(resource);
    }

    public static void saveContent(String path, String content) {
        FileOutputStream output = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    if (!parent.mkdirs()) {
                        return;
                    }
                }
//                file.createNewFile();
            }
            output = new FileOutputStream(path);
            output.write(content.getBytes());
            output.flush();
        } catch (Exception e) {
            LOG.error("save content has an error:{}", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LOG.error("close stream error:{}", e);
                }
            }
        }
    }

    public static File loadFile(String fileName) {
        if (fileName.startsWith("/")) {
            return new File(fileName);
        }
        URL url = null;
        try {
            url = ClassHelper.getClassLoader().getResource(fileName);
        } catch (Throwable t) {
            LOG.warn("Fail to load " + fileName + " file: " + t.getMessage(), t);
        }

        if (url == null) {
            LOG.warn("No " + fileName + " found on the class path.");
            return null;
        }

        try {
            return new File(url.toURI());
        } catch (Throwable e) {
            LOG.warn("Failed to load " + fileName + " file from " + fileName + "(ingore this file): " + e.getMessage(), e);
        }
        return null;
    }
}
