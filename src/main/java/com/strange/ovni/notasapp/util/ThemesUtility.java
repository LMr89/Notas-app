package com.strange.ovni.notasapp.util;

/**
 *
 * @author MrRob0t
 */

public enum ThemesUtility {
    
    Arc("com.formdev.flatlaf.intellijthemes.FlatArcIJTheme","Arc"),
    Arc_Orange("com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme"," Arc_Orange"),
    Carbon("com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme"," Carbon"),
    Cyan_Light("com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme"," Cyan_Light"),
    Dark_Flat("com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme"," Dark_Flat"),
    Dracula("com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme"," Dracula"),
    Monokai_Pro("com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme"," Monokai_Pro"),
    Light_Flat("com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme"," Light_Flat"),
    Gruvbox_Dark_Medium("com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme"," Gruvbox_Dark_Medium"),
    Atom_One_Light("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme"," Atom_One_Light"),
    Light_Owl("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlIJTheme"," Light_Owl"),
    Material_Deep_Ocean("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme"," Material_Deep_Ocean");
    
    private String className;
    private String themeName;

    public String getThemeName() {
        return themeName;
    }

    public String getClassName() {
        return className;
    }

    private ThemesUtility(String className, String themeName) {
        this.className = className;
        this.themeName = themeName;
    }
    
   
}


