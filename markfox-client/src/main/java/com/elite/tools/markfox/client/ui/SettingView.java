package com.elite.tools.markfox.client.ui;

import com.elite.tools.markfox.client.widget.*;
import com.elite.tools.markfox.common.layout.GridBagConstraintsBuilder;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 上午12:18
 */
public class SettingView extends AbstractView {
    private SettingList settingList;
    private Frame owner;
    private CommonSetting settingContainer;
    private JButton applyBtn;
    private JButton okBtn;
    private JButton cancelBtn;
    private JDialog dialog;

    private static SettingView INSTANCE;

    private SettingView(Frame owner) {
        this.owner = owner;
        init();
        initDialog();
        configListener();
    }

    public static SettingView getInstance(Frame owner) {
        if (INSTANCE == null) {
            synchronized (SettingView.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SettingView(owner);
                }
            }
        }
        return INSTANCE;
    }

    private void configListener() {
        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingContainer.apply();
            }
        });

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingContainer.apply();
                dialog.dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }

    private void initDialog() {
        dialog = new JDialog(owner, "设置", true);
        Container container = dialog.getContentPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(settingList, new GridBagConstraintsBuilder()
                .position(0, 0).size(1, 2).weight(0.2, 1).build());

        settingContainer = new CommonSetting();
        settingContainer.add(new GeneralSettingPanle());
        settingContainer.add(new PicSettingPanle());
        settingContainer.add(new OtherSettingPanle());
        mainPanel.add(settingContainer, new GridBagConstraintsBuilder()
                .position(1, 0).size(1, 1).weight(1, 1).build());

        applyBtn = new JButton("应用");
        okBtn = new JButton("确定");
        cancelBtn = new JButton("取消");
        JPanel btnArea = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnArea.add(applyBtn);
        btnArea.add(okBtn);
        btnArea.add(cancelBtn);
        mainPanel.add(btnArea, new GridBagConstraintsBuilder()
                .position(1, 1).size(1, 1).weight(1, 0).build());

        container.add(mainPanel);
        dialog.setBounds(0, 0, 850, 750);
        dialog.setLocationRelativeTo(owner);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void show(){
        dialog.setVisible(true);
    }

    private void init() {
        settingList = SettingList.getInstance();
    }
}
