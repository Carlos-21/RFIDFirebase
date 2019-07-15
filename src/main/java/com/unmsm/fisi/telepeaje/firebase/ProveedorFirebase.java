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
import com.unmsm.fisi.telepeaje.coleccion.ProveedorMantenimientoColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.ProveedorMantenimiento;
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
public class ProveedorFirebase {

    public static List<ProveedorMantenimiento> listarMatenimientoProveedor() {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        try {
            ApiFuture<QuerySnapshot> future = oFirestore.collection(ProveedorMantenimientoColeccion.sNombreColeccion).get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<ProveedorMantenimiento> arregloProveedorMantenimiento = new ArrayList<>();

            if (!documents.isEmpty()) {
                documents.stream().forEach((document) -> {
                    arregloProveedorMantenimiento.add(document.toObject(ProveedorMantenimiento.class));
                });
                return arregloProveedorMantenimiento;
            }
            return null;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MantenimientoFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean registrarProveedor(ProveedorMantenimiento oProveedorMantenimiento) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(ProveedorMantenimientoColeccion.sNombreColeccion).document();
        oProveedorMantenimiento.setsIdentificador(nycRef.getId());
        batch.set(nycRef, oProveedorMantenimiento);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }

    public static boolean actualizarProveedor(ProveedorMantenimiento oProveedorMantenimiento) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(ProveedorMantenimientoColeccion.sNombreColeccion).document(oProveedorMantenimiento.getsIdentificador());

        Map<String, Object> updates = new HashMap<>();
        updates.put(ProveedorMantenimientoColeccion.sTipoDocumento, oProveedorMantenimiento.getsTipoDocumento());
        updates.put(ProveedorMantenimientoColeccion.sNumeroDocumento, oProveedorMantenimiento.getsNumeroDocumento());
        updates.put(ProveedorMantenimientoColeccion.sEmpresa, oProveedorMantenimiento.getsEmpresa());
        updates.put(ProveedorMantenimientoColeccion.sDireccion, oProveedorMantenimiento.getsDireccion());
        updates.put(ProveedorMantenimientoColeccion.sTelefono, oProveedorMantenimiento.getsTelefono());
        updates.put(ProveedorMantenimientoColeccion.sCorreo, oProveedorMantenimiento.getsCorreo());

        batch.update(nycRef, updates);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }

    public static boolean eliminarProveedor(String sIdentificadorProveedor) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(ProveedorMantenimientoColeccion.sNombreColeccion).document(sIdentificadorProveedor);
        batch.delete(nycRef);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
}
