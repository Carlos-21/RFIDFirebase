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
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Peaje;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.contenedor.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC-Cenpro
 */
public class FirebaseUtilConsulta {

    public static Vehiculo mostrarVehiculo(String sIdentificador) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        System.out.println("Conexion + " + oFirestore.toString() + " " + sIdentificador);

        DocumentReference docRef = oFirestore.collection("Vehiculo").document(sIdentificador);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                System.out.println("Document data: " + document.getData());
                return document.toObject(Vehiculo.class);
            } else {
                System.out.println("No such document!");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Personal mostrarPersona(String sIdentificador) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        System.out.println("Conexion + " + oFirestore.toString() + " " + sIdentificador);

        DocumentReference docRef = oFirestore.collection("Personal").document(sIdentificador);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                System.out.println("Document data: " + document.getData());
                return document.toObject(Personal.class);
            } else {
                System.out.println("No such document!");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Peaje> traerPeaje(String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection("Peaje").document(sIdentificadorPeaje).
                    collection("TipoPeaje").get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Peaje> arregloPeaje = new ArrayList<>();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    arregloPeaje.add(document.toObject(Peaje.class));
                }
                return arregloPeaje;
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FirebaseUtilConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
