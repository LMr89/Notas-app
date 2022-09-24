/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.util;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author mrhazard
 */
public class ResizeImage {
    private String imagePath;
    private static ImageIcon resizeImage;
 
    
    public static ImageIcon resize(String imgPath,int width, int height){
        resizeImage = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return resizeImage;
        
    }
    
    
    
    
    
}
