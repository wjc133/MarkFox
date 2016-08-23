package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.client.bootstrap.Application;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午9:31
 */
public class CommonSetting extends JPanel {
    private List<SettingPanle> panles = Lists.newArrayList();

    public CommonSetting() {
        init();
    }

    public void init() {
        setLayout(new CardLayout());
    }

    public void add(SettingPanle panle) {
        panles.add(panle);
        super.add(panle);
    }

    public void apply() {
        if (CollectionUtils.isNotEmpty(panles)) {
            for (SettingPanle panle : panles) {
                panle.apply();
            }
        }
        Application.saveConfig();
    }
}
