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
public class Vehiculo {

    private String sResponsable;
    private String sMarca;
    private String sModelo;
    private String sPlaca;
    private String sIdTag;
    private String sImagen;
    private int nTipo;
    private int nEje;

    public String getsResponsable() {
        return sResponsable;
    }

    public void setsResponsable(String sResponsable) {
        this.sResponsable = sResponsable;
    }

    public String getsMarca() {
        return sMarca;
    }

    public void setsMarca(String sMarca) {
        this.sMarca = sMarca;
    }

    public String getsModelo() {
        return sModelo;
    }

    public void setsModelo(String sModelo) {
        this.sModelo = sModelo;
    }

    public String getsPlaca() {
        return sPlaca;
    }

    public void setsPlaca(String sPlaca) {
        this.sPlaca = sPlaca;
    }

    public String getsIdTag() {
        return sIdTag;
    }

    public void setsIdTag(String sIdTag) {
        this.sIdTag = sIdTag;
    }

    public String getsImagen() {
        return sImagen;
    }

    public void setsImagen(String sImagen) {
        this.sImagen = sImagen;
    }

    public int getnTipo() {
        return nTipo;
    }

    public void setnTipo(int nTipo) {
        this.nTipo = nTipo;
    }

    public int getnEje() {
        return nEje;
    }

    public void setnEje(int nEje) {
        this.nEje = nEje;
    }

}
