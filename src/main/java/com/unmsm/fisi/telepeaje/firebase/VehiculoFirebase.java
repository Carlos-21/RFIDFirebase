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
import com.unmsm.fisi.telepeaje.coleccion.VehiculoColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Vehiculo;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class VehiculoFirebase {

    public static Vehiculo buscarVehiculo(String sIdentificadorVehiculo) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        DocumentReference docRef = oFirestore.collection(VehiculoColeccion.sNombreColeccion).document(sIdentificadorVehiculo);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                return document.toObject(Vehiculo.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
        }

        return null;
    }
}
