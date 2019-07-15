/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.vista;

import com.unmsm.fisi.telepeaje.soporte.Directorio;
import com.unmsm.fisi.telepeaje.soporte.HiloInicio;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author CARLOS
 */
public class Perfil extends javax.swing.JFrame {

    /**
     * Creates new form Perfil
     */
    public Perfil() {
        initComponents();
        
        ImageIcon iconLogo = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.imagenApp);
        this.setIconImage(iconLogo.getImage());
        
        ImageIcon icon3 = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonPersonalPeaje);
        Icon icono3 = new ImageIcon(icon3.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT));
        botonPersonalPeaje.setIcon(icono3);
        
        ImageIcon icon4 = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonAdministradorPeaje);
        Icon icono4 = new ImageIcon(icon4.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT));
        botonAdministradorPeaje.setIcon(icono4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonPersonalPeaje = new javax.swing.JButton();
        botonAdministradorPeaje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perfiles");
        setResizable(false);

        botonPersonalPeaje.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        botonPersonalPeaje.setText("Personal de Peaje");
        botonPersonalPeaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPersonalPeajeActionPerformed(evt);
            }
        });

        botonAdministradorPeaje.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        botonAdministradorPeaje.setText("Administrador");
        botonAdministradorPeaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAdministradorPeajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonPersonalPeaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonAdministradorPeaje, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(botonPersonalPeaje, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(botonAdministradorPeaje, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAdministradorPeajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAdministradorPeajeActionPerformed
        AdministradorPeaje oAdministradorPeaje = new AdministradorPeaje();
        oAdministradorPeaje.setVisible(true);
        oAdministradorPeaje.setLocationRelativeTo(null);
        
        this.dispose();
    }//GEN-LAST:event_botonAdministradorPeajeActionPerformed

    private void botonPersonalPeajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPersonalPeajeActionPerformed
        this.dispose();
        
        HiloInicio h = new HiloInicio();
        h.start();
        
    }//GEN-LAST:event_botonPersonalPeajeActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAdministradorPeaje;
    private javax.swing.JButton botonPersonalPeaje;
    // End of variables declaration//GEN-END:variables
}
