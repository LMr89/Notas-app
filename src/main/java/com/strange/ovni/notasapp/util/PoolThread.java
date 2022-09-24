/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strange.ovni.notasapp.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author mrhazard
 */
public class PoolThread{
    private static  ExecutorService executor;
    
    
    public static  ExecutorService getInstance(){
        if (executor == null) {
            //Se inicializa el pool de threads con tan solo 1
            executor = Executors.newFixedThreadPool(1);
        }
        
        return executor;
    }

    private PoolThread(){}

    
}
