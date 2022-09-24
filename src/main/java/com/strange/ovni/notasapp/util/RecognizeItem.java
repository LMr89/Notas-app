/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.util;

import java.io.File;

/**
 *
 * @author mrhazard
 */
public class RecognizeItem {
    private static Boolean isDirectory;
    private static Boolean isFile;
    private static  File workFile;
    
    
    
    public static Boolean isPathADirectory(String path){
        isDirectory = false;
        workFile = new File(path);
        
        if (workFile.isDirectory()) {
            isDirectory = true;
            
        }
        
        return isDirectory;
    }
    
    public static Boolean isPathAFile(String path){
        isFile = false;
        workFile = new File(path);
        
        if (workFile.isFile()) {
            isFile = true;
            
        }
        
        return isFile;
    }
}
