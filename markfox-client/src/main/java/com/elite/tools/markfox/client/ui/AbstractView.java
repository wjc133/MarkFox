package com.elite.tools.markfox.client.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.lang.reflect.Field;

/**
 * Created by wjc133.
 * Date: 16/8/15
 * Time: 下午11:36
 */
public abstract class AbstractView implements View {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractView.class);

    public JComponent getComponent(String name) {
        try {
            Class clz = this.getClass();
            Field component = clz.getField(name);
            if (component.getType().isAssignableFrom(JComponent.class)) {
                return (JComponent) component.get(clz);
            }
        } catch (NoSuchFieldException e) {
            LOG.info("cannot get the component named {}", name);
        } catch (Exception e) {
            LOG.error("get component error:[]",e);
        }
        return null;
    }
}
