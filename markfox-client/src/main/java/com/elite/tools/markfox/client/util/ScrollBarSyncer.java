package com.elite.tools.markfox.client.util;

import com.teamdev.jxbrowser.chromium.Browser;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * 滚动条滚动状态同步器
 *
 * @author wjc133
 */
public class ScrollBarSyncer {
    private JScrollBar bar;
    private Browser browser;
    private AdjustmentListener listener;

    private boolean bind = true;

    public ScrollBarSyncer(JScrollBar bar, Browser browser) {
        this.bar = bar;
        this.browser = browser;
        init();
    }

    private void init() {
        listener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (bind) {
                    int barValue = bar.getValue();//滑动距离
                    int height = bar.getMaximum() - bar.getVisibleRect().height;//总长度
                    float percent = (float) barValue / height;
                    browser.executeJavaScript("window.scrollTo(0," +
                            percent + " * document.body.scrollHeight);");
                }
            }
        };
        bar.addAdjustmentListener(listener);
    }

    public void sync(float percent) {
        int height = bar.getMaximum() - bar.getVisibleRect().height;//总长度
        int barValue = (int) (height * percent);//计算出的滑动距离
        bar.setValue(barValue);
    }

    public void bindAction() {
        bind = true;
    }

    public void unbindAction() {
        bind = false;
    }

    public boolean isScrolling() {
        return bar.getValueIsAdjusting();
    }


}
