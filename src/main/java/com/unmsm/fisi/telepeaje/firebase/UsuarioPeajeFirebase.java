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
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.annotations.Nullable;
import com.unmsm.fisi.telepeaje.coleccion.PeajeColeccion;
import com.unmsm.fisi.telepeaje.coleccion.UsuarioPeajeColeccion;
import com.unmsm.fisi.telepeaje.conexion.ConexionFirebase;
import com.unmsm.fisi.telepeaje.contenedor.Empresa;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.contenedor.UsuarioPeaje;
import com.unmsm.fisi.telepeaje.soporte.Constante;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
import com.unmsm.fisi.telepeaje.vista.MenuPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author CARLOS
 */
public class UsuarioPeajeFirebase {

    public static void mostrarUsuario(MenuPrincipal oMenuPrincipal) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        oFirestore.collection(PeajeColeccion.sNombreColeccion).document(Constante.sIdentificadorPeaje)
                .collection(UsuarioPeajeColeccion.sNombreColeccion)
                .whereEqualTo(UsuarioPeajeColeccion.sFechaUsuario, Fecha.fechaActual())
                .addSnapshotListener((@Nullable QuerySnapshot snapshots, @Nullable FirestoreException e) -> {
                    if (e != null) {
                        System.err.println("Listen failed:" + e);
                    }

                    List<UsuarioPeaje> lUsuario = new ArrayList<>();

                    if (snapshots != null) {
                        if (snapshots.size() != 0) {
                            for (DocumentSnapshot doc : snapshots) {
                                lUsuario.add(doc.toObject(UsuarioPeaje.class));
                                System.out.println("entro");
                            }

                            List<Personal> lPersonal = new ArrayList<>();
                            List<Empresa> lEmpresa = new ArrayList<>();
                            
                            for (UsuarioPeaje oUsuarioPeaje : lUsuario) {
                                switch(oUsuarioPeaje.getnTipo()){
                                    case 1 : lPersonal.add(PersonalFirebase.buscarPersona(oUsuarioPeaje.getsIdentificador()));
                                    break;
                                    case 2 : lEmpresa.add(EmpresaFirebase.buscarEmpresa(oUsuarioPeaje.getsIdentificador()));
                                    break;
                                }
                            }
                            System.out.println("Cnatidad Usuario : " + lPersonal.size() + " " + lEmpresa.size());
                            oMenuPrincipal.llenarUsuarioPersonal(lPersonal);
                            oMenuPrincipal.llenarUsuarioEmpresa(lEmpresa);

                        }
                    }

                });
    }

    public static boolean registrarUsuarioPeaje(String sIdentificadorPeaje, UsuarioPeaje oUsuarioPeaje) {
        ConexionFirebase oConexion = ConexionFirebase.devolverConexion();

        Firestore oFirestore = oConexion.getoFirestore();

        WriteBatch batch = oFirestore.batch();

        DocumentReference nycRef = oFirestore.collection(PeajeColeccion.sNombreColeccion).document(sIdentificadorPeaje)
                .collection(UsuarioPeajeColeccion.sNombreColeccion).document();
        
        batch.set(nycRef, oUsuarioPeaje);

        ApiFuture<List<WriteResult>> future = batch.commit();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
        }
        return false;
    }
    
}
