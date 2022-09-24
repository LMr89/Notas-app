/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.listeners;

import com.strange.ovni.notasapp.model.entities.CustomTreeNode;
import com.strange.ovni.notasapp.view.PopupTreeMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author mrhazard
 */
public class TreeActionListener implements TreeSelectionListener {

    private JTree currentTreeDirectories;
    private JPopupMenu treePopupMenu;
    //IMPLEMENTAR UN LISTENER QUE AYUADARA A PASAR LA DATA ENTRE EL JTREE Y JPOPUP
    private OnPassData[] dataListener;

    public TreeActionListener(JTree currentTreeDirectories, PopupTreeMenu popuMenu) {

        treePopupMenu = popuMenu;
        this.currentTreeDirectories = currentTreeDirectories;

        //Definir que solo se escoja un item
        this.currentTreeDirectories.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //TreeListener with custom listener class
        this.currentTreeDirectories.addTreeSelectionListener(this);
        this.currentTreeDirectories.setComponentPopupMenu(treePopupMenu);
        
        //Se inicializara el dataListener con solo 2 objetos estos son el Jpopup y el main controller
        this.dataListener = new OnPassData[2];
        this.dataListener[0] = (OnPassData) treePopupMenu;

    }
    
    public void addMainControllerListenerOnPassData(OnPassData mainPassData){
        //Esto solo tiene que ejecutarse una sola vez
        this.dataListener[1] = mainPassData;
    }
    
    
    void transferDataFoObjectsListeners(CustomTreeNode getNode){
        for (OnPassData onPassData : dataListener) {
            onPassData.transferData(getNode);
        }
        
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        try {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this.currentTreeDirectories.getLastSelectedPathComponent();
            CustomTreeNode getNode = (CustomTreeNode) selectedNode.getUserObject();

            //Se pasara el nodo a la clase quien implemente esta intefaz esto hara de callback
            transferDataFoObjectsListeners(getNode);
            //System.out.println(getNode.getAbsolutePath());
        } catch (Exception exx) {
            exx.getMessage();
        }

    }

}
