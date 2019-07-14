/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.conexion;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.unmsm.fisi.telepeaje.contenedor.ContadorVehiculo;
import com.unmsm.fisi.telepeaje.contenedor.TipoPeaje;
import com.unmsm.fisi.telepeaje.contenedor.Vehiculo;
import com.unmsm.fisi.telepeaje.firebase.ContadorVehiculoFirebase;
import com.unmsm.fisi.telepeaje.firebase.PagoFirebase;
import com.unmsm.fisi.telepeaje.firebase.VehiculoFirebase;
import com.unmsm.fisi.telepeaje.principal.Principal;
import com.unmsm.fisi.telepeaje.soporte.Constante;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
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

    private final PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private static final String PUERTO = "COM4";
    private static final int TIMEOUT = 2000;
    private static final int DATA_RATE = 9600;
    private final List<TipoPeaje> arregloPeaje;

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
                if (!texto.isEmpty()) {
                    if (texto.compareTo("ectura del UID") != 0) {
                        Vehiculo oVehiculo = VehiculoFirebase.buscarVehiculo(texto);

                        if (oVehiculo != null) {
                            MenuPrincipal.llenarCampos(oVehiculo.getsPlaca(), oVehiculo.getsModelo(), oVehiculo.getsResponsable(), oVehiculo.getnEje(), oVehiculo.getnTipo(), oVehiculo.getsMarca());

                            switch (oVehiculo.getnEje()) {
                                case 1:
                                    boolean estado = PagoFirebase.registroPago(oVehiculo.getsResponsable(), arregloPeaje.get(0).getnMonto(), oVehiculo);
                                    ContadorVehiculo oContadorVehiculo = ContadorVehiculoFirebase.buscarContadorVehiculo(Constante.sIdentificadorPeaje, Fecha.fechaActual());
                                    MenuPrincipal.llenarContador(String.valueOf(oContadorVehiculo.getnContador()) + "vehiculos");
                                    break;
                                case 2:
                                    boolean estado2 = PagoFirebase.registroPago(oVehiculo.getsResponsable(), arregloPeaje.get(2).getnMonto(), oVehiculo);
                                    ContadorVehiculo oContadorVehiculo2 = ContadorVehiculoFirebase.buscarContadorVehiculo(Constante.sIdentificadorPeaje, Fecha.fechaActual());
                                    MenuPrincipal.llenarContador(String.valueOf(oContadorVehiculo2.getnContador()) + "vehiculos");
                                    break;

                            }
                        } else {
                            MenuPrincipal.vaciarCampos();
                            JOptionPane.showMessageDialog(null, "No hay registro del vehiculo");
                        }
                    }

                }

            }
        } catch (SerialPortException | ArduinoException ex) {

        }
    }

}
