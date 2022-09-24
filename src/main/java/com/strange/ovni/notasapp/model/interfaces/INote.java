/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.model.interfaces;

/**
 *
 * @author mrhazard
 */
public interface INote {
    public String readNote();
    public Boolean writeNote( String data);
    public Boolean renameNote(String newName);
    public Boolean deleteNote();
    
}
