package com.elite.tools.markfox.client.ui;

import com.elite.tools.markfox.client.widget.MainMenu;
import com.elite.tools.markfox.client.widget.MainToolBar;
import com.elite.tools.markfox.client.widget.TabPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by wjc133
 * Date: 2016/7/21
 * Time: 13:11
 * 主界面
 */
public class MainView extends AbstractView {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    private static final MainView INSTANCE = new MainView();
    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);

    private MainView() {
        initWindow();
    }

    public static MainView getInstance() {
        return INSTANCE;
    }

    private void initWindow() {
        LOG.info("init window invoked...");
        frame = new JFrame("MarkFox");
        frame.pack();
        frame.setSize(1000, 500);


        Container container = frame.getContentPane();
        container.add(createMainPanel());

        try {
            URL icon = MainView.class.getClassLoader().getResource("icon/logo.png");
            if (icon != null) {
                frame.setIconImage(ImageIO.read(icon));
            }
        } catch (IOException e) {
            LOG.error("set icon error:{}", e);
        }
        frame.setJMenuBar(MainMenu.getInstance());
        frame.setVisible(true);
        //设置关闭按钮响应
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JPanel createMainPanel() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("新文档", TabPanel.createTabPanel(frame.getSize()));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(MainToolBar.getInstance(), BorderLayout.NORTH);
        mainPanel.add(tabbedPane);
        return mainPanel;
    }

    public void addTab(TabPanel tab) {
        tabbedPane.add(tab);
    }
}
