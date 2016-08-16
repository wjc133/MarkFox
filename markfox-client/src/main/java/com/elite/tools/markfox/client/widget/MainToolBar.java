package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.client.ui.MainView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by wjc133.
 * Date: 16/8/15
 * Time: 下午11:00
 */
public class MainToolBar extends JPanel {
    private static final MainToolBar INSTANCE = new MainToolBar();

    private MainToolBar() {
        init();
    }

    public static MainToolBar getInstance() {
        return INSTANCE;
    }

    private void init() {
        JButton button1 = new JButton("B ");
        JButton button2 = new JButton("I ");
        JButton button3 = new JButton("H1");
        JButton button4 = new JButton("H2");
        JButton iconButton1 = new JButton();
        button1.setSize(15, 15);
        button2.setSize(15, 15);
        button3.setSize(15, 15);
        button4.setSize(15, 15);

        JToolBar toolBar = new JToolBar();
        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(button3);
        toolBar.add(button4);

        URL image = MainView.class.getClassLoader().getResource("icon/file.jpg");
        if (image != null) {
            iconButton1.setIcon(new ImageIcon(image));
            iconButton1.setSize(15, 15);
            toolBar.add(iconButton1);
        }

        setLayout(new BorderLayout());
        add(toolBar);
    }
}
