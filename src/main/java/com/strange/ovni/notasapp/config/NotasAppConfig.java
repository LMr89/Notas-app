/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.config;

import java.awt.Font;

/**
 *
 * @author mrhazard
 */
public class NotasAppConfig extends ApplicationPropertiesClass {

    private String mainDirectoryPath;
    private String theme;

    private Font fontUser;
    private static NotasAppConfig appConfig;

    private String fontFamily;
    private int fontStyle;
    private int fontSize;

    //Se aplica el patrón singleton para solo obtner una sola instancia  instancia 
    //Para manejar el archivo de propiedades
    public static NotasAppConfig getInstance() {
        if (appConfig == null) {
            appConfig = new NotasAppConfig();

        }

        return appConfig;
    }

    //Constantes de configuracion de la aplicacion
    private final String USER_DIRECTORY = "userDirectory";
    private final String FONT_FAMILY = "fontConfigFamily";
    private final String FONT_STYLE = "fontConfigStyle";
    private final String FONT_SIZE = "fontConfigSize";
    private final String THEME_CONFIG = "themeConfig";
    public static final String APP_NAME = "Notas App";

    private NotasAppConfig() {
        super();//Llamo al constructor del padre
        mainDirectoryPath = "";
        theme = "";
        fontFamily = "";
        fontStyle = 0;
        fontSize = 0;

    }

    public Font getFontUser() {
        try {
            this.fontFamily = this.getProperty(FONT_FAMILY);
            this.fontStyle = Integer.parseInt(this.getProperty(FONT_STYLE));
            this.fontSize = Integer.parseInt(this.getProperty(FONT_SIZE));
            fontUser = new Font(this.fontFamily, this.fontStyle, this.fontSize);
        } catch (NumberFormatException e) {
            fontUser = null;
        }

        return fontUser;
    }

    public void setFontUser(Font fontUser) {
        this.fontUser = fontUser;
        this.fontFamily = fontUser.getFamily();
        this.fontStyle = fontUser.getStyle();
        this.fontSize = fontUser.getSize();

        this.addProperty(FONT_FAMILY, fontFamily);
        this.addProperty(FONT_STYLE, String.valueOf(fontStyle));
        this.addProperty(FONT_SIZE, String.valueOf(fontSize));

    }

    public String getMainDirectoryPath() {
        mainDirectoryPath = this.getProperty(USER_DIRECTORY);
        return mainDirectoryPath;
    }

    public void setMainDirectoryPath(String mainDirectoryPath) {
        this.mainDirectoryPath = mainDirectoryPath;

        this.addProperty(USER_DIRECTORY, mainDirectoryPath);
    }

    public String getTheme() {
        theme = this.getProperty(THEME_CONFIG);
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;

        this.addProperty(THEME_CONFIG, theme);
    }

    public void saveAllChanges() {
        this.saveAllPropertiesChanged();
    }

    /*
    public static void main(String[] args) {
        NotasAppConfig config = new NotasAppConfig();
        //config.FONT_CONFIG;
        //config.setMainDirectoryPath("dasdasd");
        System.out.println(config.getMainDirectoryPath());
        //config.addProperty("Lonely", "tell yout");
        System.out.println(config.getProperty("Lonely"));
               
    }*/
}
