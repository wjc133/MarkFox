package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.parser.MarkdownParser;
import com.elite.tools.markfox.parser.MarkdownParsers;
import com.teamdev.jxbrowser.chromium.demo.JxBrowserDemo;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by wjc133
 * Date: 2016/8/15
 * Time: 17:44
 * 多标签页的基础内容
 */
public class TabPanel extends JPanel {
    private static final Logger LOG = LoggerFactory.getLogger(TabPanel.class);

    private EditArea editArea;
    private BrowserView previewArea;

    MarkdownParser parser = MarkdownParsers.createPegdownParser();

    private TabPanel() {
    }

    private void init() {
        editArea = new EditArea();
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
//        panel.configEditor();
        panel.configPreview();
        return panel;
    }

    private void configEditor() {
        editArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
                    System.out.println("Hello World");
                }
            }
        });
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
        String markdown = parser.parse(editArea.getText());
        StringBuilder html = new StringBuilder();
        html.append("<html><head>");
//        html.append("E:/Work/MarkFox/markfox-client/src/main/resources/style/default.css");
        html.append("<link rel=\"stylesheet\" href=\"http://kevinburke.bitbucket.org/markdowncss/markdown.css\"></head>");
        html.append("<link rel=\"stylesheet\" href=\"http://apps.bdimg.com/libs/prettify/r298/prettify.min.css\"></head>");
        html.append("<body>");
        html.append(markdown);
        //add js
        html.append("<script src=\"http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js\"></script>");
        html.append("<script src=\"http://apps.bdimg.com/libs/prettify/r298/prettify.min.js\"></script>");
        html.append("<script>$(window).load(function(){$(\"pre\").addClass(\"prettyprint\");prettyPrint();})</script>");
        html.append("</body></html>");
        previewArea.getBrowser().loadHTML(html.toString());
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
