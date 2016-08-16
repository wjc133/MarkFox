package com.teamdev.jxbrowser.chromium.demo;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/7/28
 * Time: 21:07
 */
public class JxBrowserDemo {
    private BrowserView browserView;

    public JxBrowserDemo() {
        Browser browser = new Browser();
        browserView = new BrowserView(browser);
    }

    public BrowserView getBrowserView() {
        return browserView;
    }
}
