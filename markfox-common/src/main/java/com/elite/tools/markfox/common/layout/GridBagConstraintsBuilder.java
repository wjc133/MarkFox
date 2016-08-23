package com.elite.tools.markfox.common.layout;

import java.awt.*;

/**
 * Created by wjc133.
 * Date: 16/8/20
 * Time: 下午9:41
 */
public class GridBagConstraintsBuilder {
    private int gridx;
    private int gridy;
    private int gridwidth;
    private int gridheight;
    private double weightx;
    private double weighty;
    private int anchor=GridBagConstraints.CENTER;
    private int fill=GridBagConstraints.BOTH;
    private Insets insets = new Insets(0, 0, 0, 0);
    private int ipadx;
    private int ipady;

    public GridBagConstraintsBuilder position(int x, int y) {
        this.gridx = x;
        this.gridy = y;
        return this;
    }

    public GridBagConstraintsBuilder gridx(int gridx) {
        this.gridx = gridx;
        return this;
    }

    public GridBagConstraintsBuilder gridy(int gridy) {
        this.gridy = gridy;
        return this;
    }

    public GridBagConstraintsBuilder size(int width,int height){
        this.gridwidth = width;
        this.gridheight = height;
        return this;
    }

    public GridBagConstraintsBuilder width(int gridwidth) {
        this.gridwidth = gridwidth;
        return this;
    }

    public GridBagConstraintsBuilder height(int gridheight) {
        this.gridheight = gridheight;
        return this;
    }

    public GridBagConstraintsBuilder weight(double weightx, double weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    public GridBagConstraintsBuilder anchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GridBagConstraintsBuilder fill(int fill) {
        this.fill = fill;
        return this;
    }

    public GridBagConstraintsBuilder insets(Insets insets) {
        this.insets = insets;
        return this;
    }

    public GridBagConstraintsBuilder padding(int ipadx,int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }

    public GridBagConstraints build() {
        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
                weightx, weighty, anchor, fill, insets, ipadx, ipady);
    }
}
