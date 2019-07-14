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
import com.unmsm.fisi.telepeaje.coleccion.EmpresaColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Empresa;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class EmpresaFirebase {
    public static Empresa mostrarEmpresa(String sIdentificadorEmpresa) {
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
