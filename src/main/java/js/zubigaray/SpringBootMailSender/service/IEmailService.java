package js.zubigaray.SpringBootMailSender.service;

import java.io.File;

import java.io.File;

/**
 * Interfaz que define los métodos para enviar correos electrónicos.
 * Provee funcionalidades básicas para envío simple y envío con archivo adjunto.
 */
public interface IEmailService {

    /**
     * Envía un correo electrónico simple (sin archivos adjuntos).
     *
     * @param toUser  Arreglo de direcciones de correo electrónico de los destinatarios.
     * @param subject Asunto del correo.
     * @param message Cuerpo del mensaje en formato texto plano o HTML.
     */
    void sendEmail(String[] toUser, String subject, String message);

    /**
     * Envía un correo electrónico con un archivo adjunto.
     *
     * @param toUser  Arreglo de direcciones de correo electrónico de los destinatarios.
     * @param subject Asunto del correo.
     * @param message Cuerpo del mensaje en formato texto plano o HTML.
     * @param file    Archivo que se adjuntará al correo.
     */
    void sendEmailWithFile(String[] toUser, String subject, String message, File file);
}

