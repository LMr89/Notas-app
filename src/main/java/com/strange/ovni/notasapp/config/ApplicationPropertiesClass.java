/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mrhazard
 */
public class ApplicationPropertiesClass {

    private final String PROPERTY_FILE_NAME = "notasApp.properties";
    private Properties properties;
    InputStream streamPropertyFile;
   // FileWriter writer;

    public ApplicationPropertiesClass() {
        verifyPropertyExisting();
        properties = new Properties();
        loadFileProperties();
        
        

    }
    
    private void loadFileProperties(){
        try(FileInputStream streamPropertyFile = new FileInputStream(PROPERTY_FILE_NAME)) {
            
            
            this.properties.load(streamPropertyFile);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "PropertiesFIle", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public ApplicationPropertiesClass addProperty(String key,String value){
        this.properties.setProperty(key, value);
        return this;
        
    }
    
    public String getProperty(String key){
        return this.properties.getProperty(key);
    }
    
    public void saveAllPropertiesChanged(){
        try {
            this.properties.store(new FileWriter(PROPERTY_FILE_NAME), PROPERTY_FILE_NAME);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPropertiesClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Método que se encarga de crear el archivo properties en caso este no exista
    private void verifyPropertyExisting() {
         
        File f = new File(PROPERTY_FILE_NAME);

        try {
            if (f.exists() == false) {
                f.createNewFile();
            }

        } catch (IOException ex) {
            Logger.getLogger(ApplicationPropertiesClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
