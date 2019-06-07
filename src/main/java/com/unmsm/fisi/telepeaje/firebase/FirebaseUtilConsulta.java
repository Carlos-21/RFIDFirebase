/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.unmsm.fisi.telepeaje.coleccion.ConductorColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Conductor;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC-Cenpro
 */
public class FirebaseUtilConsulta {

    public static Conductor mostrarDatos(String sIdentificador) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();
        
        try {
            ApiFuture<DocumentSnapshot> future = oFirestore.collection(ConductorColeccion.sNombreColeccion).document(sIdentificador).get();

            List<QueryDocumentSnapshot> documents = (List<QueryDocumentSnapshot>) future.get();

            if (!documents.isEmpty()) {
                return documents.get(0).toObject(Conductor.class);
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FirebaseUtilConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
