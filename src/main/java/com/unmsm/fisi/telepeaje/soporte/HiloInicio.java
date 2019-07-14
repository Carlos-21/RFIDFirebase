/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import com.unmsm.fisi.telepeaje.vista.ProgressBarInicio;

/**
 *
 * @author CARLOS
 */
public class HiloInicio extends Thread{
    @Override
    public void run() {
        ProgressBarInicio i = new ProgressBarInicio();
        i.setVisible(true);
        i.activar();
    }
}
