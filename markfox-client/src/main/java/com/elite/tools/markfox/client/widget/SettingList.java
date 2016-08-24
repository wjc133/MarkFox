package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.client.ui.SettingView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午12:54
 */
public class SettingList extends JTree {
    private static SettingList INSTANCE;

    private SettingList() {
        super();
        init();
    }

    public static SettingList getInstance() {
        if (INSTANCE == null) {
            synchronized (SettingList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SettingList();
                }
            }
        }
        return INSTANCE;
    }

    private void init() {
        DefaultMutableTreeNode root = buildTreeModel();
        TreeModel treeModel = new DefaultTreeModel(root);
        setModel(treeModel);
        setRootVisible(false);
        setRowHeight(27);
        setFont(new Font("幼圆",Font.BOLD,15));
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    private DefaultMutableTreeNode buildTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        DefaultMutableTreeNode nodeA = new DefaultMutableTreeNode("常规");
        DefaultMutableTreeNode nodeB = new DefaultMutableTreeNode("图片上传");
        DefaultMutableTreeNode nodeC = new DefaultMutableTreeNode("其他");
        root.add(nodeA);
        root.add(nodeB);
        root.add(nodeC);
        return root;
    }
}
