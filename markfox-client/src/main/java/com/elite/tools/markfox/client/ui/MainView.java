package com.elite.tools.markfox.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 * Created by wjc133
 * Date: 2016/7/21
 * Time: 13:11
 * 主界面
 */
public class MainView {
    JFrame mainJFrame;
    Container con;
    JPanel Panel,Panel1;
    JScrollPane JSPane1, JSPane2;
    JTextArea text1, text2;
    JMenuBar mainMenuBar;
    JMenu fileMenu, editMenu, formatMenu, helpMenu, insertMenu, checkMenu, toolMenu;

    JMenuItem newItem, openItem, saveItem, saveasItem, pageItem, printItem, exitItem;

    JMenuItem undoItem, cutItem, copyItem, pasteItem, findItem, replaceItem, selectallItem;

    JCheckBoxMenuItem wrapItem;
    JMenuItem fontItem;

    JMenuItem helpItem, aboutItem;

    public MainView() {
        initWindow();

        mainJFrame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent state) {

                if(state.getNewState() == 1 || state.getNewState() == 7) {
                    System.out.println("窗口最小化");
                }else if (state.getNewState() == 6) {
                    //重新设定控件尺寸
                    System.out.println("窗口最大化");
                    Dimension dim = mainJFrame.getSize();
                    int w=(int)(dim.getWidth()/2);
                    int h=(int)dim.getHeight();
                    text1.setSize(w,h);
                }else if(state.getNewState() == 0) {
                    //重新设定控件尺寸
                    Dimension dim = mainJFrame.getSize();
                    int w=(int)(dim.getWidth()/2);
                    int h=(int)dim.getHeight();
                    text1.setSize(w,h);
                    System.out.println("窗口恢复到初始状态");
                }
            }

        });
    }


    public void initWindow()
    {
        System.out.println("进入加载~~~~");
        JPanel Panel1;
        mainJFrame=new JFrame("MarkFox");
        mainJFrame.pack();
        mainJFrame.setSize(1000,500);
        Dimension dim = mainJFrame.getSize();
        Panel=new JPanel();

        Panel1=new JPanel();
        Panel1.setLayout(new BorderLayout());
        Panel1.removeAll();
        Panel1.repaint();
        int w=(int)(dim.getWidth()/2);
        int h=(int)dim.getHeight();
        con=mainJFrame.getContentPane();

        text1=new JTextArea();
        text1.setFont(new Font("宋体",Font.BOLD,16));
        text1.setLineWrap(true);
        text1.setSize(w,h);

        text2=new JTextArea();
        text2.setFont(new Font("宋体",Font.BOLD,16) );
        text2.setLineWrap(true);
        text2.setSize(w,h);

        JSPane1=new JScrollPane(text1);
        JSPane2=new JScrollPane(text2);
        Panel1.add(JSPane1,BorderLayout.WEST);
        Panel1.add(JSPane2);
        Panel1.add(Panel,BorderLayout.NORTH);
        con.add(Panel1);
        mainJFrame.setJMenuBar(mainMenuBar);
        //化菜单
        createMenu();
        //添加选项
        tool();
        mainJFrame.setVisible(true);
        //设置关闭按钮响应
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void tool()
    {
        JButton button1 =new JButton("B ");
        JButton button2 =new JButton("I ");
        JButton button3 =new JButton("H1");
        JButton button4 =new JButton("H2");
        JButton iconButton1 =new JButton();
        button1.setSize(15,15);
        button2.setSize(15,15);
        button3.setSize(15,15);
        button4.setSize(15,15);
        iconButton1.setSize(15,15);

        iconButton1.setIcon(new ImageIcon(MainView.class.getResource("file.jpg")));

        JToolBar bar = new JToolBar();

        bar.add(button1);
        bar.add(button2);
        bar.add(button3);
        bar.add(button4);
        bar.add(iconButton1);

        BorderLayout bord = new BorderLayout();
        Panel.setLayout(bord);
        Panel.add("North",bar);

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
