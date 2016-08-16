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
        frame.pack();
        frame.setSize(1000, 500);

        Dimension dim = frame.getSize();

        tabPanel = TabPanel.createTabPanel(dim);
        Container container = frame.getContentPane();
        container.add(tabPanel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestView();
    }
}
