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
import com.unmsm.fisi.telepeaje.coleccion.EmpresaColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PagoColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PersonalColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Pago;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class FirebaseUtilEscritura {

    public static boolean registroPago(String sIdentificador, double monto, int tipo, String sVehiculo) {
        Personal oPersonal = FirebaseUtilConsulta.mostrarPersona(sIdentificador);
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();
        System.out.println("Tipo peaje: " + monto);
        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference sfRef = oFirestore.collection("Personal").document(sIdentificador);
        batch.update(sfRef, "nCredito", oPersonal.getnCredito() - 5.5);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            if (future.get().isEmpty()) {
                Pago oPago = new Pago();
                oPago.setnPago(0);
                oPago.setnMonto(monto);
                oPago.setsFecha(Fecha.fechaActual());
                oPago.setsHora(Fecha.horaActual());
                oPago.setsVehiculo(sIdentificador);
                
                return false;
            } else {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registrarPagoColeccion(String sIdentificador, double monto, int nTipo, Pago oPago) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        String sColeccion = "";
        
        switch(nTipo){
            case 1 : sColeccion = PersonalColeccion.sNombreColeccion;
            break;
            case 2 : sColeccion = EmpresaColeccion.sNombreColeccion;
            break;
        }
                
        
        DocumentReference nycRef = oFirestore.collection(sColeccion).document(sIdentificador)
                .collection(PagoColeccion.sNombreColeccion).document();
        batch.set(nycRef, oPago);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            if (future.get().isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
