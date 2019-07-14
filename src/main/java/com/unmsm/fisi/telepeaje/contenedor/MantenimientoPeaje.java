/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.contenedor;

/**
 *
 * @author CARLOS
 */
public class MantenimientoPeaje {

    private String sDescripcion;
    private String sFecha;
    private String sHora;
    private String sIdentificador;

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public String getsFecha() {
        return sFecha;
    }

    public void setsFecha(String sFecha) {
        this.sFecha = sFecha;
    }

    public String getsHora() {
        return sHora;
    }

    public void setsHora(String sHora) {
        this.sHora = sHora;
    }

    public String getsIdentificador() {
        return sIdentificador;
    }

    public void setsIdentificador(String sIdentificador) {
        this.sIdentificador = sIdentificador;
    }

}
