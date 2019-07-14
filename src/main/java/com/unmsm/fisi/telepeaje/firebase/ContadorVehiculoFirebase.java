/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.unmsm.fisi.telepeaje.coleccion.ContadorVehiculoColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.RecaudacionColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.ContadorVehiculo;
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
public class ContadorVehiculoFirebase {
    public static ContadorVehiculo buscarContadorVehiculo(String sIdentificadorPeaje, String sFecha) {
        try {
            ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

            Firestore oFirestore = oConexion.getoFirestore();

            CollectionReference lRecaudacion = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje).collection(ContadorVehiculoColeccion.sNombreColeccion);

            Query query = lRecaudacion.whereEqualTo(ContadorVehiculoColeccion.sFecha, sFecha);

            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            ContadorVehiculo oContadorVehiculo = documents.get(0).toObject(ContadorVehiculo.class);
            oContadorVehiculo.setsIdentificador(documents.get(0).getId());
            return oContadorVehiculo;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(RecaudacionFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
