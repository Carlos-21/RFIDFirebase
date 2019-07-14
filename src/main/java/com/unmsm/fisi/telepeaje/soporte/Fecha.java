/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Calendar;

/**
 *
 * @author Jorge Meza
 */
public class Fecha {

    private static final String LUGAR = "America/Lima";
    private static final DateFormat HORA = new SimpleDateFormat("HH:mm:ss a");
    private static final DateFormat FECHA = new SimpleDateFormat("dd/MM/yyyy");

    public static String añoActual(){
        return String.valueOf(Calendar.YEAR);
    }
    
    public static String mesActual(){
        switch(Calendar.MONTH){
            case 1 : return "enero";
            case 2 : return "febrero";
            case 3 : return "marzo";
            case 4 : return "abril";
            case 5 : return "mayo";
            case 6 : return "junio";
            case 7 : return "julio";
            case 8 : return "agosto";
            case 9 : return "setiembre";
            case 10 : return "octubre";
            case 11 : return "noviembre";
            case 12 : return "diciembre";
            default : return null;
        }
    }
    
    public static String horaActual() {
        Calendar c = Calendar.getInstance(TimeZone
                .getTimeZone("America/Los_Angeles"));
        TimeZone zone = TimeZone.getTimeZone(LUGAR);
        Calendar calendar = Calendar.getInstance(zone);
        System.out.println("Fecha 1 : " + calendar.getTime());
        System.out.println("Fecha 2 : " + c.getTimeZone());
        return HORA.format(calendar.getTime());
    }

    public static String fechaActual() {
        TimeZone zone = TimeZone.getTimeZone(LUGAR);
        Calendar calendar = Calendar.getInstance(zone);
        return FECHA.format(calendar.getTime());
    }

    public static boolean rangoFecha() {
        TimeZone zone = TimeZone.getTimeZone(LUGAR);
        Calendar calendar = Calendar.getInstance(zone);

        Date horaActual = calendar.getTime();

        GregorianCalendar cal = new GregorianCalendar();

        cal.set(Calendar.AM_PM, Calendar.AM);

        cal.set(GregorianCalendar.HOUR, 8);
        cal.set(GregorianCalendar.MINUTE, 0);
        cal.set(GregorianCalendar.SECOND, 0);
        Date horaInicio = cal.getTime();

        cal.set(GregorianCalendar.HOUR, 9);
        cal.set(GregorianCalendar.MINUTE, 0);
        cal.set(GregorianCalendar.SECOND, 0);
        Date horaFin = cal.getTime();

        if (horaActual.compareTo(horaInicio) >= 0 && horaActual.compareTo(horaFin) <= 0) {
            return true;
        }
        return false;
    }
}
