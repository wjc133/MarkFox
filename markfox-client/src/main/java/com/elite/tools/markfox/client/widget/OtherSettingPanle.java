package com.elite.tools.markfox.client.widget;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午9:23
 */
public class OtherSettingPanle extends SettingPanle{
    private JLabel title;
    private JLabel temp;

    public OtherSettingPanle() {
        init();
    }

    private void init() {
        title = new JLabel("其他");
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
