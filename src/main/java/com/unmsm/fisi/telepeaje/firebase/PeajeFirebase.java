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
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.annotations.Nullable;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Peaje;
import com.unmsm.fisi.telepeaje.vista.MenuPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 *
 * @author CARLOS
 */
public class PeajeFirebase {
    
    public static void listarPeaje(MenuPrincipal oMenuPrincipal) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        oFirestore.collection(PeajeColeccion.sNombreColeccion)
                .addSnapshotListener((@Nullable QuerySnapshot snapshots, @Nullable FirestoreException e) -> {
                    if (e != null) {
                        System.err.println("Listen failed:" + e);
                    }

                    List<Peaje> lPeaje = new ArrayList<>();

                    if (snapshots != null) {
                        if (snapshots.size() != 0) {
                            for (DocumentSnapshot doc : snapshots) {
                                lPeaje.add(doc.toObject(Peaje.class));
                            }
                             oMenuPrincipal.llenarPeaje(lPeaje);
                            

                        }
                    }

                });
    }
    
    public static Peaje buscarPeaje(String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        DocumentReference docRef = oFirestore.collection(PeajeColeccion.sNombre).document(sIdentificadorPeaje);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                return document.toObject(Peaje.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
        }

        return null;

    }
    
}
