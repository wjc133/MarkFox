package com.elite.tools.markfox.client.ui;

import com.elite.tools.markfox.client.widget.MainMenu;
import com.elite.tools.markfox.client.widget.MainToolBar;
import com.elite.tools.markfox.client.widget.TabPanel;
import com.elite.tools.markfox.client.widget.TabbedPopupMenu;
import com.elite.tools.markfox.common.FileStorager;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by wjc133
 * Date: 2016/7/21
 * Time: 13:11
 * 主界面
 */
public class MainView extends AbstractView {
    private JFrame frame;
    private MainMenu menuBar;
    private MainToolBar toolBar;
    private JTabbedPane tabbedPane;
    private TabbedPopupMenu tabbedPopupMenu;

    private int lastIndex;
    private int i=2;
    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);

    private Map<TabPanel, String> pathMap = Maps.newHashMap();
    private FileStorager storager = new FileStorager();

    private static final MainView INSTANCE = new MainView();

    private MainView() {
        initComponents();
        initWindow();
        bindAction();
    }

    private void bindAction() {
        JMenuItem newItem = menuBar.getMenu(0).getItem(0);
        JMenuItem openItem = menuBar.getMenu(0).getItem(1);
        JMenuItem saveItem = menuBar.getMenu(0).getItem(2);
        JMenuItem saveAsItem = menuBar.getMenu(0).getItem(3);
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileChooser = new JFileChooser();

                TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();//当前标签
                String path = pathMap.get(currentTab);//当前标签页路径
                if (path == null) {
                    int result = fileChooser.showSaveDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
                        file = fileChooser.getSelectedFile(); // 得到选择的文件
                        pathMap.put(currentTab, file.getAbsolutePath());
                        LOG.debug("file choose：{}", file.getName());
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        LOG.debug("file chooser canceled");
                    }
                } else {
                    file = new File(path);
                }

                if (file != null) {
                    storager.setFile(file).save(currentTab.getText());
                }
            }
        });

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setApproveButtonText("确定");
                fileChooser.setDialogTitle("打开文件");

                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    LOG.debug("file choose：{}", file.getName());
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    LOG.debug("file chooser canceled");
                }
                if (file != null) {
                    TabPanel newTab = addNewTab();
                    pathMap.put(newTab, file.getAbsolutePath());//打开的时候
                    storager.setFile(file);
                    newTab.setText(storager.open());
                }
            }
        });

        saveAsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    LOG.debug("file choose：{}", file.getName());
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    LOG.debug("file chooser canceled");
                }
                if (file != null) {
                    TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();
                    pathMap.put(currentTab, file.getAbsolutePath());
                    storager.setFile(file).save(currentTab.getText());
                }
            }
        });
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewTab1();
            }
        });
        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    lastIndex = ((JTabbedPane) e.getComponent()).getUI().tabForCoordinate(tabbedPane, e.getX(), e.getY());
                    tabbedPopupMenu.show(tabbedPane, e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    final int index = ((JTabbedPane) e.getComponent()).getUI().tabForCoordinate(tabbedPane, e.getX(), e.getY());
                    closeTab(index);
                }
            }
        });

        tabbedPopupMenu.getCloseItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeTab(lastIndex);
            }
        });

        tabbedPopupMenu.getCloseOthersItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeOtherTabs(lastIndex);
            }
        });

        tabbedPopupMenu.getCloseAllItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllTabs();
            }
        });
    }

    private void closeOtherTabs(int index) {
        if (index >= 0) {
            int k=0;
            int count = tabbedPane.getTabCount();
            for (int i = 0; i < count; i++) {
                if (i != index) {
                    tabbedPane.removeTabAt(k);
                }else {
                    k++;
                }
            }
        }
    }

    private void closeAllTabs() {
        tabbedPane.removeAll();
    }

    private void closeTab(int index) {
        tabbedPane.removeTabAt(index);
    }

    public static MainView getInstance() {
        return INSTANCE;
    }

    private void initWindow() {
        LOG.info("init window invoked...");
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
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void show() {
        frame.setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new GridBagLayout());
//        GridBagConstraints toolBarBag = new GridBagConstraints(0, 0, 1, 1, 1, 1,
//                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
//                new Insets(0, 0, 0, 0), 0, 0);
//        mainPanel.add(toolBar, toolBarBag);
//
//        GridBagConstraints tabbedBag = new GridBagConstraints(0, 1, 1, 1, 1, 1,
//                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//                new Insets(0, 0, 0, 0), 0, 0);
//        mainPanel.add(tabbedPane, tabbedBag);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(tabbedPane);
        return mainPanel;
    }

    private void initComponents() {
        frame = new JFrame("MarkFox");
        menuBar = MainMenu.getInstance();
        toolBar = MainToolBar.getInstance();
        tabbedPopupMenu = TabbedPopupMenu.getInstance();

        tabbedPane = new JTabbedPane();
        addNewTab();
    }

    public void addTab(TabPanel tab) {
        tabbedPane.add(tab);
    }

    public TabPanel addNewTab() {
        TabPanel tabPanel = TabPanel.createTabPanel();
        tabbedPane.addTab("新文档", tabPanel);
        //tabbedPane.setSelectedIndex(i-1);
        //i++;
        return tabPanel;
    }
    public TabPanel addNewTab1() {
        TabPanel tabPanel = TabPanel.createTabPanel();
        tabbedPane.addTab("新文档"+"("+i+")", tabPanel);
        tabbedPane.setSelectedIndex(i-1);
        i++;
        return tabPanel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public MainMenu getMenuBar() {
        return menuBar;
    }

    public MainToolBar getToolBar() {
        return toolBar;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
