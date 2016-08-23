package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.common.settings.PicSettings;
import com.elite.tools.markfox.uploader.CheveratoUploader;
import com.elite.tools.markfox.uploader.PicUploader;
import com.elite.tools.markfox.uploader.Uploaders;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 13:17
 */
public class EditArea extends JTextArea {
    private CheveratoUploader uploader = new CheveratoUploader();

    @Override
    public void paste() {
        if (isEditable() && isEnabled()) {
            Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable cc = sysClip.getContents(null);
            try {
                if (cc != null && cc.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    Image img = (Image) cc.getTransferData(DataFlavor.imageFlavor);
                    uploader.setWebsite("");
                    String url = uploader.upload(img);
                    String md = "![](" + url + ")";
                    int pos = getCaretPosition();
                    insert(md, pos);
                    setCaretPosition(pos + 2);
                } else {
                    super.paste();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
