/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import com.unmsm.fisi.telepeaje.conexion.ConexionArduino;
import com.unmsm.fisi.telepeaje.contenedor.TipoPeaje;
import com.unmsm.fisi.telepeaje.firebase.TipoPeajeFirebase;
import com.unmsm.fisi.telepeaje.vista.MenuPrincipal;
import com.unmsm.fisi.telepeaje.vista.ProgressBarInicio;
import java.util.List;

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
        
        List<TipoPeaje> arregloPeaje = TipoPeajeFirebase.traerPeaje(Constante.sIdentificadorPeaje);
        ConexionArduino oConexionArduino = new ConexionArduino(arregloPeaje);
        oConexionArduino.conectar();
        
        MenuPrincipal i2 = new MenuPrincipal();
        i2.setVisible(true);
        i2.setLocationRelativeTo(null);
    }
}
