package com.elite.tools.markfox.parser;

import com.elite.tools.markfox.parser.markdownj.MarkdownProcessor;

/**
 * Created by wjc133
 * Date: 2016/8/17
 * Time: 20:49
 */
public class MarkdownJParser implements MarkdownParser {
    private MarkdownProcessor processor = new MarkdownProcessor();

    MarkdownJParser() {

    }

    public String parse(String markdown) {
        return processor.markdown(markdown);
    }
}
