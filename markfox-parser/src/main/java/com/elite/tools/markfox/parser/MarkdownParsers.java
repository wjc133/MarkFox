package com.elite.tools.markfox.parser;

/**
 * Created by wjc133
 * Date: 2016/8/17
 * Time: 20:46
 */
public class MarkdownParsers {
    public static MarkdownParser createPegdownParser() {
        return new PegdownParser();
    }

    public static MarkdownParser createSimpleParser() {
        return new MarkdownJParser();
    }

    public static MarkdownParser createFlexmarkParser() {
        return new FlexmarkParser();
    }
}
