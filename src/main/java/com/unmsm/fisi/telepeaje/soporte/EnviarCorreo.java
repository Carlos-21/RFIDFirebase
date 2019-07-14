/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import com.unmsm.fisi.telepeaje.contenedor.Peaje;
import com.unmsm.fisi.telepeaje.contenedor.ProveedorMantenimiento;
import com.unmsm.fisi.telepeaje.firebase.PeajeFirebase;
import com.unmsm.fisi.telepeaje.firebase.ProveedorFirebase;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author CARLOS
 */
public class EnviarCorreo {

    private final String sCorreoUsuario = "";
    private final String sContraseñaUsuario = "";
    private final Properties oProperties;

    public EnviarCorreo() {
        oProperties = new Properties();
        oProperties.put("mail.smtp.auth", "true");
        oProperties.put("mail.smtp.starttls.enable", "true");
        oProperties.put("mail.smtp.host", "smtp.gmail.com");
        oProperties.put("mail.smtp.port", "587");
    }

    public void enviarCorreoMatenimiento() {
        List<ProveedorMantenimiento> listaProveedor = ProveedorFirebase.listarMatenimientoProveedor();
        Peaje oPeaje = PeajeFirebase.buscarPeaje(Constante.sIdentificadorPeaje);
        for (ProveedorMantenimiento oProveedorMantenimiento : listaProveedor) {
            String sMensaje = "Cordiales saludos empresa " + oProveedorMantenimiento.getsEmpresa()
                    + "\nSomos del peaje " + oPeaje.getsNombre() + " distrito " + oPeaje.getsDistrito() + " ubicado en " + oPeaje.getsUbicacion()
                    + "\nnecesitamos un proceso de mantenimiento para el día de mañana"
                    + "\nagradecemos que responsa a este correo para poder coordinar los"
                    + "\nprecios y también el horario para que puedan realizar el mantenimiento."
                    + "\nGracias por su atención,esperamos su pronta respuesta.";
            Session session = Session.getInstance(oProperties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sCorreoUsuario, sContraseñaUsuario);
                }
            });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sCorreoUsuario));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(oProveedorMantenimiento.getsCorreo()));
                message.setSubject("Atención de mantenimiento");
                message.setText(sMensaje);

                Transport.send(message);
                JOptionPane.showMessageDialog(null, "Su mensaje ha sido enviado");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

    }
    
}
