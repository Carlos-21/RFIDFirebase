/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.principal;

import com.unmsm.fisi.telepeaje.conexion.ConexionArduino;

/**
 *
 * @author PC-Cenpro
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionArduino oConexionArduino = new ConexionArduino();
        oConexionArduino.conectar();
    }
    
}
