/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.vista;

import com.unmsm.fisi.telepeaje.contenedor.ContadorVehiculo;
import com.unmsm.fisi.telepeaje.contenedor.Empresa;
import com.unmsm.fisi.telepeaje.contenedor.MantenimientoPeaje;
import com.unmsm.fisi.telepeaje.contenedor.Personal;
import com.unmsm.fisi.telepeaje.contenedor.ProveedorMantenimiento;
import com.unmsm.fisi.telepeaje.firebase.ContadorVehiculoFirebase;
import com.unmsm.fisi.telepeaje.firebase.MantenimientoFirebase;
import com.unmsm.fisi.telepeaje.firebase.UsuarioPeajeFirebase;
import com.unmsm.fisi.telepeaje.soporte.Constante;
import com.unmsm.fisi.telepeaje.soporte.Directorio;
import com.unmsm.fisi.telepeaje.soporte.Fecha;
import java.awt.Image;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Meza
 */
public class MenuPrincipal extends javax.swing.JFrame {

    private List<ProveedorMantenimiento> lProvedorPeaje;
    private List<MantenimientoPeaje> lMantenimientoPeaje;

    /**
     * Creates new form Menu_Principal
     */
    public MenuPrincipal() {
        initComponents();

        ImageIcon iconLogo = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.imagenApp);
        this.setIconImage(iconLogo.getImage());
        
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

        ImageIcon icon = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonRegistrar);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
        botonRegistrarMantenimiento.setIcon(icono);

        ImageIcon icon2 = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonActualizar);
        Icon icono2 = new ImageIcon(icon2.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
        botonActualizarMantenimiento.setIcon(icono2);

        ImageIcon icon3 = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonEliminar);
        Icon icono3 = new ImageIcon(icon3.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
        botonEliminarMantenimiento.setIcon(icono3);

        ImageIcon icon4 = new ImageIcon(Directorio.devolverDirectorioActual() + Directorio.botonAtras);
        Icon icono4 = new ImageIcon(icon4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        botonAtras.setIcon(icono4);
        
        UsuarioPeajeFirebase.mostrarUsuario(this);
        llenarTablaMantenimiento();
        
        ContadorVehiculo oContadorVehiculo2 = ContadorVehiculoFirebase.buscarContadorVehiculo(Constante.sIdentificadorPeaje, Fecha.fechaActual());
        if(oContadorVehiculo2 != null){
            llenarContador( oContadorVehiculo2.getnContador()==1?String.valueOf(oContadorVehiculo2.getnContador()) + " vehiculo": String.valueOf(oContadorVehiculo2.getnContador()) + " vehiculos");
        }
        else{
            llenarContador("0 vehiculos");
        }
        
        //ProcesoAlerta.procesoAutomatico();
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

    public static void llenarContador(String sContadorVehiculo) {
        textoContador.setText(sContadorVehiculo);
    }

    // METODOS PARA PESTAÑA DE USUARIOS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void llenarUsuarioPersonal(List<Personal> lPersonal) {
        int i = 0;
        if (lPersonal != null) {
            if (!lPersonal.isEmpty()) {
                String aTitulo[] = {"Tipo de Doc.", "N° Doc.", "Usuario", "Celular", "Direccion", "Credito"};
                String[][] mData = new String[lPersonal.size()][6];
                for (Personal oPersonal : lPersonal) {
                    mData[i][0] = oPersonal.getsTipoDocumento();
                    mData[i][1] = oPersonal.getsNumeroDocumento();
                    mData[i][2] = oPersonal.getsApellidoPaterno() + " " + oPersonal.getsApellidoMaterno() + ", " + oPersonal.getsNombre();
                    mData[i][3] = oPersonal.getsCelular();
                    mData[i][4] = oPersonal.getsDireccion();
                    mData[i][5] = String.valueOf(oPersonal.getnCredito());
                    i++;
                }
                DefaultTableModel tablaModelo = new DefaultTableModel(mData, aTitulo);

                tablaUsuarioPersonal.setModel(tablaModelo);
            }
        }
    }

    public void llenarUsuarioEmpresa(List<Empresa> lEmpresa) {
        int i = 0;

        if (lEmpresa != null) {
            String aTitulo[] = {"Tipo de Doc.", "N° Doc.", "Empresa", "Celular", "Direccion", "Credito"};
            String[][] mData = new String[lEmpresa.size()][6];
            for (Empresa oEmpresa : lEmpresa) {
                mData[i][0] = oEmpresa.getsTipoDocumento();
                mData[i][1] = oEmpresa.getsNumeroDocumento();
                mData[i][2] = oEmpresa.getsEmpresa();
                mData[i][3] = oEmpresa.getsCelular();
                mData[i][4] = oEmpresa.getsDireccion();
                mData[i][5] = String.valueOf(oEmpresa.getnCredito());
                i++;
            }
            DefaultTableModel tablaModelo = new DefaultTableModel(mData, aTitulo);

            tablaUsuarioEmpresa.setModel(tablaModelo);
        }
    }

    public void llenarTablaMantenimiento() {
        lMantenimientoPeaje = MantenimientoFirebase.listarMatenimientoPeaje(Constante.sIdentificadorPeaje);

        if (lMantenimientoPeaje != null) {
            String aTitulo[] = {"Descripción", "Fecha", "Hora"};
            String[][] mData = new String[lMantenimientoPeaje.size()][3];

            int i = 0;
            if (!lMantenimientoPeaje.isEmpty()) {
                for (MantenimientoPeaje oMantenimientoPeaje : lMantenimientoPeaje) {
                    mData[i][0] = oMantenimientoPeaje.getsDescripcion();
                    mData[i][1] = oMantenimientoPeaje.getsFecha();
                    mData[i][2] = oMantenimientoPeaje.getsHora();
                    i++;
                }
                DefaultTableModel tablaModelo = new DefaultTableModel(mData, aTitulo);

                tablaMantenimiento.setModel(tablaModelo);
            }
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
        panelMostrador = new javax.swing.JPanel();
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
        textoContador = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelUsuario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarioPersonal = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaUsuarioEmpresa = new javax.swing.JTable();
        panelMantenimiento = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMantenimiento = new javax.swing.JTable();
        botonEliminarMantenimiento = new javax.swing.JButton();
        botonActualizarMantenimiento = new javax.swing.JButton();
        botonRegistrarMantenimiento = new javax.swing.JButton();
        botonAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel principal");
        setResizable(false);

        panelMostrador.setBackground(new java.awt.Color(255, 255, 255));
        panelMostrador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2), "E-TOLL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 51, 102))); // NOI18N
        panelMostrador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        titulo.setText(" DATOS DEL VEHÍCULO");
        panelMostrador.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 240, 46));

        eje.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        eje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        panelMostrador.add(eje, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 150, 40));

        tipo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        panelMostrador.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 150, 40));

        marca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        marca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        marca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 2, true));
        panelMostrador.add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 150, 40));

        modelo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        modelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        panelMostrador.add(modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 150, 40));

        responsable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        responsable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        responsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        panelMostrador.add(responsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, 150, 40));

        placa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        placa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        panelMostrador.add(placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 150, 40));
        panelMostrador.add(iconTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 38, 34, 36));
        panelMostrador.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 850, 10));

        labelPlaca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelPlaca.setText("N° Placa");
        panelMostrador.add(labelPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("N° Ejes");
        panelMostrador.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Marca");
        panelMostrador.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setText("Tipo");
        panelMostrador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Modelo");
        panelMostrador.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Conductor");
        panelMostrador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, -1, -1));
        panelMostrador.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 850, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel6.setText("Gracias por su preferencia...");
        panelMostrador.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, -1, -1));
        panelMostrador.add(iconDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 50, 30));
        panelMostrador.add(iconTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 40, 30));
        panelMostrador.add(iconPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 30));
        panelMostrador.add(iconEjes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 40, 30));
        panelMostrador.add(iconConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 40, 30));
        panelMostrador.add(iconMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 40, 30));
        panelMostrador.add(iconModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 40, 30));

        textoContador.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        textoContador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoContador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 2));
        textoContador.setEnabled(false);
        panelMostrador.add(textoContador, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 150, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Cantidad de vehículos");
        panelMostrador.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, -1, -1));

        jTabbedPane1.addTab("Mostrador", panelMostrador);

        tablaUsuarioPersonal.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaUsuarioPersonal);

        tablaUsuarioEmpresa.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tablaUsuarioEmpresa);

        javax.swing.GroupLayout panelUsuarioLayout = new javax.swing.GroupLayout(panelUsuario);
        panelUsuario.setLayout(panelUsuarioLayout);
        panelUsuarioLayout.setHorizontalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUsuarioLayout.setVerticalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane1.addTab("Usuarios", panelUsuario);

        tablaMantenimiento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tablaMantenimiento);

        botonEliminarMantenimiento.setText("Eliminar");
        botonEliminarMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarMantenimientoActionPerformed(evt);
            }
        });

        botonActualizarMantenimiento.setText("Actualizar");
        botonActualizarMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarMantenimientoActionPerformed(evt);
            }
        });

        botonRegistrarMantenimiento.setText("Registrar");
        botonRegistrarMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarMantenimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMantenimientoLayout = new javax.swing.GroupLayout(panelMantenimiento);
        panelMantenimiento.setLayout(panelMantenimientoLayout);
        panelMantenimientoLayout.setHorizontalGroup(
            panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMantenimientoLayout.createSequentialGroup()
                .addGroup(panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMantenimientoLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelMantenimientoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonRegistrarMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(botonActualizarMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(botonEliminarMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panelMantenimientoLayout.setVerticalGroup(
            panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMantenimientoLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(panelMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminarMantenimiento)
                    .addComponent(botonRegistrarMantenimiento)
                    .addComponent(botonActualizarMantenimiento))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        jTabbedPane1.addTab("Mantenimiento", panelMantenimiento);

        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonAtras, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarMantenimientoActionPerformed
        FormularioRegistrarMantenimiento oRegistrarMantenimiento = new FormularioRegistrarMantenimiento(this);
        oRegistrarMantenimiento.setVisible(true);
        oRegistrarMantenimiento.setLocationRelativeTo(null);

        this.setVisible(false);
    }//GEN-LAST:event_botonRegistrarMantenimientoActionPerformed

    private void botonActualizarMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarMantenimientoActionPerformed
        int numeroFila = tablaMantenimiento.getSelectedRow();
        if (numeroFila != -1) {
            FormularioActualizarMantenimiento oActualizarMantenimiento = new FormularioActualizarMantenimiento(this, lMantenimientoPeaje.get(numeroFila).getsIdentificador());
            oActualizarMantenimiento.setVisible(true);
            oActualizarMantenimiento.setLocationRelativeTo(null);
            oActualizarMantenimiento.llenarCampos(lMantenimientoPeaje.get(numeroFila));

            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Antes de actualizar un registro, debe seleccionar un mantenimiento de la tabla.", "Actualizar mantenimiento", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botonActualizarMantenimientoActionPerformed

    private void botonEliminarMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarMantenimientoActionPerformed
        int numeroFila = tablaMantenimiento.getSelectedRow();
        if (numeroFila != -1) {
            MantenimientoPeaje oMantenimientoPeaje = lMantenimientoPeaje.get(numeroFila);

            int seleccion = JOptionPane.showOptionDialog(null, "¿Desea eliminar el mantenimiento: " + oMantenimientoPeaje.getsDescripcion() + " ?",
                    "Eliminación de mantenimiento", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"Aceptar", "Cancelar"}, "Cancelar");
            if (seleccion == 0) {
                boolean bEliminacion = MantenimientoFirebase.eliminarMatenimiento(Constante.sIdentificadorPeaje, oMantenimientoPeaje.getsIdentificador());

                if (bEliminacion) {
                    llenarTablaMantenimiento();
                    JOptionPane.showMessageDialog(null, "Se eliminó un mantenimiento de manera exitosa", "Eliminación de Mantenimiento", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar", "Error de eliminar", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Antes de eliminar un registro, debe seleccionar un mantenimiento de la tabla.", "Eliminación de un mantenimiento", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botonEliminarMantenimientoActionPerformed

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        this.dispose();
        Perfil oPerfil = new Perfil();
        oPerfil.setVisible(true);
        oPerfil.setLocationRelativeTo(null);
    }//GEN-LAST:event_botonAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarMantenimiento;
    private javax.swing.JButton botonAtras;
    private javax.swing.JButton botonEliminarMantenimiento;
    private javax.swing.JButton botonRegistrarMantenimiento;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelPlaca;
    public static javax.swing.JLabel marca;
    public static javax.swing.JLabel modelo;
    private javax.swing.JPanel panelMantenimiento;
    private javax.swing.JPanel panelMostrador;
    private javax.swing.JPanel panelUsuario;
    public static javax.swing.JLabel placa;
    public static javax.swing.JLabel responsable;
    private javax.swing.JTable tablaMantenimiento;
    private javax.swing.JTable tablaUsuarioEmpresa;
    private javax.swing.JTable tablaUsuarioPersonal;
    public static javax.swing.JLabel textoContador;
    public static javax.swing.JLabel tipo;
    public static javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
