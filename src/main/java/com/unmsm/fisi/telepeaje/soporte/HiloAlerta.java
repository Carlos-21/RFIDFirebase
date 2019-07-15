/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import java.util.TimerTask;

/**
 *
 * @author CARLOS
 */
public class HiloAlerta extends TimerTask{
    private final EnviarCorreo enviarCorreo;

    public HiloAlerta() {
        enviarCorreo = new EnviarCorreo();
    }

    @Override
    public void run() {
        enviarCorreo.enviarCorreoMatenimiento();
    }
}
