package com.strange.ovni.notasapp.model.entities;

import com.google.common.io.Files;
import com.strange.ovni.notasapp.util.ResizeImage;
import com.strange.ovni.notasapp.util.UrlImages;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author mrhazard
 */
public class CustomTreeNode {

    private String fileName;
    private String absolutePath;
    private String ico;

    public CustomTreeNode() {

    }

    public CustomTreeNode(String fileName, String absolutePath) {
        this.fileName = fileName;
        this.absolutePath = absolutePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public ImageIcon getIco() {

        switch (Files.getFileExtension(this.fileName)) {

            case "txt":
                ico = UrlImages.NOTE_ICON;
                break;

            case "jpeg":
            case "png":
            case "gif":
            case "jpg":
            case "svg":
            case "webp":
                ico = UrlImages.IMAGE_ICON;
                break;

            case "pdf":
                ico = UrlImages.PDF_ICON;
                break;

            case "docx":
                ico = UrlImages.DOCUMENT_ICON;
                break;

            case "3gp":
            case "flv":
            case "mp4":
                ico = UrlImages.VIDEO_ICON;
                break;

            case "zip":
            case "rar":
                ico = UrlImages.ZIPED_FILE_ICON;
                break;
            case "":
                ico = UrlImages.CLOSE_FOLDER_ICON;
                break;
            default:
                ico = UrlImages.UNDIFINED_ICON;
                break;

        }

        return ResizeImage.resize(ico, 18, 18);
        //return new ImageIcon(new ImageIcon(ico).getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
    }

}
