/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.conexion;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author CARLOS
 */
public class ConexionArduino implements SerialPortEventListener{

    private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private static final String PUERTO = "COM5";
    private static final int TIMEOUT = 2000;
    private static final int DATA_RATE = 9600;

    public void conectar(){
        try {
            ino.arduinoRXTX(PUERTO, DATA_RATE, this);
        } catch (ArduinoException ex) {
            //
        }
    }
    
    public static void main(String[] args) {
        ConexionArduino oConexionArduino = new ConexionArduino();
        oConexionArduino.conectar();
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        try {
            if (ino.isMessageAvailable()) {
                String texto = ino.printMessage();
                texto = texto.substring(1, texto.length());
                System.out.println(texto);
            }
        } catch (SerialPortException | ArduinoException ex) {
            //
        }
    }
    
}
