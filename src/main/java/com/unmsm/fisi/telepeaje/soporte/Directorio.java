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
