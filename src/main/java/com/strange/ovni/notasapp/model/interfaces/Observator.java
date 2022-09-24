/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.model.interfaces;

/**
 *
 * @author mrhazard
 */
public interface Observator {
    public void updateState();
    
    //Este método ayudara a transferir rutas entre distintas vistas
    //En este caso mas se usa entre JOPPUPMENU y el CONTROLADOR PRINCIPAL
    public void transferPath(String path);
    
}
