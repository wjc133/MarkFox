package com.elite.tools.markfox.client;

import com.elite.tools.markfox.client.widget.TabPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133 on 16/8/15.
 */
public class TestView {
    private JFrame frame;
    private TabPanel tabPanel;

    public TestView(){
        frame = new JFrame("MarkFox");
        frame.setSize(1000, 500);

        tabPanel = TabPanel.createTabPanel();
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(tabPanel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestView();
    }
}
