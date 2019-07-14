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
public class UsuarioPeaje {

    private String sIdentificador;
    private String sFecha;
    private int nTipo;

    public String getsIdentificador() {
        return sIdentificador;
    }

    public void setsIdentificador(String sIdentificador) {
        this.sIdentificador = sIdentificador;
    }

    public String getsFecha() {
        return sFecha;
    }

    public void setsFecha(String sFecha) {
        this.sFecha = sFecha;
    }

    public int getnTipo() {
        return nTipo;
    }

    public void setnTipo(int nTipo) {
        this.nTipo = nTipo;
    }

}
