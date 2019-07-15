/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.unmsm.fisi.telepeaje.coleccion.MantenimientoPeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.MantenimientoPeaje;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS
 */
public class MantenimientoFirebase {

    public static List<MantenimientoPeaje> listarMatenimientoPeaje(String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).
                    collection(MantenimientoPeajeColeccion.sNombreColeccion).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<MantenimientoPeaje> arregloMantenimientoPeaje = new ArrayList<>();
            
            if (!documents.isEmpty()) {
                documents.stream().forEach((document) -> {
                    arregloMantenimientoPeaje.add(document.toObject(MantenimientoPeaje.class));
                });
                return arregloMantenimientoPeaje;
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MantenimientoFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static MantenimientoPeaje buscarMatenimientoPeaje(String sIdentificadorPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).
                    collection(MantenimientoPeajeColeccion.sNombreColeccion).whereEqualTo(MantenimientoPeajeColeccion.sFecha, Fecha.fechaActual()).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<MantenimientoPeaje> arregloMantenimientoPeaje = new ArrayList<>();
            
            if (!documents.isEmpty()) {
                documents.stream().forEach((document) -> {
                    arregloMantenimientoPeaje.add(document.toObject(MantenimientoPeaje.class));
                });
                return arregloMantenimientoPeaje.get(0);
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MantenimientoFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean registrarMatenimiento(String sIdentificadorPeaje, MantenimientoPeaje oMantenimientoPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje)
                .collection(MantenimientoPeajeColeccion.sNombreColeccion).document();
        oMantenimientoPeaje.setsIdentificador(nycRef.getId());
        batch.set(nycRef, oMantenimientoPeaje);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }

    public static boolean actualizarMatenimiento(String sIdentificadorPeaje, MantenimientoPeaje oMantenimientoPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje)
                .collection(MantenimientoPeajeColeccion.sNombreColeccion).document(oMantenimientoPeaje.getsIdentificador());
        
        Map<String, Object> updates = new HashMap<>();
        updates.put(MantenimientoPeajeColeccion.sDescripcion, oMantenimientoPeaje.getsDescripcion());
        updates.put(MantenimientoPeajeColeccion.sFecha, oMantenimientoPeaje.getsFecha());
        updates.put(MantenimientoPeajeColeccion.sHora, oMantenimientoPeaje.getsHora());
        updates.put(MantenimientoPeajeColeccion.sIdentificador, oMantenimientoPeaje.getsIdentificador());
        
        batch.update(nycRef, updates);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
    
    public static boolean eliminarMatenimiento(String sIdentificadorPeaje, String sIdentificadorMantenimiento) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje)
                .collection(MantenimientoPeajeColeccion.sNombreColeccion).document(sIdentificadorMantenimiento);
        batch.delete(nycRef);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
    
}
