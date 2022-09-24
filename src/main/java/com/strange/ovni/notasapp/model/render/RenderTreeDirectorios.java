/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.model.render;

import com.strange.ovni.notasapp.model.entities.CustomTreeNode;
import com.strange.ovni.notasapp.util.TreeIcons;
import java.awt.Component;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author mrhazard
 */
public class RenderTreeDirectorios  extends DefaultTreeCellRenderer{

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component compt =  super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus); //To change body of generated methods, choose Tools | Templates.
            
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        CustomTreeNode treeNode = (CustomTreeNode) node.getUserObject();
        if (node.isLeaf()) {
            //Si el directorio no tiene hijos
            
            
            setText(treeNode.getFileName());
            
            
            setIcon( treeNode.getIco());
            
        }else{
            setText(treeNode.getFileName());
            setClosedIcon(TreeIcons.CLOSE_FOLDER.getIco());
            setOpenIcon(TreeIcons.OPEN_FOLDER.getIco());
        }
        
        return compt;
    
    
    }
    
}
