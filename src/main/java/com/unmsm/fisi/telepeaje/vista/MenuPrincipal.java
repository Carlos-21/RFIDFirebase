/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.vista;

import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.firebase.FirebaseUtilConsulta;
import com.unmsm.fisi.telepeaje.soporte.Directorio;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Meza
 */
public class MenuPrincipal extends javax.swing.JFrame {

    DefaultTableModel modelotable;

    /**
     * Creates new form Menu_Principal
     */
    public MenuPrincipal() throws InterruptedException, ExecutionException {
        initComponents();

        ImageIcon imagenDatos = new ImageIcon(Directorio.sIconoDatos);
        Icon iconoDatos = new ImageIcon(imagenDatos.getImage().getScaledInstance(iconDatos.getWidth(), iconDatos.getHeight(), Image.SCALE_DEFAULT));
        iconDatos.setIcon(iconoDatos);

        ImageIcon imagenPlaca = new ImageIcon(Directorio.sIconoPlaca);
        Icon iconoPlaca = new ImageIcon(imagenPlaca.getImage().getScaledInstance(iconPlaca.getWidth(), iconPlaca.getHeight(), Image.SCALE_DEFAULT));
        iconPlaca.setIcon(iconoPlaca);

        ImageIcon imagenMarca = new ImageIcon(Directorio.sIconoMarca);
        Icon iconoMarca = new ImageIcon(imagenMarca.getImage().getScaledInstance(iconMarca.getWidth(), iconMarca.getHeight(), Image.SCALE_DEFAULT));
        iconMarca.setIcon(iconoMarca);

        ImageIcon imagenModelo = new ImageIcon(Directorio.sIconoModelo);
        Icon iconoModelo = new ImageIcon(imagenModelo.getImage().getScaledInstance(iconModelo.getWidth(), iconModelo.getHeight(), Image.SCALE_DEFAULT));
        iconModelo.setIcon(iconoModelo);

        ImageIcon imagenEjes = new ImageIcon(Directorio.sIconoEje);
        Icon iconoEjes = new ImageIcon(imagenEjes.getImage().getScaledInstance(iconEjes.getWidth(), iconEjes.getHeight(), Image.SCALE_DEFAULT));
        iconEjes.setIcon(iconoEjes);

        ImageIcon imagenTipo = new ImageIcon(Directorio.sIconoTipo);
        Icon iconoTipo = new ImageIcon(imagenTipo.getImage().getScaledInstance(iconTipo.getWidth(), iconTipo.getHeight(), Image.SCALE_DEFAULT));
        iconTipo.setIcon(iconoTipo);

        ImageIcon imagenConductor = new ImageIcon(Directorio.sIconoConductor);
        Icon iconoConductor = new ImageIcon(imagenConductor.getImage().getScaledInstance(iconConductor.getWidth(), iconConductor.getHeight(), Image.SCALE_DEFAULT));
        iconConductor.setIcon(iconoConductor);

        inicializar_tabla_usuarios();
    }

    // METODOS PARA EL MOSTRADOR PRINCIPAL ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void llenarCampos(String placaVeh, String modeloVeh, String responsableVeh, int nEjesVeh, int nTipoVeh, String marcaVeh) {
        placa.setText(placaVeh);
        modelo.setText(modeloVeh);
        responsable.setText(responsableVeh);
        eje.setText(String.valueOf(nEjesVeh));
        tipo.setText(String.valueOf(nTipoVeh));
        marca.setText(marcaVeh);
    }

    public static void vaciarCampos() {
        placa.setText("");
        modelo.setText("");
        responsable.setText("");
        eje.setText(String.valueOf(""));
        tipo.setText(String.valueOf(""));
        marca.setText("");

    }

    // METODOS PARA PESTAÑA DE USUARIOS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void inicializar_tabla_usuarios() {
        String cabecera[] = {"Nombres", "Apellidos", "Celular", "Direccion", "Tipo de Doc.", "N° Doc.", "Credito"};
        String datos[][] = {};
        modelotable = new DefaultTableModel(datos, cabecera);
        table_usuarios.setModel(modelotable);
    }

    public void llenarTablaUsuarios() throws InterruptedException, ExecutionException {
        List<Personal> personas = new ArrayList<>();
        personas = FirebaseUtilConsulta.traerPersonas();
        for (Personal persona : personas) {
            String nombre = persona.getsNombre();
            String apellidoPat = persona.getsApellidoPaterno();
            String apellidoMat = persona.getsApellidoMaterno();
            String celular = persona.getsCelular();
            String direccion = persona.getsDireccion();
            String tipo_documento = persona.getsTipoDocumento();
            String numero_documento = persona.getsNumeroDocumento();
            Double credito = persona.getnCredito();
            Object datos[] = {nombre, apellidoPat + " " + apellidoMat, celular, direccion, tipo_documento, numero_documento, credito + " "};
            modelotable.addRow(datos);
        }
        table_usuarios.setModel(modelotable);
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelotable.getRowCount(); i++) {
            modelotable.removeRow(i);
            i -= 1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        eje = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        marca = new javax.swing.JLabel();
        modelo = new javax.swing.JLabel();
        responsable = new javax.swing.JLabel();
        placa = new javax.swing.JLabel();
        iconTitulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        labelPlaca = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        iconDatos = new javax.swing.JLabel();
        iconTipo = new javax.swing.JLabel();
        iconPlaca = new javax.swing.JLabel();
        iconEjes = new javax.swing.JLabel();
        iconConductor = new javax.swing.JLabel();
        iconMarca = new javax.swing.JLabel();
        iconModelo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_usuarios = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2), "E-TOLL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        titulo.setText(" DATOS DEL VEHÍCULO");
        jPanel1.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 240, 46));

        eje.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        eje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        jPanel1.add(eje, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 150, 40));

        tipo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        jPanel1.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 150, 40));

        marca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        marca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 2, true));
        jPanel1.add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 150, 40));

        modelo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        modelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        jPanel1.add(modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 150, 40));

        responsable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        responsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        jPanel1.add(responsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, 150, 40));

        placa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        jPanel1.add(placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 150, 40));
        jPanel1.add(iconTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 38, 34, 36));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 850, 10));

        labelPlaca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelPlaca.setText("N° Placa");
        jPanel1.add(labelPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("N° Ejes");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Marca");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setText("Tipo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Modelo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Conductor");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 850, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel6.setText("Gracias por su preferencia...");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, -1, -1));
        jPanel1.add(iconDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 50, 30));
        jPanel1.add(iconTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 40, 30));
        jPanel1.add(iconPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 30));
        jPanel1.add(iconEjes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 40, 30));
        jPanel1.add(iconConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 40, 30));
        jPanel1.add(iconMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 40, 30));
        jPanel1.add(iconModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 40, 30));

        jTabbedPane1.addTab("Mostrador", jPanel1);

        table_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table_usuarios);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Usuarios", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Peajes", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Mantenimiento", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        try {
            limpiarTabla();
            llenarTablaUsuarios();
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuPrincipal().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel eje;
    private javax.swing.JLabel iconConductor;
    private javax.swing.JLabel iconDatos;
    private javax.swing.JLabel iconEjes;
    private javax.swing.JLabel iconMarca;
    private javax.swing.JLabel iconModelo;
    private javax.swing.JLabel iconPlaca;
    private javax.swing.JLabel iconTipo;
    private javax.swing.JLabel iconTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelPlaca;
    public static javax.swing.JLabel marca;
    public static javax.swing.JLabel modelo;
    public static javax.swing.JLabel placa;
    public static javax.swing.JLabel responsable;
    private javax.swing.JTable table_usuarios;
    public static javax.swing.JLabel tipo;
    public static javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}