package com.elite.tools.markfox.client.bootstrap;

import org.apache.commons.lang3.StringUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wjc133
 * Date: 2016/7/29
 * Time: 11:30
 */
public class Config {
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    public static void configLogging() {
        String path = copyLoggingFile();
        if (StringUtils.isNotEmpty(path)) {
            System.setProperty("java.util.logging.config.file", path);
        }
    }

    public static void julToSlf4j() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    private static String copyLoggingFile() {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            ClassLoader cl = BootStrap.class.getClassLoader();
            in = cl.getResourceAsStream("logging.properties");
            File configFile = File.createTempFile("logging", ".properties");
            configFile.deleteOnExit();
            out = new FileOutputStream(configFile);
            int i;
            byte[] buf = new byte[1024];
            while ((i = in.read(buf, 0, buf.length)) > 0) {
                out.write(buf, 0, i);
            }
            LOG.info("java logging api config file path : {}", configFile.getAbsolutePath());
            return configFile.getAbsolutePath();
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public static void configAll() {
        configLogging();
        julToSlf4j();

        beautyEye();
    }

    public static void beautyEye() {
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
