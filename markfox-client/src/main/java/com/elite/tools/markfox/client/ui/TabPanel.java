package com.elite.tools.markfox.client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/8/15
 * Time: 17:44
 * 多标签页的基础内容
 */
public class TabPanel extends JPanel {
    private int width;
    private int height;
    private JTextArea editArea, previewArea;

    private TabPanel() {
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private void init() {
        editArea = new JTextArea();
        previewArea = new JTextArea();

        editArea = new JTextArea();
        editArea.setFont(new Font("宋体", Font.BOLD, 16));
        editArea.setLineWrap(true);
        editArea.setSize(width/2, height);

        previewArea = new JTextArea();
        previewArea.setFont(new Font("宋体", Font.BOLD, 16));
        previewArea.setLineWrap(true);
        previewArea.setSize(width/2, height);
    }

    public static TabPanel createTabPanel(int width, int height) {
        TabPanel panel = new TabPanel();
        panel.setSize(width, height);
        panel.init();
        return panel;
    }
}
