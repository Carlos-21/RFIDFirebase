/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author CARLOS
 */
public class Directorio {
    public static String sIconoDatos="/src/main/java/imagen/parking.png";
    public static String sIconoPlaca="/src/main/java/imagen/license-plate (2).png";
    public static String sIconoMarca="/src/main/java/imagen/kk.png";
    public static String sIconoModelo="/src/main/java/imagen/engine.png";
    public static String sIconoEje="/src/main/java/imagen/tire.png";
    public static String sIconoTipo="/src/main/java/imagen/car.png";
    public static String sIconoConductor="/src/main/java/imagen/id-card.png";
    public static String botonRegistrar = "/src/main/java/archivos/imagen/registrar.png";
    public static String botonActualizar = "/src/main/java/archivos/imagen/actualizar.png";
    public static String botonEliminar = "/src/main/java/archivos/imagen/eliminar.png";
    public static String botonAtras = "/src/main/java/archivos/imagen/back.png";

    public static String devolverDirectorioActual() {
        File miDir = new File(".");
        try {
            return miDir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
