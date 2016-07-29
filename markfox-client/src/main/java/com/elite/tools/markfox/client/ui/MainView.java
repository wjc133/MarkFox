package com.elite.tools.markfox.client.ui;

import com.elite.tools.markfox.common.FileStorager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by wjc133
 * Date: 2016/7/21
 * Time: 13:11
 * 主界面
 */
public class MainView {
    JFrame mainJFrame;
    Container con;
    JPanel Panel= new JPanel();
    JTextArea text1, text2;
    JMenuBar mainMenuBar;
    JMenu fileMenu, editMenu, formatMenu, helpMenu, insertMenu, checkMenu, toolMenu;

    JMenuItem newItem, openItem, saveItem, saveasItem, pageItem, printItem, exitItem;

    JMenuItem undoItem, cutItem, copyItem, pasteItem, findItem, replaceItem, selectallItem;

    JCheckBoxMenuItem wrapItem;
    JMenuItem fontItem;

    JMenuItem helpItem, aboutItem;
    JTabbedPane tjPanel = new JTabbedPane();
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton iconButton1;

    File file = null;
    FileStorager storager = new FileStorager();

    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);

    public MainView() {
        initWindow();

        /**
         * 动态调整窗口大小
         */
        mainJFrame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent state) {
                if (state.getNewState() == 1 || state.getNewState() == 7) {
                    System.out.println("窗口最小化");
                } else if (state.getNewState() == 6) {
                    //重新设定控件尺寸
                    System.out.println("窗口最大化");
                    Dimension dim = mainJFrame.getSize();
                    int w = (int) (dim.getWidth() / 2);
                    int h = (int) dim.getHeight();
                    text1.setSize(w, h);
                } else if (state.getNewState() == 0) {
                    //重新设定控件尺寸
                    Dimension dim = mainJFrame.getSize();
                    int w = (int) (dim.getWidth() / 2);
                    int h = (int) dim.getHeight();
                    text1.setSize(w, h);
                    System.out.println("窗口恢复到初始状态");
                }
            }
        });



        /**
         * 实现保存功能
         */
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = null;
                JFileChooser fileChooser = new JFileChooser();

                if (file != null) {
                    path = file.getAbsolutePath();
                }
                if (path == null) {
                    int result = fileChooser.showSaveDialog(mainJFrame);
                    if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
                        file = fileChooser.getSelectedFile(); // 得到选择的文件
                        LOG.debug("file choose：{}", file.getName());
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        LOG.debug("file chooser canceled");
                    }
                }

                if (file != null) {
                    storager.setFile(file).save(text1.getText());
                }
            }
        });
        /**
         * 实现打开功能
         */

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setApproveButtonText("确定");
                fileChooser.setDialogTitle("打开文件");
                int result = fileChooser.showOpenDialog(mainJFrame);
                if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
                    file = fileChooser.getSelectedFile(); // 得到选择的文件
                    LOG.debug("file choose：{}", file.getName());
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    LOG.debug("file chooser canceled");
                }
                if (file != null) {
                    text1.setText(""); // 清空
                    storager.setFile(file);
                    text1.setText(storager.open());
                }
            }
        });
        /**
         * 实现另存为功能
         */
        saveasItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;   //接收文件
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showSaveDialog(mainJFrame);
                if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
                    file = fileChooser.getSelectedFile(); // 得到选择的文件
                    LOG.debug("file choose：{}", file.getName());
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    LOG.debug("file chooser canceled");
                }
                if (file != null) {
                    storager.setFile(file).save(text1.getText());
                }
            }
        });
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JScrollPane JSPane1, JSPane2;
                JTextArea text1, text2;

                Dimension dim = mainJFrame.getSize();
                con = mainJFrame.getContentPane();
                int w = (int) (dim.getWidth() / 2);
                int h = (int) dim.getHeight();
                text1=new JTextArea();
                text1.setFont(new Font("宋体", Font.BOLD, 16));
                text1.setLineWrap(true);
                text2=new JTextArea();
                text1.setSize(w, h);
                JPanel Panelbig,Panel2;
                Panelbig = new JPanel();
                Panel2 =new JPanel();
                Panelbig.setLayout(new BorderLayout());
                Panelbig.removeAll();
                Panelbig.repaint();
                Panel2.setLayout(new BorderLayout());

                JSPane1 = new JScrollPane(text1);
                JSPane2 = new JScrollPane(text2);//这个涉及全局变量。。。。可能有问题
                Panel2.add(JSPane1,BorderLayout.WEST);
                Panel2.add(JSPane2);
                tjPanel.addTab("新文档",Panel2);
                Panelbig.add(Panel, BorderLayout.NORTH);
                Panelbig.add(tjPanel);
                con.add(Panelbig);

            }
        });

    }

    public void initWindow() {
        JScrollPane JSPane1, JSPane2;
        LOG.info("init window invoked...");
        JPanel Panelbig,Paneltj,Panel2;
        mainJFrame = new JFrame("MarkFox");
        mainJFrame.pack();
        mainJFrame.setSize(1000, 500);
        Dimension dim = mainJFrame.getSize();

        Panelbig = new JPanel();
        Panel2 =new JPanel();
        Panel2.setLayout(new BorderLayout());
        Panelbig.setLayout(new BorderLayout());
        Panelbig.removeAll();
        Panelbig.repaint();
        int w = (int) (dim.getWidth() / 2);
        int h = (int) dim.getHeight();
        con = mainJFrame.getContentPane();

        text1 = new JTextArea();
        text1.setFont(new Font("宋体", Font.BOLD, 16));
        text1.setLineWrap(true);
        text1.setSize(w, h);

        text2 = new JTextArea();
        text2.setFont(new Font("宋体", Font.BOLD, 16));
        text2.setLineWrap(true);
        text2.setSize(w, h);

        JSPane1 = new JScrollPane(text1);
        JSPane2 = new JScrollPane(text2);
        Panel2.add(JSPane1,BorderLayout.WEST);
        Panel2.add(JSPane2);

        tjPanel.addTab("新文档",Panel2);

      //  Panelbig.add(JSPane1, BorderLayout.WEST);
      //  Panelbig.add(JSPane2);
        Panelbig.add(Panel, BorderLayout.NORTH);
        Panelbig.add(tjPanel);
        con.add(Panelbig);

        //化菜单
        createMenu();
        //添加选项
        tool();
        try {
            mainJFrame.setIconImage(ImageIO.read(MainView.class.getClassLoader().getResource("icon/logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainJFrame.setJMenuBar(mainMenuBar);
        mainJFrame.setVisible(true);
        //设置关闭按钮响应
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void tool() {
        JButton button1 = new JButton("B ");
        JButton button2 = new JButton("I ");
        JButton button3 = new JButton("H1");
        JButton button4 = new JButton("H2");
        JButton iconButton1 = new JButton();
        button1.setSize(15, 15);
        button2.setSize(15, 15);
        button3.setSize(15, 15);
        button4.setSize(15, 15);
        iconButton1.setSize(15, 15);

        URL image = MainView.class.getClassLoader().getResource("icon/file.jpg");
        iconButton1.setIcon(new ImageIcon(image));

        JToolBar bar = new JToolBar();

        bar.add(button1);
        bar.add(button2);
        bar.add(button3);
        bar.add(button4);
        bar.add(iconButton1);

        BorderLayout bord = new BorderLayout();
        Panel.setLayout(bord);
        //Panel.add("North",bar);
        Panel.add(bar);

    }

    public void createMenu() {
        mainMenuBar = new JMenuBar();
        fileMenu = new JMenu("文件");
        editMenu = new JMenu("编辑");
        formatMenu = new JMenu("格式");
        insertMenu = new JMenu("插入");
        checkMenu = new JMenu("查看");
        toolMenu = new JMenu("工具");
        helpMenu = new JMenu("帮助");

        mainMenuBar.add(fileMenu);
        newItem = new JMenuItem("新建");
        openItem = new JMenuItem("打开");
        saveItem = new JMenuItem("保存");
        saveasItem = new JMenuItem("另存为");
        pageItem = new JMenuItem("页面");
        printItem = new JMenuItem("打印");
        exitItem = new JMenuItem("退出");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveasItem);
        fileMenu.addSeparator();
        fileMenu.add(pageItem);
        fileMenu.add(printItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);


        mainMenuBar.add(editMenu);
        undoItem = new JMenuItem("撤销");
        cutItem = new JMenuItem("剪切");
        copyItem = new JMenuItem("复制");
        pasteItem = new JMenuItem("粘贴");
        findItem = new JMenuItem("查找");
        replaceItem = new JMenuItem("替换");
        selectallItem = new JMenuItem("全选");
        editMenu.add(undoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(findItem);
        editMenu.add(replaceItem);
        editMenu.addSeparator();
        editMenu.add(selectallItem);

        mainMenuBar.add(insertMenu);
        mainMenuBar.add(checkMenu);
        mainMenuBar.add(toolMenu);

        mainMenuBar.add(formatMenu);
        wrapItem = new JCheckBoxMenuItem("换行");
        fontItem = new JMenuItem("字体");
        formatMenu.add(wrapItem);
        formatMenu.add(fontItem);

        mainMenuBar.add(helpMenu);
        helpItem = new JMenuItem("查看帮助");
        aboutItem = new JMenuItem("关于");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);
    }

}
