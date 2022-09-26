package com.strange.ovni.notasapp.model.defaultModel;

import com.strange.ovni.notasapp.util.ThemeProvider;
import javax.swing.DefaultListModel;

/**
 *
 * @author MrRob0t
 */
public class ListDefaultModel extends DefaultListModel<String>{
    
    public ListDefaultModel() {
        addAll(ThemeProvider.mapThemes.keySet());
    }
}
