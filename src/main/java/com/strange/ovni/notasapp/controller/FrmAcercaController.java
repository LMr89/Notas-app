/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.controller;

import com.strange.ovni.notasapp.util.UrlImages;
import com.strange.ovni.notasapp.view.FrmAcerca;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author MrRob0t
 */
public class FrmAcercaController implements ActionListener{
    private FrmAcerca  frmAcerca;

    public FrmAcercaController(FrmAcerca frmAcerca) {
        this.frmAcerca = frmAcerca;
        this.frmAcerca.getBtnOk().addActionListener(this);
        init();
        
    }
    
      private void init() {
          ImageIcon presentacionImagen;
        presentacionImagen = new ImageIcon(new ImageIcon(UrlImages.ACERCA_IMAGE)
                .getImage()
                .getScaledInstance(this.frmAcerca.getTxtImgAcerca().getWidth(),
                        this.frmAcerca.getTxtImgAcerca().getHeight(),
                        Image.SCALE_SMOOTH));
        
        this.frmAcerca.getBtnOk().setIcon(new ImageIcon(UrlImages.APPLY_ICON));
          this.frmAcerca.getTxtImgAcerca().setIcon(presentacionImagen);
      }
    public void showAcerca(){
        this.frmAcerca.setLocationRelativeTo(null);
        this.frmAcerca.setVisible(true);
    }
    
     public void hideAcerca(){
        this.frmAcerca.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hideAcerca();
    }

  
    
    
}
