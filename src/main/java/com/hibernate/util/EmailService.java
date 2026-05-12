package com.hibernate.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    private static final String REMITENTE = "hecmartinez555@gmail.com";
    private static final String PASSWORD   = "aeqj wyar cwmk rzjm";

    public static void enviarNotificacionRutina(String destinatario,
                                                 String nombreCliente,
                                                 String nombreRutina) {
        Properties props = new Properties();
        props.put("mail.smtp.auth",            "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host",            "smtp.gmail.com");
        props.put("mail.smtp.port",            "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, PASSWORD);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(REMITENTE));
            mensaje.setRecipients(Message.RecipientType.TO,
                                  InternetAddress.parse(destinatario));
            mensaje.setSubject("Nueva rutina asignada - Gimnasio");
            mensaje.setText(
                "Hola " + nombreCliente + ",\n\n" +
                "Tu entrenador te ha asignado la rutina: " + nombreRutina + "\n\n" +
                "Accede a la app para ver los detalles.\n\n" +
                "Saludos,\nEl equipo del Gimnasio"
            );

            Transport.send(mensaje);
            System.out.println("Email enviado a " + destinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}