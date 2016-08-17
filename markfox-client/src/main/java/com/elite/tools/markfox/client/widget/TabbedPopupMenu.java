package com.elite.tools.markfox.client.widget;

import javax.swing.*;

/**
 * Created by wjc133
 * Date: 2016/8/17
 * Time: 19:44
 */
public class TabbedPopupMenu extends JPopupMenu {
    private JMenuItem closeItem;
    private JMenuItem closeOthersItem;
    private JMenuItem closeAllItem;

    private static final TabbedPopupMenu INSTANCE = new TabbedPopupMenu();

    private TabbedPopupMenu() {
        init();
    }

    public static TabbedPopupMenu getInstance() {
        return INSTANCE;
    }

    public void init() {
        closeItem = new JMenuItem("关闭当前页");
        closeOthersItem = new JMenuItem("关闭其他页");
        closeAllItem = new JMenuItem("关闭所有页");

        this.add(closeItem);
        this.add(closeOthersItem);
        this.add(closeAllItem);
    }

    public JMenuItem getCloseItem() {
        return closeItem;
    }

    public JMenuItem getCloseOthersItem() {
        return closeOthersItem;
    }

    public JMenuItem getCloseAllItem() {
        return closeAllItem;
    }
}
