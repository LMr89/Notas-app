/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.model.dao;

import com.strange.ovni.notasapp.model.interfaces.INote;
import com.strange.ovni.notasapp.model.entities.Note;
import com.strange.ovni.notasapp.view.Notifications;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author mrhazard
 */
public class NoteImpl  implements INote{
    private String title;
    private String content;
    private String fullPath;
    private File workFile;
    private File fileToRename;

    private Boolean isNoteWriteenSuccessfully;

    public NoteImpl(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteImpl() {
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        workFile = new File(fullPath);
        fileToRename = new File(fullPath);//Por mientras lo instancio a la ruta que me mandan para no ocasionar NullPointer
        this.fullPath = fullPath;
    }
    
    

    public String getTitle() {
        int extensionPosition = workFile.getName().lastIndexOf(".");
        this.title = workFile.getName().substring(0, extensionPosition);
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    

    @Override
    public String readNote() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(this.fullPath)))) {
            this.content = "";
            String currentRead= "";
            while ((currentRead = reader.readLine()) != null) {
               
              this.content +=currentRead+"\n";
            }
          
        } catch (Exception e) {
            Notifications.showWarning(e.getMessage());
        }
        
        return content;
    }

    @Override
    public Boolean writeNote(String data) {
        isNoteWriteenSuccessfully = false;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.fullPath),false))) {
            isNoteWriteenSuccessfully = true;
            writer.write(data);
            
        } catch (Exception e) {
            Notifications.showWarning(e.getMessage());
        }
        
        return isNoteWriteenSuccessfully;
    }

    @Override
    public Boolean renameNote(String newFullPath) {
        Boolean successRenamed = false;
        try {
            fileToRename = new File(newFullPath);
            successRenamed = true;
            workFile.renameTo(fileToRename);
        } catch (Exception e) {
             Notifications.showWarning(e.getMessage());
        }
        
        return successRenamed;
    }

    @Override
    public Boolean deleteNote() {
         Boolean successDeleted = false;
        try {
            workFile.delete();
            successDeleted = true;
        } catch (Exception e) {
            Notifications.showWarning(e.getMessage());
        }
        
        return successDeleted;
    }
    
}
