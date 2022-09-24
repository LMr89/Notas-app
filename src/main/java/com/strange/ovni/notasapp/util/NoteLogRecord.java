/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.util;

import com.strange.ovni.notasapp.model.interfaces.INote;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrhazard
 * Esta clase sera la encargada de guardar el contenido de una nota en un archivo log
 * para luego ser leida y escrita en otra nota en caso el usuario lo pida 
 * es como si fuera la accion UNDO para no guardar cambios
 */
public class NoteLogRecord {
    private final static File LOG_FILENAME = new File( "noteApp.log");
    
    public static void createLogIfNotExists(){
        if (!LOG_FILENAME.exists()) {
            try {
                LOG_FILENAME.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(NoteLogRecord.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public static String readLogContent(){
        String content = "", aux="";
        try(BufferedReader reader = new BufferedReader(new FileReader(LOG_FILENAME))) {
            while((aux = reader.readLine()) != null){
                content +=aux;
            }
            
        } catch (Exception e) {
            Logger.getLogger(NoteLogRecord.class.getName()).log(Level.SEVERE, null, e);
        }
        return content;
    }
    
    public static void writeLogContent(String msg){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILENAME,false))) {
            writer.write(msg);
            
        } catch (Exception e) {
            Logger.getLogger(NoteLogRecord.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public static void cleanLog(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILENAME,false))) {
            writer.write("");
            
        } catch (Exception e) {
            Logger.getLogger(NoteLogRecord.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    

   
    
    
    
}
