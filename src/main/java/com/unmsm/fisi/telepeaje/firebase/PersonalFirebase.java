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
import com.unmsm.fisi.telepeaje.coleccion.PersonalColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class PersonalFirebase {
    
    public static List<Personal> listarPersonal() {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection(PersonalColeccion.sNombreColeccion).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Personal> lPersonal = new ArrayList<>();
            
            if (!documents.isEmpty()) {
                documents.stream().forEach((document) -> {
                    lPersonal.add(document.toObject(Personal.class));
                });
                return lPersonal;
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            
        }
        return null;
    }
    
    public static Personal buscarPersona(String sIdentificadorPersonal) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        DocumentReference docRef = oFirestore.collection(PersonalColeccion.sNombreColeccion).document(sIdentificadorPersonal);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                return document.toObject(Personal.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
        }

        return null;
    }
    
}
