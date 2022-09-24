/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.view;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import javax.swing.JOptionPane;

/**
 *
 * @author mrhazard
 */
public class Notifications {

    public static void showInformation(String msg) {
        JOptionPane.showMessageDialog(null, msg, NotasAppConfig.APP_NAME, JOptionPane.INFORMATION_MESSAGE);
    }

    ;
    
    public static void showWarning(String msg) {
        JOptionPane.showMessageDialog(null, msg, NotasAppConfig.APP_NAME, JOptionPane.WARNING_MESSAGE);
    }

    ;
    
    public static void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, NotasAppConfig.APP_NAME, JOptionPane.ERROR_MESSAGE);
    }
    
    public static int askForSomething(String msg){
        return JOptionPane.showConfirmDialog(null, msg, NotasAppConfig.APP_NAME, JOptionPane.YES_NO_OPTION);
    }
;

}
