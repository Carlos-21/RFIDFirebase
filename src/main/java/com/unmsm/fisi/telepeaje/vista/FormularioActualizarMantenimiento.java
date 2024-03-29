/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.vista;

import com.unmsm.fisi.telepeaje.contenedor.MantenimientoPeaje;
import com.unmsm.fisi.telepeaje.firebase.MantenimientoFirebase;
import com.unmsm.fisi.telepeaje.soporte.Constante;
import com.unmsm.fisi.telepeaje.soporte.Directorio;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author CARLOS
 */
public class FormularioActualizarMantenimiento extends javax.swing.JFrame {
    private final MenuPrincipal oMenuPrincipal;
    private final String sIdentificadorMantenimiento;
    
    /**
     * Creates new form FormularioRegistrarMantenimiento
     * @param oMenuPrincipal
     * @param sIdentificadorMantenimiento
     */
    public FormularioActualizarMantenimiento(MenuPrincipal oMenuPrincipal, String sIdentificadorMantenimiento) {
        initComponents();
        
        ImageIcon iconLogo = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.imagenApp);
        this.setIconImage(iconLogo.getImage());
        
        ImageIcon icon = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonActualizar);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
        botonActualizarMantenimiento.setIcon(icono);
        
        ImageIcon icon4 = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonAtras);
        Icon icono4 = new ImageIcon(icon4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        botonAtras.setIcon(icono4);
        
        this.oMenuPrincipal = oMenuPrincipal;
        this.sIdentificadorMantenimiento = sIdentificadorMantenimiento;
    }

    public void llenarCampos(MantenimientoPeaje oMantenimientoPeaje){
        textoDescripcion.setText(oMantenimientoPeaje.getsDescripcion());
        textoFecha.setText(oMantenimientoPeaje.getsFecha());
        textoHora.setText(oMantenimientoPeaje.getsHora());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonAtras = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textoDescripcion = new javax.swing.JTextField();
        textoFecha = new javax.swing.JTextField();
        textoHora = new javax.swing.JTextField();
        botonActualizarMantenimiento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulario de actualización");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Descripción");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Hora");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Fecha");

        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setText("Actualizar de mantenimiento");

        botonActualizarMantenimiento.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        botonActualizarMantenimiento.setText("<html><p align=\"center\">Actualizar</p><p align=\"center\">matenimiento</p></html>");
        botonActualizarMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarMantenimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(botonActualizarMantenimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel4)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(botonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(botonActualizarMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        oMenuPrincipal.setVisible(true);
        oMenuPrincipal.llenarTablaMantenimiento();
        this.dispose();
    }//GEN-LAST:event_botonAtrasActionPerformed

    private void botonActualizarMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarMantenimientoActionPerformed
        String sDescripcion = textoDescripcion.getText();
        String sFecha = textoFecha.getText();
        String sHora = textoHora.getText();

        if (!sDescripcion.isEmpty() && !sFecha.isEmpty() && !sHora.isEmpty()) {
            MantenimientoPeaje oMantenimientoPeaje = new MantenimientoPeaje();
            oMantenimientoPeaje.setsDescripcion(sDescripcion);
            oMantenimientoPeaje.setsFecha(sFecha);
            oMantenimientoPeaje.setsHora(sHora);
            oMantenimientoPeaje.setsIdentificador(sIdentificadorMantenimiento);

            boolean bActualizar = MantenimientoFirebase.actualizarMatenimiento(Constante.sIdentificadorPeaje, oMantenimientoPeaje);
            
            if (bActualizar) {
                JOptionPane.showMessageDialog(null, "Se actualizó un mantenimiento de manera exitosa", "Actualización de Mantenimiento", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar", "Error de actualizar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar, verifique lo que se ha escrito", "Error de actualizar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonActualizarMantenimientoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarMantenimiento;
    private javax.swing.JButton botonAtras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField textoDescripcion;
    private javax.swing.JTextField textoFecha;
    private javax.swing.JTextField textoHora;
    // End of variables declaration//GEN-END:variables
}
