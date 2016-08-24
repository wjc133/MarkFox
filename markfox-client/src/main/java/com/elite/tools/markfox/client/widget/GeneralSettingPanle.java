package com.elite.tools.markfox.client.widget;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午8:07
 */
public class GeneralSettingPanle extends SettingPanle{
    private JLabel title;
    private JLabel temp;

    public GeneralSettingPanle() {
       init();
    }

    private void init() {
        title = new JLabel("通用");
        temp = new JLabel("敬请期待");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(temp, BorderLayout.CENTER);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
//        add(title);
        add(panel);
    }

    @Override
    public void apply() {

    }
}
