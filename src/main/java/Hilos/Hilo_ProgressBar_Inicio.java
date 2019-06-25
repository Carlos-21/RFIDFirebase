/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import views.ProgressBar_Inicio;

/**
 *
 * @author Jorge Meza
 */
public class Hilo_ProgressBar_Inicio extends Thread {

    @Override
    public void run() {
        ProgressBar_Inicio i = new ProgressBar_Inicio();
        i.setVisible(true);
        i.activar();
    }
    
}
