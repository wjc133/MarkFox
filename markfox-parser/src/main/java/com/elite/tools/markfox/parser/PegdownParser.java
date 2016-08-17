package com.elite.tools.markfox.parser;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

/**
 * Created by wjc133
 * Date: 2016/8/17
 * Time: 20:42
 */
public class PegdownParser implements MarkdownParser {
    private PegDownProcessor processor = new PegDownProcessor(Extensions.ALL,5000);

    PegdownParser() {

    }

    public String parse(String markdown) {
        return processor.markdownToHtml(markdown);
    }
}
