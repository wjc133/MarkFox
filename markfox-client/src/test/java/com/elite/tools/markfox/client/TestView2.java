package com.elite.tools.markfox.client;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.demo.JxBrowserDemo;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/8/16
 * Time: 18:39
 */
public class TestView2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        Container container = frame.getContentPane();

        JPanel panel = new JPanel();

        JTextArea com1 = new JTextArea();
        com1.setText("Hello World!");
        JButton com2 = new JButton();
        com2.setText("Send");

        //Layout Manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(new GridLayout(1,2));
        GridBagConstraints editConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        panel.add(new JScrollPane(com1));

        GridBagConstraints previewConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        BrowserView browserView = new JxBrowserDemo().getBrowserView();
        browserView.getBrowser().loadHTML("<h1>Hello</h1>");
        panel.add(browserView);
//        container.add(com2, previewConstraints);

        container.setLayout(new BorderLayout());
        container.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
