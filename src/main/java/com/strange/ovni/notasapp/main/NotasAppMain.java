
package com.strange.ovni.notasapp.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.strange.ovni.notasapp.controller.MainController;
import com.strange.ovni.notasapp.util.NoteLogRecord;
import com.strange.ovni.notasapp.view.FrmPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MrRob0t
 */
public class NotasAppMain {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FrmPrincipal mainView = new FrmPrincipal();
                    MainController mainController = new MainController(mainView);
                    
                    //Se crear el archivo log para guardar temporalmente el contenido de las notas
                    NoteLogRecord.createLogIfNotExists();
                    //COMIENZA EL FLUJO DE LA APP
                    mainController.startApp();
                }
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NotasAppMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
