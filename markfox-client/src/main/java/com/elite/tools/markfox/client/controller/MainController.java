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

}
