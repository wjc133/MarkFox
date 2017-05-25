package com.teamdev.jxbrowser.chromium.demo;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjc133
 * Date: 2016/7/28
 * Time: 21:07
 */
public class JxBrowserDemo {
    private static final Logger LOG = LoggerFactory.getLogger(JxBrowserDemo.class);

    private BrowserView browserView;

    public JxBrowserDemo() {
        BrowserPreferences.setChromiumSwitches(
                "--disable-web-security",
                "--allow-file-access-from-files",
                "--remote-debugging-port=9222");
        Browser browser = new Browser();
        browserView = new BrowserView(browser);
        if (LOG.isDebugEnabled()) {
            browser.addConsoleListener(new ConsoleListener() {
                @Override
                public void onMessage(ConsoleEvent consoleEvent) {
                    LOG.debug("Console: " + consoleEvent.getMessage());
                }
            });
        }
        String remoteDebuggingURL = browser.getRemoteDebuggingURL();
        LOG.info("remote debugging url >>> {}", remoteDebuggingURL);
    }

    public BrowserView getBrowserView() {
        return browserView;
    }

//    public static void main(String[] args) {
//        JxBrowserDemo demo = new JxBrowserDemo();
//
//        JFrame frame = new JFrame("JxBrowser - Hello World");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(demo.getBrowserView(), BorderLayout.CENTER);
//        frame.setSize(500, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//        demo.getBrowserView().getBrowser().loadURL("http://www.yy.com");
//    }
}
