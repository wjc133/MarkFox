package com.elite.tools.markfox.client.widget;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/9/23
 * Time: 15:12
 */
public class ProgressDialog extends JDialog {
    private JProgressBar bar;
    private JLabel label;
    private Component owner;
    private JPanel panel;

    public ProgressDialog(Frame owner) {
        super(owner);
        this.owner = owner;
        init();
    }

    public void init() {
//        setUndecorated(true);
        setSize(260, 140);
        setResizable(false);

        panel = new JPanel(new GridLayout(2, 1));
        bar = new JProgressBar();
        bar.setIndeterminate(true);
        label = new JLabel();
        setLayout(new BorderLayout());
        panel.add(label);
        panel.add(bar);
        add(panel);
    }

    public void show(String msg) {
        setUndecorated(true);
        setLocationRelativeTo(owner);
        if (StringUtils.isNotEmpty(msg) && msg.length() <= 15) {
            label.setText(msg);
        }
        System.out.println("size====" + this.getSize());
        setVisible(true);
    }
}
