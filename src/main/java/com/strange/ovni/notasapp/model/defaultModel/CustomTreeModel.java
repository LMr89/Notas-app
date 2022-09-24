/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.model.defaultModel;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.model.entities.CustomTreeNode;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author MrRob0t
 */
public class CustomTreeModel extends DefaultMutableTreeNode {
    private File mainRoot;
    private DefaultTreeModel customModelWithDirectories;
    private NotasAppConfig appConfig;

    public CustomTreeModel() {
        
        appConfig = NotasAppConfig.getInstance();
    }

    
    
    
   //Método en el que se define el nodo padre 
   public void setMainRootDirectory(File root){
       this.mainRoot = root;
       this.setUserObject(new CustomTreeNode(this.mainRoot.getName(),this.mainRoot.getAbsolutePath()));
       
   }
   
   //Aqui se devuelve el modelo para incrustarlo en JTREE
    public DefaultTreeModel getModel(){
        customModelWithDirectories = null;
        customModelWithDirectories = new DefaultTreeModel(this);
        crearModelo(this, mainRoot);
        
        return customModelWithDirectories;
    }

   
   public void reCreateModelTree(){
       this.removeAllChildren();//Elimino todo lo que tenia
       
       String newMainDirectory = appConfig.getMainDirectoryPath();
       if (!newMainDirectory.equals(null)) {
           setMainRootDirectory(new File(newMainDirectory));
       }
       
      
   }
   
   /*
    Método encargado de crear el modelo recorriendo los directorios
    * @param nodoParent 
    */
   private void crearModelo(DefaultMutableTreeNode nodoParent , File folder){
       File[] filesFromRootDirectory = folder.listFiles();
       
       if (filesFromRootDirectory != null) {
           int counter = 0;
           for (File file : filesFromRootDirectory) {
               DefaultMutableTreeNode childDirectory  =  new DefaultMutableTreeNode(new CustomTreeNode(file.getName(),file.getAbsolutePath()));
               customModelWithDirectories.insertNodeInto(childDirectory, nodoParent, counter);
               counter++;
               
               
               if (file.isDirectory()) {
                   crearModelo(childDirectory, file);
                   
               }
               
           }
           
       }else{
           //Nothing to do
       
        }
       
   }
  
   
    
}
