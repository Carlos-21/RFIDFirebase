/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.TipoPeajeColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.TipoPeaje;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class TipoPeajeFirebase {
    
    public static List<TipoPeaje> traerPeaje(String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).
                    collection(TipoPeajeColeccion.sNombreColeccion).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<TipoPeaje> arregloPeaje = new ArrayList<>();
            
            if (!documents.isEmpty()) {
                documents.stream().forEach((document) -> {
                    arregloPeaje.add(document.toObject(TipoPeaje.class));
                });
                return arregloPeaje;
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            
        }
        return null;
    }
    
}
