package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.common.AppBase;
import com.elite.tools.markfox.common.settings.PicSettings;
import com.elite.tools.markfox.common.settings.Settings;
import com.elite.tools.markfox.uploader.WebSite;
import com.elite.tools.markfox.uploader.WebSites;
import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 上午11:58
 */
public class PicSettingPanle extends SettingPanle {
    private JLabel title;
    private JLabel enableLabel;
    private JCheckBox enableCheckBox;
    private JLabel timeoutLabel;
    private JEditorPane timeoutEdit;
    private JLabel websiteLabel;
    private JComboBox websiteBox;
    private Map<String, WebSite> webSiteMap;

    public PicSettingPanle() {
        init();
    }

    private void init() {
        title = new JLabel("图片上传");
        enableLabel = new JLabel("自动上传:");
        enableCheckBox = new JCheckBox("启用上传");
        timeoutLabel = new JLabel("超时时间设置:");
        timeoutEdit = new JEditorPane();
        websiteLabel = new JLabel("选择图床:");
        websiteBox = new JComboBox();
        webSiteMap = WebSites.INSTANCE.getWebSiteMap();
        for (String site : webSiteMap.keySet()) {
            websiteBox.addItem(site);
        }

        JPanel container = new JPanel(new GridLayout(3, 2));
        container.add(enableLabel);
        container.add(enableCheckBox);
        container.add(timeoutLabel);
        container.add(timeoutEdit);
        container.add(websiteLabel);
        container.add(websiteBox);

//        add(title);
        add(container);
        loadData();
    }

    public void loadData() {
        PicSettings pic = AppBase.getConf().getPic();
        enableCheckBox.setSelected(pic.isEnabled());
        timeoutEdit.setText(String.valueOf(pic.getTimeout()));
        websiteBox.setSelectedItem(pic.getWebsite());
    }

    @Override
    public void apply() {
        Settings conf = AppBase.getConf();
        PicSettings pic = conf.getPic();
        pic.setEnabled(enableCheckBox.isSelected());
        pic.setWebsite((String) websiteBox.getSelectedItem());
        pic.setTimeout(NumberUtils.toInt(timeoutEdit.getText().trim(), 5));
    }
}
