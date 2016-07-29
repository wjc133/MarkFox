package com.elite.tools.markfox.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by wjc133
 * Date: 2016/7/29
 * Time: 11:55
 * 文件存储器
 */
public class FileStorager implements Storager {
    private static final Logger LOG = LoggerFactory.getLogger(FileStorager.class);

    private File file;
    private String path;

    public File getFile() {
        if (file == null && path != null) {
            synchronized (this) {
                if (file == null) {
                    file = new File(path);
                }
            }
        }
        return file;
    }

    public FileStorager setFile(File file) {
        this.file = file;
        return this;
    }

    public String getPath() {
        if (path == null && file != null) {
            synchronized (this) {
                if (path == null) {
                    path = file.getAbsolutePath();
                }
            }
        }
        return path;
    }

    public FileStorager setPath(String path) {
        this.path = path;
        return this;
    }

    public String open() {
        StringBuilder content = new StringBuilder();
        try {
            Scanner scan = new Scanner(new FileInputStream(file));
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                content.append(scan.next()).append("\n");
            }
            scan.close();
        } catch (Exception e) {
            LOG.error("open file error:{}", e);
        }
        return content.toString();
    }

    public void save(String content) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(getFile()));
            out.print(content);
            out.close();
        } catch (Exception e) {
            LOG.error("save file error:{}", e);
        }
    }
}
