/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.principal;

import com.unmsm.fisi.telepeaje.conexion.ConexionArduino;
import com.unmsm.fisi.telepeaje.contenedor.TipoPeaje;
import com.unmsm.fisi.telepeaje.firebase.TipoPeajeFirebase;
import com.unmsm.fisi.telepeaje.soporte.Constante;
import com.unmsm.fisi.telepeaje.soporte.HiloInicio;
import java.util.List;
import com.unmsm.fisi.telepeaje.vista.MenuPrincipal;

/**
 *
 * @author PC-Cenpro
 */
public class Principal {

    public static boolean bandera = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        HiloInicio h = new HiloInicio();
        h.start();

        List<TipoPeaje> arregloPeaje = TipoPeajeFirebase.traerPeaje(Constante.sIdentificadorPeaje);
        ConexionArduino oConexionArduino = new ConexionArduino(arregloPeaje);
        oConexionArduino.conectar();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        MenuPrincipal i = new MenuPrincipal();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }

}
