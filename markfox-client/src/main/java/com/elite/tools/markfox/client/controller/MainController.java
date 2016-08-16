package com.elite.tools.markfox.client.controller;

import com.elite.tools.markfox.client.ui.MainView;
import com.elite.tools.markfox.client.ui.View;
import com.elite.tools.markfox.common.FileStorager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by wjc133
 * Date: 2016/8/15
 * Time: 17:50
 * 主页面行为控制器
 */
public class MainController extends AutoBindController{
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    private File file;
    private FileStorager storager = new FileStorager();

    private JFrame mainFrame;

    public MainController(View view) {
        super(view);
    }

//    public void temp() {
//        bindAction("saveItem",new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String path = null;
//                JFileChooser fileChooser = new JFileChooser();
//
//                if (file != null) {
//                    path = file.getAbsolutePath();
//                }
//                if (path == null) {
//                    int result = fileChooser.showSaveDialog(frame);
//                    if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
//                        file = fileChooser.getSelectedFile(); // 得到选择的文件
//                        LOG.debug("file choose：{}", file.getName());
//                    } else if (result == JFileChooser.CANCEL_OPTION) {
//                        LOG.debug("file chooser canceled");
//                    }
//                }
//
//                if (file != null) {
////                    storager.setFile(file).save(text1.getText());
//                }
//            }});
//
//        openItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setApproveButtonText("确定");
//                fileChooser.setDialogTitle("打开文件");
//                int result = fileChooser.showOpenDialog(frame);
//                if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
//                    file = fileChooser.getSelectedFile(); // 得到选择的文件
//                    LOG.debug("file choose：{}", file.getName());
//                } else if (result == JFileChooser.CANCEL_OPTION) {
//                    LOG.debug("file chooser canceled");
//                }
//                if (file != null) {
////                    text1.setText(""); // 清空
//                    storager.setFile(file);
////                    text1.setText(storager.open());
//                }
//            }
//        });
//
//        saveasItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                File file = null;   //接收文件
//                JFileChooser fileChooser = new JFileChooser();
//
//                int result = fileChooser.showSaveDialog(frame);
//                if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
//                    file = fileChooser.getSelectedFile(); // 得到选择的文件
//                    LOG.debug("file choose：{}", file.getName());
//                } else if (result == JFileChooser.CANCEL_OPTION) {
//                    LOG.debug("file chooser canceled");
//                }
//                if (file != null) {
////                    storager.setFile(file).save(text1.getText());
//                }
//            }
//        });
//        newItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                JScrollPane JSPane1, JSPane2;
//                JTextArea text1, text2;
//
//                Dimension dim = frame.getSize();
//
//                JPanel Panelbig, Panel2;
//                Panelbig = new JPanel();
//                Panelbig.setLayout(new BorderLayout());
//                Panelbig.removeAll();
//                Panelbig.repaint();
//
//                tjPanel.addTab("新文档", Panel2);
//                Panelbig.add(Panel, BorderLayout.NORTH);
//                Panelbig.add(tjPanel);
//                con.add(Panelbig);
//            }
//        });
//    }
}
