package com.elite.tools.markfox.parser;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.Arrays;

/**
 * @author wjc133
 */
public class FlexmarkParser implements MarkdownParser {
    private Parser parser;
    private HtmlRenderer renderer;

    FlexmarkParser() {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));
        options.set(HtmlRenderer.SOFT_BREAK, "<br/>"); //解决换行问题
        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    public String parse(String markdown) {
        // Standardize line endings:
        markdown = markdown.replaceAll("\\r\\n", "\n");    // DOS to Unix
        markdown = markdown.replaceAll("\\r", "\n");        // Mac to Unix
        markdown = markdown.replaceAll("^[ \\t]+$", "");

//        markdown = markdown.replaceAll("\\n", "\n\n");  //解决换行问题
        // FIXME: 2017/5/31 最好能解决单行换行问题
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
}
