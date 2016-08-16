package com.elite.tools.markfox.client.widget;

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
        editArea.setSize(width / 2, height);

        previewArea = new JTextArea();
        previewArea.setFont(new Font("宋体", Font.BOLD, 16));
        previewArea.setLineWrap(true);
        previewArea.setSize(width / 2, height);

        //布局管理
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints editConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        add(new JScrollPane(editArea), editConstraints);

        GridBagConstraints previewConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        add(new JScrollPane(previewArea), previewConstraints);
    }

    public static TabPanel createTabPanel(int width, int height) {
        TabPanel panel = new TabPanel();
        panel.setSize(width, height);
        panel.init();
        return panel;
    }

    public static TabPanel createTabPanel(Dimension dimension) {
        return createTabPanel(dimension.width, dimension.height);
    }

    public void clear() {
        editArea.setText("");
    }

    public String getText() {
        return editArea.getText();
    }

    public void setText(String text) {
        editArea.setText(text);
    }
}
