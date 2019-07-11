/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.principal;

import Hilos.Hilo_ProgressBar_Inicio;
import com.unmsm.fisi.telepeaje.conexion.ConexionArduino;
import com.unmsm.fisi.telepeaje.contenedor.Peaje;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.firebase.FirebaseUtilConsulta;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import views.Menu_Principal;
import views.Mostrador_Peaje;

/**
 *
 * @author PC-Cenpro
 */
public class Principal {

    public static boolean bandera = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Hilo_ProgressBar_Inicio h = new Hilo_ProgressBar_Inicio();
        h.start();
        String sIdentificadorPeaje = "AXcVcZg4JsOCariIx5LH";
        List<Peaje> arregloPeaje = FirebaseUtilConsulta.traerPeaje(sIdentificadorPeaje);
        ConexionArduino oConexionArduino = new ConexionArduino(arregloPeaje);
        oConexionArduino.conectar();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Menu_Principal i = new Menu_Principal();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
//          String sIdentificadorPeaje = "AXcVcZg4JsOCariIx5LH";
//          int numero = FirebaseUtilConsulta.traerCantidad_Accesos(sIdentificadorPeaje);
    }

}
