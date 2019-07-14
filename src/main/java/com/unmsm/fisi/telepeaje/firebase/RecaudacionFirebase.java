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
import com.unmsm.fisi.telepeaje.coleccion.ProveedorMantenimientoColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.ProveedorMantenimiento;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class RecaudacionFirebase {
    
    
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
}
