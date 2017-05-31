package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.client.util.ScrollBarSyncer;
import com.elite.tools.markfox.parser.MarkdownParser;
import com.elite.tools.markfox.parser.MarkdownParsers;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FrameLoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private EditArea editArea;
    private PreviewPanel previewArea;
    private JScrollPane editScrollPane;
    private long lastPreviewTime = System.currentTimeMillis();
    private static final long WAIT_TIME = 3 * 1000;

    private ScrollBarSyncer syncer;

    private MarkdownParser parser = MarkdownParsers.createFlexmarkParser();

    private Callback callback;

    private TabPanel() {
    }

    private void init() {
        editArea = new EditArea();
        previewArea = new PreviewPanel();
        editScrollPane = new JScrollPane(editArea);
        editArea.setFont(new Font("宋体", Font.BOLD, 16));
        editArea.setLineWrap(true);

        //Layout Manager
        setLayout(new GridLayout(1, 2));
        add(editScrollPane);
        add(previewArea);

        JScrollBar jsb = editScrollPane.getVerticalScrollBar();
        syncer = new ScrollBarSyncer(jsb, previewArea.getBrowserView().getBrowser());

//        browserBarAction();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public static TabPanel createTabPanel() {
        TabPanel panel = new TabPanel();
        panel.init();
        panel.configPreview();
        return panel;
    }

    private void goTop() {
        editArea.setCaretPosition(0);
        editScrollPane.getVerticalScrollBar().setValue(0);
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
        Browser browser = previewArea.getBrowserView().getBrowser();
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onDocumentLoadedInFrame(FrameLoadEvent frameLoadEvent) {
                if (callback != null) {
                    callback.onPreviewAreaReady();
                }
            }
        });
    }

    private void preview() {
        String text = editArea.getText();
        text = text.trim();
        if (StringUtils.isEmpty(text)) {
            return;
        }

        String markdown = parser.parse(text);
        markdown = markdown.replaceAll("\\n", "\\\\n");
        Browser browser = previewArea.getBrowserView().getBrowser();
        browser.executeJavaScript("$('#content').html('" + markdown + "');$('pre').addClass('prettyprint');prettyPrint();");
    }

    public void clear() {
        editArea.setText("");
    }

    public EditArea getEditArea() {
        return editArea;
    }

    public String getText() {
        return editArea.getText();
    }

    public void setText(String text) {
        editArea.setText(text);
        goTop();
    }

//    private void browserBarAction() {
//        final Browser browser = previewArea.getBrowserView().getBrowser();
//        browser.addScriptContextListener(new ScriptContextAdapter() {
//            @Override
//            public void onScriptContextCreated(ScriptContextEvent event) {
//                JSValue window = browser.executeJavaScriptAndReturnValue("window");
//                window.asObject().setProperty("scroller", syncer);
//            }
//        });
//    }

    public interface Callback {
        void onPreviewAreaReady();
    }
}
