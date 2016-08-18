package com.elite.tools.markfox.client.widget;

import javax.swing.*;

/**
 * Created by wjc133.
 * Date: 16/8/15
 * Time: 下午10:37
 */
public class MainMenu extends JMenuBar {
    private static final MainMenu INSTANCE = new MainMenu();

    private MainMenu() {
        init();
    }

    public static MainMenu getInstance() {
        return INSTANCE;
    }

    private void init() {
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu formatMenu = new JMenu("格式");
        JMenu insertMenu = new JMenu("插入");
        JMenu viewMenu = new JMenu("查看");
        JMenu toolMenu = new JMenu("工具");
        JMenu helpMenu = new JMenu("帮助");

        createFileMenu(fileMenu);
        createEditMenu(editMenu);
        createFormatMenu(formatMenu);
//        createInsertMenu(insertMenu);
//        createViewMenu(viewMenu);
//        createToolMenu(toolMenu);
        createHelpMenu(helpMenu);
    }

    private void createFileMenu(JMenu fileMenu) {
        add(fileMenu);
        JMenuItem newItem = new JMenuItem("新建");
        JMenuItem openItem = new JMenuItem("打开");
        JMenuItem saveItem = new JMenuItem("保存");
        JMenuItem saveasItem = new JMenuItem("另存为");
        JMenuItem pageItem = new JMenuItem("页面");
        JMenuItem printItem = new JMenuItem("打印");
        JMenuItem exitItem = new JMenuItem("退出");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveasItem);
        fileMenu.addSeparator();
        fileMenu.add(pageItem);
        fileMenu.add(printItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
    }

    private void createEditMenu(JMenu editMenu) {
        add(editMenu);
        JMenuItem undoItem = new JMenuItem("撤销");
        JMenuItem cutItem = new JMenuItem("剪切");
        JMenuItem copyItem = new JMenuItem("复制");
        JMenuItem pasteItem = new JMenuItem("粘贴");
        JMenuItem findItem = new JMenuItem("查找");
        JMenuItem replaceItem = new JMenuItem("替换");
        JMenuItem selectallItem = new JMenuItem("全选");
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
    }

    private void createInsertMenu(JMenu insertMenu) {
        add(insertMenu);
    }

    private void createViewMenu(JMenu viewMenu) {
        add(viewMenu);
    }

    private void createToolMenu(JMenu toolMenu) {
        add(toolMenu);
    }

    private void createFormatMenu(JMenu formatMenu) {
        add(formatMenu);
        JCheckBoxMenuItem wrapItem = new JCheckBoxMenuItem("换行");
        JMenuItem fontItem = new JMenuItem("字体");
        formatMenu.add(wrapItem);
        formatMenu.add(fontItem);
    }

    private void createHelpMenu(JMenu helpMenu) {
        add(helpMenu);
        JMenuItem helpItem = new JMenuItem("查看帮助");
        JMenuItem aboutItem = new JMenuItem("关于");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);
    }
}
