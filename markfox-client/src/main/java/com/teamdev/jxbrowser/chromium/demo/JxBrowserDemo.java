package com.teamdev.jxbrowser.chromium.demo;

import com.elite.tools.markfox.client.widget.TabPanel;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by wjc133
 * Date: 2016/7/28
 * Time: 21:07
 */
public class JxBrowserDemo {
    private static final Logger LOG = LoggerFactory.getLogger(JxBrowserDemo.class);

    private BrowserView browserView;
    static private Browser browser;
    public JxBrowserDemo() {
        BrowserPreferences.setChromiumSwitches(
                "--disable-web-security",
                "--allow-file-access-from-files");
        browser = new Browser();
        browserView = new BrowserView(browser);
        if (LOG.isDebugEnabled()) {
            browser.addConsoleListener(new ConsoleListener() {
                @Override
                public void onMessage(ConsoleEvent consoleEvent) {
                    LOG.debug("Console: " + consoleEvent.getMessage());
                }
            });
        }
    }

    public BrowserView getBrowserView() {
        return browserView;
    }
    static public Browser getBrower()
    {
        return browser;
    }
}
