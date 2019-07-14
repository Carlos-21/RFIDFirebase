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
import com.unmsm.fisi.telepeaje.contenedor.Empresa;
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
public class PagoFirebase {
    
    public static boolean registroPago(String sIdentificador, double monto, Vehiculo oVehiculo) {
        switch (oVehiculo.getnTipo()) {
            case 1:
                Personal oPersonal = PersonalFirebase.mostrarPersona(sIdentificador);
                return registrarPagoPersonal(oPersonal, sIdentificador, monto, oVehiculo);
            case 2:
                Empresa oEmpresa = EmpresaFirebase.mostrarEmpresa(sIdentificador);
                return registrarPagoEmpresa(oEmpresa, sIdentificador, monto, oVehiculo);
        }
        return false;
        
    }
    
    private static boolean registrarPagoPersonal(Personal oPersonal, String sIdentificador, double monto, Vehiculo oVehiculo){
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();
        
        WriteBatch batch = oFirestore.batch();
        
        DocumentReference sfRef = oFirestore.collection(PersonalColeccion.sNombreColeccion).document(sIdentificador);

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
                registrarPagoColeccion(sIdentificador, 1, oPago);
                return false;
            } else {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
    
    private static boolean registrarPagoEmpresa(Empresa oEmpresa, String sIdentificador, double monto, Vehiculo oVehiculo){
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();
        
        WriteBatch batch = oFirestore.batch();
        
        DocumentReference sfRef = oFirestore.collection(PersonalColeccion.sNombreColeccion).document(sIdentificador);

        if (oVehiculo.getnEje() > 1) {
            batch.update(sfRef, "nCredito", oEmpresa.getnCredito() - (monto * oVehiculo.getnEje()));
        } else {
            batch.update(sfRef, "nCredito", oEmpresa.getnCredito() - monto);
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
                registrarPagoColeccion(sIdentificador, 2, oPago);
                return false;
            } else {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
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
