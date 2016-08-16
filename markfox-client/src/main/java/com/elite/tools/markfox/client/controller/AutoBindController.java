package com.elite.tools.markfox.client.controller;

import com.elite.tools.markfox.client.ui.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by wjc133.
 * Date: 16/8/15
 * Time: 下午11:57
 */
public abstract class AutoBindController implements Controller {
    private static final Logger LOG = LoggerFactory.getLogger(AutoBindController.class);
    private View view;

    public AutoBindController(View view) {
        this.view = view;
    }

    public void bindAction(String componentName, ActionListener actionListener) {
        JComponent component = view.getComponent(componentName);
        if (component == null) {
            LOG.error("cannot bind action, because no component named {}", componentName);
        }
        if (component instanceof AbstractButton) {
            ((AbstractButton) component).addActionListener(actionListener);
        }
    }

    public void setView(View view) {
        this.view = view;
    }
}
