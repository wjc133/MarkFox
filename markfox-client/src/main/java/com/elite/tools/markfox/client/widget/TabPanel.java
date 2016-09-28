package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.common.utils.ResourceUtils;
import com.elite.tools.markfox.parser.MarkdownParser;
import com.elite.tools.markfox.parser.MarkdownParsers;
import com.teamdev.jxbrowser.chromium.demo.JxBrowserDemo;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
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
    private JScrollPane jScrollPane;
    private long lastPreviewTime = System.currentTimeMillis();
    private static final long WAIT_TIME = 3 * 1000;

    private MarkdownParser parser = MarkdownParsers.createPegdownParser();

    private TabPanel() {
    }

    private void init() {
        editArea = new EditArea();
        previewArea = new JxBrowserDemo().getBrowserView();
        jScrollPane=new JScrollPane(editArea);
        editArea.setFont(new Font("宋体", Font.BOLD, 16));
        editArea.setLineWrap(true);

        //Layout Manager
        setLayout(new GridLayout(1, 2));
        add(jScrollPane);
        add(previewArea);
    }


    public static TabPanel createTabPanel() {
        TabPanel panel = new TabPanel();
        panel.init();
//      panel.configEditor();
        panel.configPreview();
        return panel;
    }

    public void test()
    {
        jScrollPane.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {

            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

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
        String text = editArea.getText();
        if (StringUtils.isEmpty(text.trim())) {
            return;
        }
//        if (System.currentTimeMillis() - lastPreviewTime < WAIT_TIME) {
//            return;
//        }
//        lastPreviewTime = System.currentTimeMillis();

        String markdown = parser.parse(text);
        StringBuilder html = new StringBuilder();
        String Csspath = null;
        String prettifyPath=null;
        String jqueryPath=null;
        String prettifyJsPath=null;

        File cssfile = ResourceUtils.loadFile("style/markdown.css");
        if (cssfile != null && cssfile.exists()) {
            Csspath = cssfile.getAbsolutePath();
        }

        File prettifyfile = ResourceUtils.loadFile("style/MarkFox.css");
        if (prettifyfile != null && prettifyfile.exists()) {
            prettifyPath = prettifyfile.getAbsolutePath();
        }
        File jqueryfile = ResourceUtils.loadFile("style/jquery.js");
        if (jqueryfile != null && jqueryfile.exists()) {
            jqueryPath = jqueryfile.getAbsolutePath();
        }
        File prettifyJsfile = ResourceUtils.loadFile("style/prettify.js");
        if (prettifyJsfile != null && prettifyJsfile.exists()) {
            prettifyJsPath = prettifyJsfile.getAbsolutePath();
        }

        html.append("<html><head>");
        //html.append("<link rel=\"stylesheet\" href=\"http://kevinburke.bitbucket.org/markdowncss/markdown.css\"></head>");
        html.append("<link rel=\"stylesheet\" href=\"").append(Csspath).append("\" type=\"text/css\"></head>");
        html.append("<link rel=\"stylesheet\" href=\"").append(prettifyPath).append("\"type=\"text/css\"></head> ");
        html.append("<body>");
        html.append(markdown);
        //add js
        html.append("<script src=\"").append(jqueryPath).append("\"></script>");
        html.append("<script src=\"").append(prettifyJsPath).append("\"></script>");
       // html.append("<script src=\"http://apps.bdimg.com/libs/prettify/r298/prettify.min.js\"></script>");
        html.append("<script>$(window).load(function(){$(\"pre\").addClass(\"prettyprint\");prettyPrint();})</script>");
        html.append("</body></html>");
        previewArea.getBrowser().loadHTML(html.toString());
    }

    public void clear() {
        editArea.setText("");
    }

    public EditArea getEditArea() {
        return editArea;
    }

    public JScrollPane getjScrollPane()
    {
        return jScrollPane;
    }

    public String getText() {
        return editArea.getText();
    }

    public void setText(String text) {
        editArea.setText(text);
    }
}
