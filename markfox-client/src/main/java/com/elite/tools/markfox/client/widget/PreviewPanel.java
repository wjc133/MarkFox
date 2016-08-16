package com.elite.tools.markfox.client.widget;

import com.teamdev.jxbrowser.chromium.demo.JxBrowserDemo;

import javax.swing.*;

/**
 * Created by wjc133
 * Date: 2016/8/16
 * Time: 16:48
 */
public class PreviewPanel extends JPanel {
    public PreviewPanel() {
        init();
    }

    private void init() {
        add(new JxBrowserDemo().getBrowserView());
    }
}
