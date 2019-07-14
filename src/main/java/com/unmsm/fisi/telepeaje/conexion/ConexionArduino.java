/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.conexion;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.unmsm.fisi.telepeaje.contenedor.TipoPeaje;
import com.unmsm.fisi.telepeaje.contenedor.Vehiculo;
import com.unmsm.fisi.telepeaje.firebase.FirebaseUtilConsulta;
import com.unmsm.fisi.telepeaje.firebase.FirebaseUtilEscritura;
import com.unmsm.fisi.telepeaje.principal.Principal;
import java.util.List;
import javax.swing.JOptionPane;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import com.unmsm.fisi.telepeaje.vista.MenuPrincipal;

/**
 * import jssc.SerialPortEvent; import jssc.SerialPortEventListener; import
 * jssc.SerialPortException; import views.Inicio;
 *
 *
 * @author CARLOS
 */
public class ConexionArduino implements SerialPortEventListener {

    private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private static final String PUERTO = "COM4";
    private static final int TIMEOUT = 2000;
    private static final int DATA_RATE = 9600;
    private List<TipoPeaje> arregloPeaje;

    public ConexionArduino(List<TipoPeaje> arregloPeaje) {
        this.arregloPeaje = arregloPeaje;
    }

    public void conectar() {
        try {
            ino.arduinoRXTX(PUERTO, DATA_RATE, this);
            Principal.bandera = false;
        } catch (ArduinoException ex) {
            //
        }
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        try {
            if (ino.isMessageAvailable()) {
                String texto = ino.printMessage();
                texto = texto.substring(1, texto.length());
                System.out.println(texto);
                if (!texto.isEmpty()) {
                    if (texto.compareTo("ectura del UID") != 0) {
                        Vehiculo oVehiculo = FirebaseUtilConsulta.mostrarVehiculo(texto);

                        if (oVehiculo != null) {
                            MenuPrincipal.llenarCampos(oVehiculo.getsPlaca(), oVehiculo.getsModelo(), oVehiculo.getsResponsable(), oVehiculo.getnEje(), oVehiculo.getnTipo(), oVehiculo.getsMarca());

                            switch (oVehiculo.getnEje()) {
                                case 1:
                                    boolean estado = FirebaseUtilEscritura.registroPago(oVehiculo.getsResponsable(), arregloPeaje.get(0).getnMonto(), oVehiculo);
                                    System.out.println("Estado : " + estado);
                                    break;
                                case 2:
                                    boolean estado2 = FirebaseUtilEscritura.registroPago(oVehiculo.getsResponsable(), arregloPeaje.get(2).getnMonto(), oVehiculo);
                                    System.out.println("Estado : " + estado2);
                                    break;

                            }
                        } else {
                            MenuPrincipal.vaciarCampos();
                            JOptionPane.showMessageDialog(null, "No hay registro del vehiculo");
                        }
                    }

                    /*Conductor oConductor = FirebaseUtilConsulta.mostrarDatos(texto);
                    if(oConductor != null){
                        System.out.println("Datos: " + oConductor.getsApellidoPaterno() + " " + oConductor.getsApellidoMaterno() + " " + oConductor.getsNombres());
                    }*/
                }

            }
        } catch (SerialPortException | ArduinoException ex) {
            //
        }
    }

}
