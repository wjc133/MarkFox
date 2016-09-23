package com.elite.tools.markfox.client.widget;

import com.elite.tools.markfox.common.AppBase;
import com.elite.tools.markfox.uploader.CheveratoUploader;
import com.elite.tools.markfox.uploader.Uploader;
import com.elite.tools.markfox.uploader.constants.UploadError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * Created by wjc133
 * Date: 2016/8/19
 * Time: 13:17
 * 可自动上传图片的EditArea
 */
public class EditArea extends JTextArea {
    private static final Logger LOG = LoggerFactory.getLogger(EditArea.class);
    private CheveratoUploader uploader = new CheveratoUploader();
    private UndoManager undoManager = new UndoManager();
    private EditorListener listener;

    public EditArea() {
        super();
        getDocument().addUndoableEditListener(undoManager);
    }

    public void setListener(EditorListener listener) {
        this.listener = listener;
    }

    @Override
    public void paste() {
        if (isEditable() && isEnabled()) {
            Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable cc = sysClip.getContents(null);
            try {
                if (AppBase.getConf().getPic().isEnabled() && cc != null && cc.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    Image img = (Image) cc.getTransferData(DataFlavor.imageFlavor);
                    uploader.setWebsite(AppBase.getConf().getPic().getWebsite());

                    uploader.asyncUpload(img, new Uploader.UploadListener() {
                        @Override
                        public void onUploadBegin() {
                            if (listener != null) {
                                listener.onImageUploadBegin();
                            }
                        }

                        @Override
                        public void onUploadFinished(String url) {
                            if (StringUtils.isEmpty(url)) {
                                return;
                            }
                            String md = "![](" + url + ")";
                            int pos = getCaretPosition();
                            insert(md, pos);
                            setCaretPosition(pos + 2);
                            if (listener != null) {
                                listener.onImageUploadFinished();
                            }
                        }

                        @Override
                        public void onUploadError(UploadError error) {
                            LOG.warn("upload error");
                        }
                    });

//                    String url = uploader.upload(img, AppBase.getConf().getPic().getTimeout(), TimeUnit.MILLISECONDS);
//                    if (StringUtils.isEmpty(url)) {
//                        return;
//                    }
//                    String md = "![](" + url + ")";
//                    int pos = getCaretPosition();
//                    insert(md, pos);
//                    setCaretPosition(pos + 2);
                } else {
                    super.paste();
                }
            } catch (Exception e) {
                LOG.error("paste error: {}", e);
            }
        }
    }

    public void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    public void redo() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }

    public interface EditorListener {
        void onImageUploadBegin();

        void onImageUploadFinished();
    }
}
