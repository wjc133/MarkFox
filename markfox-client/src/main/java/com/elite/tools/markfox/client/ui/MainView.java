package com.elite.tools.markfox.client.ui;


import com.elite.tools.markfox.client.widget.*;
import com.elite.tools.markfox.common.FileStorager;
import com.elite.tools.markfox.common.utils.FileUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private ProgressDialog progressDialog;

    private int lastIndex;
    private int i = 1;
    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);

    private Map<TabPanel, String> pathMap = Maps.newHashMap();
    private FileStorager storager = new FileStorager();

    private static final MainView INSTANCE = new MainView();

    private MainView() {
        initComponents();
        initWindow();
        bindAction();
        EditAction();
        ToolAction();
    }

    private void bindAction() {
        JMenuItem newItem = menuBar.getMenu(0).getItem(0);
        JMenuItem openItem = menuBar.getMenu(0).getItem(1);
        JMenuItem saveItem = menuBar.getMenu(0).getItem(2);
        JMenuItem saveAsItem = menuBar.getMenu(0).getItem(3);
        JMenuItem exitItem = menuBar.getMenu(0).getItem(6);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
                fileChooser.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            String filename = f.getName().toLowerCase();
                            return filename.endsWith(".md") || filename.endsWith(".markdown");
                        }
                    }

                    @Override
                    public String getDescription() {
                        return "Markdown文件(*.md)";
                    }
                });
                fileChooser.setAcceptAllFileFilterUsed(false);
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
                    TabPanel newTab = addNewTab(FileUtils.getFileNameNoEx(file.getName()));
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
                addNewTab();
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
                System.exit(1);
            }
        });

        JMenuItem optionItem = menuBar.getMenu(2).getItem(0);
        optionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingView settingView = SettingView.getInstance(MainView.this.frame);
                settingView.show();
            }
        });

        JMenuItem helpItem = menuBar.getMenu(3).getItem(0);
        JMenuItem aboutItem = menuBar.getMenu(3).getItem(1);
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Geek还需要什么帮助吗?",
                        "帮助", JOptionPane.NO_OPTION);
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "MarkFox 0.0.1预览版,致力于成为最好用的Markdown编辑器",
                        "关于", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void EditAction() {
        JMenuItem Undotem = menuBar.getMenu(1).getItem(0);
        JMenuItem CutItem = menuBar.getMenu(1).getItem(2);
        JMenuItem CopyItem = menuBar.getMenu(1).getItem(3);
        JMenuItem PasteItem = menuBar.getMenu(1).getItem(4);
        JMenuItem AllItem = menuBar.getMenu(1).getItem(7);
        CopyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();
                currentTab.getEditArea().copy();
            }
        });
        PasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();
                currentTab.getEditArea().paste();
            }
        });
        CutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();
                currentTab.getEditArea().cut();
            }
        });

        Undotem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();
                currentTab.getEditArea().undo();
            }
        });
        AllItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TabPanel currentTab = (TabPanel) tabbedPane.getSelectedComponent();
                currentTab.getEditArea().selectAll();
            }
        });
    }

    private void ToolAction() {
        JButton newBtn = toolBar.getNewBtn();
        JButton openBtn = toolBar.getOpenBtn();
        JButton saveBtn = toolBar.getSaveBtn();
        JButton feedbackBtn = toolBar.getFeedbackBtn();
        newBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewTab();
            }
        });
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            String filename = f.getName().toLowerCase();
                            return filename.endsWith(".md") || filename.endsWith(".markdown");
                        }
                    }

                    @Override
                    public String getDescription() {
                        return "Markdown文件(*.md)";
                    }
                });
                fileChooser.setAcceptAllFileFilterUsed(false);

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
                    TabPanel newTab = addNewTab(FileUtils.getFileNameNoEx(file.getName()));
                    pathMap.put(newTab, file.getAbsolutePath());//打开的时候
                    storager.setFile(file);
                    newTab.setText(storager.open());
                }

            }
        });
        saveBtn.addActionListener(new ActionListener() {
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
        feedbackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "感谢您的反馈，请将您的反馈信息发送至邮箱wjc133@elt-group.com，谢谢您的支持！",
                        "反馈", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    private void closeOtherTabs(int index) {
        if (index >= 0) {
            int k = 0;
            int count = tabbedPane.getTabCount();
            for (int i = 0; i < count; i++) {
                if (i != index) {
                    tabbedPane.removeTabAt(k);
                } else {
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
        progressDialog = new ProgressDialog(frame);

        tabbedPane = new JTabbedPane();
        addNewTab();
    }

    public void addTab(TabPanel tab) {
        tabbedPane.add(tab);
    }

    private TabPanel addNewTab() {
        return addNewTab(null);
    }

    private TabPanel addNewTab(String name) {
        TabPanel tabPanel = TabPanel.createTabPanel();
        tabPanel.getEditArea().setListener(new EditArea.EditorListener() {
            @Override
            public void onImageUploadBegin() {
                progressDialog.show("正在上传图片");
            }

            @Override
            public void onImageUploadFinished() {
                progressDialog.setVisible(false);
            }
        });
        if (StringUtils.isEmpty(name)) {
            if (i == 1) {
                tabbedPane.addTab("新文档", tabPanel);
            } else {
                tabbedPane.addTab("新文档" + "(" + i + ")", tabPanel);
            }
        } else {
            tabbedPane.addTab(name, tabPanel);
        }
        tabbedPane.setSelectedIndex(i - 1);
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
