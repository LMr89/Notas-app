
package com.strange.ovni.notasapp.main;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.controller.FrmAcercaController;
import com.strange.ovni.notasapp.controller.FrmThemeController;
import com.strange.ovni.notasapp.controller.MainController;
import com.strange.ovni.notasapp.util.NoteLogRecord;
import com.strange.ovni.notasapp.view.FrmAcerca;
import com.strange.ovni.notasapp.view.FrmPrincipal;
import com.strange.ovni.notasapp.view.FrmTheme;
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
        
        NotasAppConfig config = NotasAppConfig.getInstance();
        try {
            
            //Compruebo que haya un tema en el archivo properties sino lo hay escogo al por defecto
            if (config.getTheme().isEmpty()) {
                config.setTheme(config.DEFAULT_THEME_CONFIG);
                config.saveAllChanges();
            }
            UIManager.setLookAndFeel(config.getTheme());
            config.checkForValues();
            
            
            java.awt.EventQueue.invokeLater(() -> {
                FrmPrincipal mainView = new FrmPrincipal();
                MainController mainController = new MainController(mainView);
                
                //FRMTHEME view and controller
                FrmTheme themeDialog = new FrmTheme();
                FrmThemeController themeController = new FrmThemeController(themeDialog);
                
                //FRMTHEME view and controller
                FrmAcerca frmAcerca = new FrmAcerca();
                FrmAcercaController acercaController = new FrmAcercaController(frmAcerca);
                
                //Se le manda las vistas que implementan la interfaz OnChangetheme al frmThemeController
                themeController.setFrmMainView(mainView);
                themeController.setFrmThemeView(themeDialog);
 
                //Se les pasa como argunmento los demas controladores al controlador principal
                mainController.setThemeController(themeController);
                mainController.setAcercaController(acercaController);
                
                //Se crear el archivo log para guardar temporalmente el contenido de las notas
                NoteLogRecord.createLogIfNotExists();
                //COMIENZA EL FLUJO DE LA APP
                mainController.startApp();
            });
        } catch (Exception ex) {
            config.settingEmptyValues();
        }
        
    }
    
}
