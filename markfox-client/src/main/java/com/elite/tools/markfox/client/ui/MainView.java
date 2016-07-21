package com.elite.tools.markfox.client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/7/21
 * Time: 13:11
 * 主界面
 */
public class MainView {
    JFrame mainJFrame;
    Container con;
    //JPanel Panel;
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
        mainJFrame = new JFrame("MarkFox");
        con = mainJFrame.getContentPane();
        con.setLayout(new BorderLayout());

        text1 = new JTextArea(10, 48);
        text1.setTabSize(4);
        text1.setFont(new Font("宋体", Font.BOLD, 16));
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);

        text2 = new JTextArea(10, 49);
        text2.setFont(new Font("宋体", Font.BOLD, 16));
        text2.setLineWrap(true);
        text2.setWrapStyleWord(true);
        //Panel=new JPanel();

        JSPane1 = new JScrollPane(text1);
        JSPane2 = new JScrollPane(text2);
        //JSPane1.add(text1);
        //JSPane2.add(text2);
        //Panel.add(JSPane1,BorderLayout.WEST);
        //Panel.add(JSPane2,BorderLayout.EAST);

        text1.setBounds(12, 27, 425, 476);
        text2.setBounds(443, 27, 423, 476);
        con.add(JSPane1, BorderLayout.WEST);
        con.add(JSPane2, BorderLayout.EAST);

        createMenu();

        mainJFrame.setJMenuBar(mainMenuBar);
        //	mainJFrame.add(Panel,BorderLayout.CENTER);
        mainJFrame.pack();
        //con.add(Panle,BorderLayout.CENTER);
        //con.add(JSPane2);

        mainJFrame.setSize(894, 533);
        mainJFrame.setVisible(true);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
