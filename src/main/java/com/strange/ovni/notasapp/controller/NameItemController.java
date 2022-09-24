/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.controller;

import com.strange.ovni.notasapp.util.ResizeImage;
import com.strange.ovni.notasapp.util.UrlImages;
import com.strange.ovni.notasapp.view.NameItemDialog;
import com.strange.ovni.notasapp.view.Notifications;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author mrhazard
 */
public class NameItemController implements ActionListener{
    private NameItemDialog dialogItem;
    private String userInput;

    public NameItemController(NameItemDialog dialogItem) {
        this.dialogItem = dialogItem;
        this.userInput = "";
        init();
    }
    
    private void init() {
        this.dialogItem.getBtnAplicarNombre().addActionListener(this);
        this.dialogItem.getBtnCancelar().addActionListener(this);
        
        addIconsToButtons();
    }

    public String getUserInput() {
        return userInput;
    }
    
    
    public void closeDialog(){
        this.dialogItem.setVisible(false);
    }
    
    public void showDialog(){
        this.dialogItem.setVisible(true);
    }
    
     private void addIconsToButtons() {
        this.dialogItem.getBtnAplicarNombre().setIcon(ResizeImage.resize(UrlImages.APPLY_ICON, 25, 25));
        this.dialogItem.getBtnCancelar().setIcon(new ImageIcon(UrlImages.CANCEL_ICON));
    }
    
    void cleanUserInput(){
        this.dialogItem.getTxtNombreItem().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.dialogItem.getBtnAplicarNombre()) {
            String userInputString = this.dialogItem.getTxtNombreItem().getText();
            if (!(userInputString.isBlank()) ||!(userInputString.isEmpty()) ) {
                userInput = userInputString;
                closeDialog();
                
            }else{
                Notifications.showWarning("Porfavor ingresa un nombre correcto");
            }
            
            cleanUserInput();
            
        }
        if (e.getSource() == this.dialogItem.getBtnCancelar()) {
            cleanUserInput();
            closeDialog();
        }
    }

   

    
    
    
    
    
    
}
