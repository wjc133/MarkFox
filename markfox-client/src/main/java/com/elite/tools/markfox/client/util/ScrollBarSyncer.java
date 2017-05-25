package com.elite.tools.markfox.client.util;

import javax.swing.*;

/**
 * 滚动条滚动状态同步器
 *
 * @author wjc133
 */
public class ScrollBarSyncer {
    private JScrollBar bar;

    public ScrollBarSyncer(JScrollBar bar) {
        this.bar = bar;
    }

    public void sync(float percent) {
        int height = bar.getMaximum() - bar.getVisibleRect().height;//总长度
        int barValue = (int) (height * percent);//计算出的滑动距离
        bar.setValue(barValue);
    }
}
