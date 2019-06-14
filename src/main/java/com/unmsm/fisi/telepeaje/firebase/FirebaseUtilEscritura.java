/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class FirebaseUtilEscritura {

    public static boolean registroPago(String sIdentificador, double monto) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference sfRef = oFirestore.collection("Personal").document(sIdentificador);
        batch.update(sfRef, "nCredito", 10);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            if (future.get().isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

}
