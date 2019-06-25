/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.conexion;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.unmsm.fisi.telepeaje.soporte.Directorio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author CARLOS
 */
public class ConexionFirebase {
    private static final String PATH = "/src/main/java/credencial/accountKey.json";
    private static final String PROJECT_ID = "e-toll-ffcc8";
    private static ConexionFirebase oConexion;
    private Firestore oFirestore;

    /**
     * Constructor encargado de inicializar el atributo oFirestore para poder
     * tener acceso a la base de datos usando un archivo como credencial
     * generado por Firebase
     */
    private ConexionFirebase() {
        FileInputStream serviceAccount = null;
        try {
            System.out.println("fasfi : 0" + Directorio.devolverDirectorioActual() + PATH);
            serviceAccount = new FileInputStream(Directorio.devolverDirectorioActual() + PATH);
            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId(PROJECT_ID)
                    .build();
            oFirestore = firestoreOptions.getService();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexionFirebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método encargado de devolver una sola instancia de la clase Conexion
     *
     * @return
     */
    public static ConexionFirebase devolverConexion() {
        if (oConexion == null) {
            oConexion = new ConexionFirebase();
        }
        return oConexion;
    }

    /**
     * Método encargado de devolver un objetivo del tipo Firestore, que
     * permitirá realizar diversas operaciones a la base de datos ya que es la
     * base para iniciar cualquier operación
     *
     * @return
     */
    public Firestore getoFirestore() {
        return oFirestore;
    }
}
