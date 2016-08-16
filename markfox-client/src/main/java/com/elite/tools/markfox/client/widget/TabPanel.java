package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.parser.MarkdownProcessor;
import com.teamdev.jxbrowser.chromium.demo.JxBrowserDemo;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by wjc133
 * Date: 2016/8/15
 * Time: 17:44
 * 多标签页的基础内容
 */
public class TabPanel extends JPanel {
    private static final Logger LOG = LoggerFactory.getLogger(TabPanel.class);

    private JTextArea editArea;
    private BrowserView previewArea;

    MarkdownProcessor processor = new MarkdownProcessor();

    private TabPanel() {
    }

    private void init() {
        editArea = new JTextArea();
        previewArea = new JxBrowserDemo().getBrowserView();

        editArea.setFont(new Font("宋体", Font.BOLD, 16));
        editArea.setLineWrap(true);

        //Layout Manager
        setLayout(new GridLayout(1, 2));
        add(new JScrollPane(editArea));
        add(previewArea);
    }

    public static TabPanel createTabPanel() {
        TabPanel panel = new TabPanel();
        panel.init();
        panel.configPreview();
        return panel;
    }

    private void configPreview() {
        editArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                LOG.debug("editArea document inserted!");
                preview();
            }

            public void removeUpdate(DocumentEvent e) {
                LOG.debug("editArea document removed!");
                preview();
            }

            public void changedUpdate(DocumentEvent e) {
                LOG.debug("editArea document changed!");
            }
        });
    }

    private void preview() {
        String markdown = processor.markdown(editArea.getText());
        previewArea.getBrowser().loadHTML(markdown);
    }

    public void clear() {
        editArea.setText("");
    }

    public String getText() {
        return editArea.getText();
    }

    public void setText(String text) {
        editArea.setText(text);
    }
}
