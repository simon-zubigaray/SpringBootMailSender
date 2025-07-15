package js.zubigaray.SpringBootMailSender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Implementación del servicio de correo electrónico {@link IEmailService}.
 * Utiliza {@link JavaMailSender} para enviar correos simples y correos con archivos adjuntos.
 */
@Service
public class EmailServiceImpl implements IEmailService {

    /**
     * Dirección de correo del remitente, inyectada desde el archivo de propiedades.
     */
    @Value("${email.sender}")
    private String emailUser;

    /**
     * Componente de Spring para enviar correos electrónicos.
     */
    private final JavaMailSender mailSender;

    /**
     * Constructor que recibe la dependencia de JavaMailSender (inyección por constructor).
     *
     * @param mailSender objeto encargado de enviar correos.
     */
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envía un correo electrónico simple sin archivos adjuntos.
     *
     * @param toUser  arreglo de destinatarios del correo.
     * @param subject asunto del correo.
     * @param message cuerpo del mensaje (texto plano).
     */
    @Override
    public void sendEmail(String[] toUser, String subject, String message) {
        // Crea un mensaje de texto simple
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailUser);      // Establece el remitente
        mailMessage.setTo(toUser);           // Establece los destinatarios
        mailMessage.setSubject(subject);     // Establece el asunto
        mailMessage.setText(message);        // Establece el cuerpo del mensaje

        mailSender.send(mailMessage);        // Envía el correo
    }

    /**
     * Envía un correo electrónico con un archivo adjunto.
     *
     * @param toUser  arreglo de destinatarios del correo.
     * @param subject asunto del correo.
     * @param message cuerpo del mensaje (texto plano o HTML).
     * @param file    archivo a adjuntar en el correo.
     */
    @Override
    public void sendEmailWithFile(String[] toUser, String subject, String message, File file) {
        try {
            // Crea un mensaje MIME que permite adjuntos y uso de codificación
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            // Usa helper para facilitar la construcción del mensaje
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(emailUser);             // Remitente
            mimeMessageHelper.setTo(toUser);                  // Destinatarios
            mimeMessageHelper.setSubject(subject);            // Asunto
            mimeMessageHelper.setText(message);               // Cuerpo del mensaje
            mimeMessageHelper.addAttachment(file.getName(), file); // Adjunta el archivo

            mailSender.send(mimeMessage);                     // Envía el correo

        } catch (MessagingException e) {
            // Lanza excepción en caso de error al construir o enviar el mensaje
            throw new RuntimeException(e);
        }
    }
}
