package com.elite.tools.markfox.client.widget;

import com.teamdev.jxbrowser.chromium.demo.JxBrowserDemo;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/8/16
 * Time: 16:48
 */
public class PreviewPanel extends JPanel {
    private BrowserView browserView;

    public PreviewPanel() {
        init();
    }

    private void init() {
        browserView = new JxBrowserDemo().getBrowserView();
        setLayout(new BorderLayout());
        add(browserView);
    }


    public BrowserView getBrowserView() {
        return browserView;
    }
}
