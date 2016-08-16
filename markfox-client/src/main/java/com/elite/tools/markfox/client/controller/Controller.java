package com.elite.tools.markfox.client.controller;

import java.awt.event.ActionListener;

/**
 * Created by wjc133.
 * Date: 16/8/15
 * Time: 下午11:53
 */
public interface Controller {
   void bindAction(String componentName, ActionListener actionListener);
}
