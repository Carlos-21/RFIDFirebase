/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Peaje;
import java.util.concurrent.ExecutionException;


/**
 *
 * @author CARLOS
 */
public class PeajeFirebase {

    public static Peaje buscarPeaje(String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        DocumentReference docRef = oFirestore.collection(PeajeColeccion.sNombre).document(sIdentificadorPeaje);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                System.out.println("Document data: " + document.getData());
                return document.toObject(Peaje.class);
            } else {
                System.out.println("No such document!");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
        }

        return null;

    }
    
}
