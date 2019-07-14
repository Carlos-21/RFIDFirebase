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
import com.unmsm.fisi.telepeaje.coleccion.MantenimientoPeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PagoColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.PersonalColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.MantenimientoPeaje;
import com.unmsm.fisi.telepeaje.contenedor.Pago;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.contenedor.Vehiculo;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class FirebaseUtilEscritura {

    public static boolean registroPago(String sIdentificador, double monto, Vehiculo oVehiculo) {
        Personal oPersonal = FirebaseUtilConsulta.mostrarPersona(sIdentificador);
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();
        System.out.println("Tipo peaje: " + monto);
        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        String sColeccion = "";
        int nTipoColeccion = 0;
        switch (oVehiculo.getnTipo()) {
            case 1:
                sColeccion = PersonalColeccion.sNombreColeccion;
                nTipoColeccion = 1;
                break;
            case 2:
                sColeccion = EmpresaColeccion.sNombreColeccion;
                nTipoColeccion = 2;
                break;
        }

        DocumentReference sfRef = oFirestore.collection(sColeccion).document(sIdentificador);

        if (oVehiculo.getnEje() > 1) {
            batch.update(sfRef, "nCredito", oPersonal.getnCredito() - (monto * oVehiculo.getnEje()));
        } else {
            batch.update(sfRef, "nCredito", oPersonal.getnCredito() - monto);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            if (future.get().isEmpty()) {
                Pago oPago = new Pago();
                oPago.setnPago(0);
                if (oVehiculo.getnEje() > 1) {
                    oPago.setnMonto(monto * oVehiculo.getnEje());
                } else {
                    oPago.setnMonto(monto);
                }
                oPago.setsFecha(Fecha.fechaActual());
                oPago.setsHora(Fecha.horaActual());
                oPago.setsVehiculo(oVehiculo.getsIdTag());
                registrarPagoColeccion(sIdentificador, nTipoColeccion, oPago);
                return false;
            } else {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registrarPagoColeccion(String sIdentificador, int nTipo, Pago oPago) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        String sColeccion = "";

        switch (nTipo) {
            case 1:
                sColeccion = PersonalColeccion.sNombreColeccion;
                break;
            case 2:
                sColeccion = EmpresaColeccion.sNombreColeccion;
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
