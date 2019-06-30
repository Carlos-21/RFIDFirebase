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
public class Personal {

    private String sTipoDocumento;
    private String sNumeroDocumento;
    private String sNombre;
    private String sApellidoPaterno;
    private String sApellidoMaterno;
    private String sDireccion;
    private String sCelular;
    private double nCredito;

    public String getsTipoDocumento() {
        return sTipoDocumento;
    }

    public void setsTipoDocumento(String sTipoDocumento) {
        this.sTipoDocumento = sTipoDocumento;
    }

    public String getsNumeroDocumento() {
        return sNumeroDocumento;
    }

    public void setsNumeroDocumento(String sNumeroDocumento) {
        this.sNumeroDocumento = sNumeroDocumento;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsApellidoPaterno() {
        return sApellidoPaterno;
    }

    public void setsApellidoPaterno(String sApellidoPaterno) {
        this.sApellidoPaterno = sApellidoPaterno;
    }

    public String getsApellidoMaterno() {
        return sApellidoMaterno;
    }

    public void setsApellidoMaterno(String sApellidoMaterno) {
        this.sApellidoMaterno = sApellidoMaterno;
    }

    public String getsDireccion() {
        return sDireccion;
    }

    public void setsDireccion(String sDireccion) {
        this.sDireccion = sDireccion;
    }

    public String getsCelular() {
        return sCelular;
    }

    public void setsCelular(String sCelular) {
        this.sCelular = sCelular;
    }

    public double getnCredito() {
        return nCredito;
    }

    public void setnCredito(double nCredito) {
        this.nCredito = nCredito;
    }
}
