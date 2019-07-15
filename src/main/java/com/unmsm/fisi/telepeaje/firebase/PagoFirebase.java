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
import com.unmsm.fisi.telepeaje.contenedor.ContadorVehiculo;
import com.unmsm.fisi.telepeaje.contenedor.Empresa;
import com.unmsm.fisi.telepeaje.contenedor.Pago;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.contenedor.Recaudacion;
import com.unmsm.fisi.telepeaje.contenedor.UsuarioPeaje;
import com.unmsm.fisi.telepeaje.contenedor.Vehiculo;
import com.unmsm.fisi.telepeaje.soporte.Constante;
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
                Personal oPersonal = PersonalFirebase.buscarPersona(sIdentificador);
                
                UsuarioPeaje oUsuarioPeaje = new UsuarioPeaje();
                oUsuarioPeaje.setsIdentificador(sIdentificador);
                oUsuarioPeaje.setsFecha(Fecha.fechaActual());
                oUsuarioPeaje.setnTipo(1);
                UsuarioPeajeFirebase.registrarUsuarioPeaje(Constante.sIdentificadorPeaje, oUsuarioPeaje);
                
                Recaudacion oRecaudacion = RecaudacionFirebase.existenciaRecaudacion(Constante.sIdentificadorPeaje);
                
                if(oRecaudacion == null){
                    System.out.println("Año : " + Fecha.añoActual() + " Mes : " + Fecha.mesActual());
                    Recaudacion oRecaudacion1 = new Recaudacion();
                    oRecaudacion1.setnMonto(monto);
                    oRecaudacion1.setsAño(Fecha.añoActual());
                    oRecaudacion1.setsMes(Fecha.mesActual());
                    oRecaudacion1.setnVehiculos(1);
                    
                    RecaudacionFirebase.registrarRecaudacion(oRecaudacion1, Constante.sIdentificadorPeaje);
                }
                else{
                    oRecaudacion.setnMonto(oRecaudacion.getnMonto() + monto);
                    oRecaudacion.setnVehiculos(oRecaudacion.getnVehiculos() + 1);
                    
                    RecaudacionFirebase.actualizarRecaudacion(oRecaudacion, Constante.sIdentificadorPeaje);
                }
                ContadorVehiculo oContadorVehiculo = ContadorVehiculoFirebase.buscarContadorVehiculo(Constante.sIdentificadorPeaje, Fecha.fechaActual());
                
                if(oContadorVehiculo == null){
                    ContadorVehiculo oContadorVehiculo1 = new ContadorVehiculo();
                    oContadorVehiculo1.setnContador(1);
                    oContadorVehiculo1.setsFecha(Fecha.fechaActual());
                    ContadorVehiculoFirebase.registrarContadorVehiculo(oContadorVehiculo1, Constante.sIdentificadorPeaje);
                }
                else{
                    oContadorVehiculo.setnContador(oContadorVehiculo.getnContador()+1);
                    ContadorVehiculoFirebase.actualizarContadorVehiculo(oContadorVehiculo, Constante.sIdentificadorPeaje);
                }
                
                return registrarPagoPersonal(oPersonal, sIdentificador, monto, oVehiculo);
            case 2:
                Empresa oEmpresa = EmpresaFirebase.buscarEmpresa(sIdentificador);
                
                UsuarioPeaje oUsuarioPeaje2 = new UsuarioPeaje();
                oUsuarioPeaje2.setsIdentificador(sIdentificador);
                oUsuarioPeaje2.setsFecha(Fecha.fechaActual());
                oUsuarioPeaje2.setnTipo(2);
                UsuarioPeajeFirebase.registrarUsuarioPeaje(Constante.sIdentificadorPeaje, oUsuarioPeaje2);
                
                Recaudacion oRecaudacion2 = RecaudacionFirebase.existenciaRecaudacion(Constante.sIdentificadorPeaje);
                
                if(oRecaudacion2 == null){
                    Recaudacion oRecaudacion1 = new Recaudacion();
                    oRecaudacion1.setnMonto(monto);
                    oRecaudacion1.setsAño(Fecha.añoActual());
                    oRecaudacion1.setsMes(Fecha.mesActual());
                    oRecaudacion1.setnVehiculos(1);
                    
                    RecaudacionFirebase.registrarRecaudacion(oRecaudacion1, Constante.sIdentificadorPeaje);
                }
                else{
                    oRecaudacion2.setnMonto(oRecaudacion2.getnMonto() + monto);
                    oRecaudacion2.setnVehiculos(oRecaudacion2.getnVehiculos() + 1);
                    
                    RecaudacionFirebase.actualizarRecaudacion(oRecaudacion2, Constante.sIdentificadorPeaje);
                }
                
                ContadorVehiculo oContadorVehiculo3 = ContadorVehiculoFirebase.buscarContadorVehiculo(Constante.sIdentificadorPeaje, Fecha.fechaActual());
                
                if(oContadorVehiculo3 == null){
                    ContadorVehiculo oContadorVehiculo1 = new ContadorVehiculo();
                    oContadorVehiculo1.setnContador(1);
                    oContadorVehiculo1.setsFecha(Fecha.fechaActual());
                    ContadorVehiculoFirebase.registrarContadorVehiculo(oContadorVehiculo1, Constante.sIdentificadorPeaje);
                }
                else{
                    oContadorVehiculo3.setnContador(oContadorVehiculo3.getnContador()+1);
                    System.out.println("Contado  mpresa:" + oContadorVehiculo3.getnContador());
                    System.out.println("Bnadera empresa: " + ContadorVehiculoFirebase.actualizarContadorVehiculo(oContadorVehiculo3, Constante.sIdentificadorPeaje));
                }
                
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
            if (!future.get().isEmpty()) {
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
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
    
    private static boolean registrarPagoEmpresa(Empresa oEmpresa, String sIdentificador, double monto, Vehiculo oVehiculo){
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();
        
        WriteBatch batch = oFirestore.batch();
        
        DocumentReference sfRef = oFirestore.collection(EmpresaColeccion.sNombreColeccion).document(sIdentificador);

        if (oVehiculo.getnEje() > 1) {
            batch.update(sfRef, "nCredito", oEmpresa.getnCredito() - (monto * oVehiculo.getnEje()));
        } else {
            batch.update(sfRef, "nCredito", oEmpresa.getnCredito() - monto);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            if (!future.get().isEmpty()) {
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
                return true;
            } else {
                return false;
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
            
        }
        return false;
    }
}
