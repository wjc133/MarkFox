package com.elite.tools.markfox.parser;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

/**
 * Created by wjc133
 * Date: 2016/8/17
 * Time: 20:42
 */
public class PegdownParser implements MarkdownParser {
    private PegDownProcessor processor = new PegDownProcessor(Extensions.TABLES | Extensions.FENCED_CODE_BLOCKS, 5000);

    PegdownParser() {

    }

    public String parse(String markdown) {
        // Standardize line endings:
        markdown = markdown.replaceAll("\\r\\n", "\n");    // DOS to Unix
        markdown = markdown.replaceAll("\\r", "\n");        // Mac to Unix
        markdown = markdown.replaceAll("^[ \\t]+$", "");

//        markdown = markdown.replaceAll("\\n", "\n\n");  //解决换行问题
        return processor.markdownToHtml(markdown);
    }
}
