package com.elite.tools.markfox.client.bootstrap;

import com.elite.tools.markfox.common.AppBase;
import com.elite.tools.markfox.common.settings.Settings;
import com.elite.tools.markfox.common.utils.ResourceUtils;
import com.elite.tools.soar.toolbox.JsonUtils;
import com.elite.tools.soar.toolbox.Soar;
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
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    private static final String CONFIG_HOME = System.getProperties().getProperty("user.home") + "/.markfox/conf";

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

        loadingConfig();

        beautyEye();

        soar();
    }

    /**
     * 读取配置信息,初始化时将配置载入内存
     */
    private static void loadingConfig() {
        String json = ResourceUtils.getContent(CONFIG_HOME + "/common.json");
        if (StringUtils.isEmpty(json)) {
            AppBase.setConf(Settings.DEFAULT_SETTINGS);
            saveConfig();
        } else {
            AppBase.setConf(JsonUtils.fromJson(json.trim(), Settings.class));
        }
    }

    /**
     * 将内存中的配置回写到磁盘文件中
     */
    public static void saveConfig() {
        String json = JsonUtils.toJson(AppBase.getConf());
        ResourceUtils.saveContent(CONFIG_HOME + "/common.json", json);
    }

    private static void soar() {
        AppBase.setQueue(Soar.newRequestQueue());
    }

    private static void beautyEye() {
        try {
            System.setProperty("sun.java2d.noddraw", "true");
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            //取消设置按钮
            UIManager.put("RootPane.setupButtonVisible", false);
            //取消多标签padding
            UIManager.put("TabbedPane.tabAreaInsets"
                    , new javax.swing.plaf.InsetsUIResource(3, 3, 2, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
