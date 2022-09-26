package com.strange.ovni.notasapp.controller;

import com.strange.ovni.notasapp.config.NotasAppConfig;
import com.strange.ovni.notasapp.listeners.ListSelectionListener;
import com.strange.ovni.notasapp.model.defaultModel.ListDefaultModel;
import com.strange.ovni.notasapp.model.interfaces.OnChangeTheme;
import com.strange.ovni.notasapp.util.UrlImages;
import com.strange.ovni.notasapp.view.FrmTheme;
import com.strange.ovni.notasapp.view.Notifications;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MrRob0t
 */
public class FrmThemeController implements ActionListener{
    private final FrmTheme themeView;
    //Un array de interfaces las cuales les llamare el método onchangeTheme para cambiar el look and feel
    
    //Una referencia se le manda al listener del JLIST a modo de preview y el otro a este 
    //Controlador para que cuando el usuario acepte los cambios este se vea en la ventana principal
    private OnChangeTheme[] listenersThemeChanging;
     //Clase encargada de escribir los cambios del usuario en el archivo properties
    private  NotasAppConfig appConfig;
    private ListSelectionListener JlistListener;

    public FrmThemeController(FrmTheme themeView) {
        this.themeView = themeView;   
        listenersThemeChanging = new OnChangeTheme[2];
        JlistListener = new ListSelectionListener(this.themeView.getListaTemas());
        init();
    }
    void putIcons(){
        this.themeView.getBtnAplicar().setIcon(new ImageIcon(UrlImages.APPLY_ICON));
        this.themeView.getBtnCancelar().setIcon(new ImageIcon(UrlImages.CANCEL_ICON));
    }
    
    public void setFrmMainView(OnChangeTheme intr){
        listenersThemeChanging[0] = intr;
    }
    
    public void setFrmThemeView(OnChangeTheme intr){
        //Objeto de modo de preview este se le mandara al listener
        listenersThemeChanging[1] = intr;
        JlistListener.setListenersThemeChanging(listenersThemeChanging[1]);
    }
    
    
    void init(){
        putIcons();
        appConfig = NotasAppConfig.getInstance();
        this.themeView.setTitle(NotasAppConfig.APP_NAME);
        this.themeView.getBtnAplicar().addActionListener(this);
        this.themeView.getBtnCancelar().addActionListener(this);
        this.themeView.getListaTemas().setModel(new ListDefaultModel());
        this.themeView.getListaTemas().addListSelectionListener(JlistListener);
        
        
    
    }
    
    
    public void showDialog(){
        this.themeView.setLocationRelativeTo(null);
        this.themeView.setVisible(true);
    }
    
    public void hideDialog(){
        this.themeView.setVisible(false);
    }
    
    public void notifyChangeTheme(){
        //Se le llama a la refrencia de la vista principal
            listenersThemeChanging[0].changeTheme();
       
    }
    private void setThemeInProperties(){
        int selection = Notifications.askForSomething("¿Esta seguro de querer cambiar a ese tema?") ;
        if ( selection == JOptionPane.YES_OPTION) {
            notifyChangeTheme();
            hideDialog();
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.themeView.getBtnCancelar()) {
            hideDialog();
        }else{
            setThemeInProperties();
        }
    }
    
    
}
