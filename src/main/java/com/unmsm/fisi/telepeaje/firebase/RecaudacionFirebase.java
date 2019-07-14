/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.RecaudacionColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Recaudacion;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS
 */
public class RecaudacionFirebase {

    public static Recaudacion existenciaRecaudacion(String sIdentificadorPeaje) {
        try {
            ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

            Firestore oFirestore = oConexion.getoFirestore();

            CollectionReference lRecaudacion = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).collection(RecaudacionColeccion.sNombreColeccion);

            Query query = lRecaudacion.whereEqualTo(RecaudacionColeccion.sAño, Fecha.añoActual()).whereEqualTo(RecaudacionColeccion.sMes, Fecha.mesActual());

            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            
            if(documents.isEmpty()){
                return null;
            }
            
            Recaudacion oRecaudacion = documents.get(0).toObject(Recaudacion.class);
            return oRecaudacion;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(RecaudacionFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean registrarRecaudacion(Recaudacion oRecaudacion, String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).collection(RecaudacionColeccion.sNombreColeccion).document();

        batch.set(nycRef, oRecaudacion);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }

    public static boolean actualizarRecaudacion(Recaudacion oRecaudacion, String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).collection(RecaudacionColeccion.sNombreColeccion).document();
        batch.update(nycRef, RecaudacionColeccion.sMonto, oRecaudacion.getnMonto());
        batch.update(nycRef, RecaudacionColeccion.sVehiculos, oRecaudacion.getnVehiculos());

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
}
