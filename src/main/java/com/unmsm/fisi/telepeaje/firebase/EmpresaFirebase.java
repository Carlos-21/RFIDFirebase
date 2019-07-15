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
import com.unmsm.fisi.telepeaje.coleccion.EmpresaColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Empresa;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class EmpresaFirebase {
    
    public static List<Empresa> listarEmpresa() {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection(EmpresaColeccion.sNombreColeccion).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Empresa> lEmpresa = new ArrayList<>();
            
            if (!documents.isEmpty()) {
                documents.stream().forEach((document) -> {
                    lEmpresa.add(document.toObject(Empresa.class));
                });
                return lEmpresa;
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            
        }
        return null;
    }
    
    public static Empresa buscarEmpresa(String sIdentificadorEmpresa) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        DocumentReference docRef = oFirestore.collection(EmpresaColeccion.sNombreColeccion).document(sIdentificadorEmpresa);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                return document.toObject(Empresa.class);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
        }

        return null;
    }
    
}
