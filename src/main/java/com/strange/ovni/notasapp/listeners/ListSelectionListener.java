/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.listeners;


import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.model.interfaces.OnChangeTheme;
import com.strange.ovni.notasapp.util.ThemeProvider;
import com.strange.ovni.notasapp.view.FrmTheme;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author MrRob0t
 */
public class ListSelectionListener implements javax.swing.event.ListSelectionListener{
    
    private final JList current;
    
    private OnChangeTheme listenersThemeChanging;
    private final NotasAppConfig config = NotasAppConfig.getInstance();

    public ListSelectionListener(JList current) {
        this.current = current;
    }

    public void setListenersThemeChanging(OnChangeTheme listenersThemeChanging) {
        this.listenersThemeChanging = listenersThemeChanging;
    }
    
   
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            if (!e.getValueIsAdjusting()) {
             Object[] selected = current.getSelectedValues();

            for (Object sel : selected) {
                System.out.println("Escogio-> "+ThemeProvider.mapThemes.get(sel.toString()));
                config.setTheme(ThemeProvider.mapThemes.get(sel.toString()));
                config.saveAllChanges();
                listenersThemeChanging.changeTheme();
                  //cambiarTema(sel.toString());
            }
        }
        } catch (Exception es) {
        }
    }
    
    private void cambiarTema(String classN) {
        try {
            UIManager.setLookAndFeel(classN);
            SwingUtilities.updateComponentTreeUI(new FrmTheme());
        } catch (ClassNotFoundException | IllegalAccessException 
                | InstantiationException | UnsupportedLookAndFeelException e) {
        
        }
    }
    
}
