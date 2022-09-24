/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.util;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author mrhazard
 */
public enum TreeIcons {
    
    OPEN_FOLDER(new ImageIcon(UrlImages.OPEN_FOLDER_ICON), "open_folder"),
    CLOSE_FOLDER(new ImageIcon(UrlImages.CLOSE_FOLDER_ICON), "open_folder"),
    NOTE(new ImageIcon(UrlImages.NOTE_ICON),"nota"),
     IMAGE(new ImageIcon(UrlImages.IMAGE_ICON),"nota");
    
    private final Icon ico;
    private final String type;

    private TreeIcons(Icon ico, String type) {
        this.ico = ico;
        this.type = type;
    }

    public Icon getIco() {
        return ico;
    }

    public String getType() {
        return type;
    }
    
    
    
    
  
}
