/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.controller;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.util.UrlImages;
import com.strange.ovni.notasapp.view.DirectoryDialog;
import com.strange.ovni.notasapp.view.Notifications;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author mrhazard
 */
public class DirectoryDialogController implements ActionListener{
    private final  DirectoryDialog dialog;
    private JFrame parent;
    private JFileChooser directoryChooser;
    private NotasAppConfig appConfig;
    
    //Bandera para saber si el usuario ha escogido o no una ruta 
    private String flag = "";
    

    
    public DirectoryDialogController(JFrame parent) {
         dialog = new DirectoryDialog(parent, true);
         directoryChooser = new JFileChooser();
         directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//Para que solo se pueda escoger directorios
         loadIconsIntoButtons();
         
         

        //setListeners
        this.dialog.getBtnEscogerDirectorio().addActionListener(this);
        this.dialog.getBtnGuardarCambios().addActionListener(this);
        
        
        appConfig = NotasAppConfig.getInstance();
    }
    
    public  void showDialog(){
        
        loadDirectoryFromProperties();
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setVisible(true);
        
    }
    
    public void hideDialog(){
        this.dialog.setVisible(false);
    }

    private void loadIconsIntoButtons() {
        this.dialog.getBtnEscogerDirectorio().setIcon(new ImageIcon(UrlImages.CHOOSE_DIRECTORY_ICON));
        this.dialog.getBtnGuardarCambios().setIcon(new ImageIcon(UrlImages.SAVE_ICON));
    }
    
    //Método el cual se abrira un dialogo para escoger un directorio 
    //Este sera el directorio base en el cual se recorrera para llenar el arbol
    private void chooseMainDirectory(){
        String userPath = "";
        
        int userChoosed = directoryChooser.showOpenDialog(this.dialog);
        
        if (userChoosed == JFileChooser.APPROVE_OPTION) {
            writePathIntoPropertiesFile(directoryChooser.getSelectedFile().getAbsolutePath());
            
        }else{
            Notifications.showWarning("Operacion cancelada");
        }
        
        
    }
    
    
    void loadDirectoryFromProperties(){
        
        try {
            String currentPath = appConfig.getMainDirectoryPath();
            flag = currentPath == null?"Directorio inicial no escogido":currentPath;
            printData(flag);
        } catch (NullPointerException e) {
            
            chooseMainDirectory();
        }

    }
    
    //Método que escribira en el archivo properties la ruta escogida por el usuario
    void writePathIntoPropertiesFile(String path){
        appConfig.setMainDirectoryPath(path);
        printData(path);
    }
    
    //Método que imprime la ruta
    void printData(String msg){
        this.dialog.getTxtUsuarioDirectorio().setText("");
        this.dialog.getTxtUsuarioDirectorio().setText(msg);
        
    }
    
    String getDirectoryTextArea(){
        return this.dialog.getTxtUsuarioDirectorio().getText();
    }
    
    void saveUserData(){
        if (flag.equals(getDirectoryTextArea())) {
            Notifications.showError("No has escogido una ruta inicial\nCambios no aplicados");
            
        }else{
            appConfig.saveAllChanges();
            Notifications.showInformation("Ruta guardada con exito");
           
        }
        
        hideDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.dialog.getBtnEscogerDirectorio()) {
            chooseMainDirectory();
        }
        
        if (e.getSource() == this.dialog.getBtnGuardarCambios()) {
            
            saveUserData();
        }
    }
    
    
    
    
}
