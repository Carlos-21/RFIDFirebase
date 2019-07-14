/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.soporte;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author CARLOS
 */
public class EnviarCorreo {
    private final Properties oProperties;
    
    public EnviarCorreo() {
        oProperties = new Properties();
        oProperties.put("mail.smtp.auth", "true");
        oProperties.put("mail.smtp.starttls.enable", "true");
        oProperties.put("mail.smtp.host", "smtp.gmail.com");
        oProperties.put("mail.smtp.port", "587");
    }
    
    
    public void enviarCorreoMatenimiento(){
        
    }
    /*
     public void SendMail() {
        Session session = Session.getInstance(Properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Su mensaje ha sido enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/
}
