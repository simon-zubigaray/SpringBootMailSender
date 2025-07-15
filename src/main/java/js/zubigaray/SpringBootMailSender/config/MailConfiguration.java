package js.zubigaray.SpringBootMailSender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Clase de configuración para el envío de correos electrónicos usando JavaMail.
 * Esta clase configura y provee un {@link JavaMailSender} con los parámetros necesarios
 * para conectarse a un servidor SMTP.
 */
@Configuration
public class MailConfiguration {

    /**
     * Dirección de correo del remitente.
     */
    @Value("${email.sender}")
    private String emailUser;

    /**
     * Crea y configura una instancia de {@link JavaMailSender} para enviar correos.
     *
     * @return una instancia configurada de {@link JavaMailSender}
     */
    @Bean // El uso de @Bean permite que Spring pueda inyectar esta instancia donde se necesite, como en un @Service para enviar mails.
    public JavaMailSender getJavaMailSender() {
        // Crea una implementación de JavaMailSender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Establece el host del servidor SMTP.
        mailSender.setHost("smtp.gmail.com");

        // Establece el puerto SMTP para TLS. Gmail usa el 587
        mailSender.setPort(587);

        // Establece el nombre de usuario del remitente (cuenta de Gmail)
        mailSender.setUsername(emailUser);

        // Establece la contraseña de la cuenta (debería usarse una contraseña de aplicación, no la real)
        mailSender.setPassword("<Password>");

        // Obtiene y configura propiedades adicionales para la conexión SMTP
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp"); // Protocolo usado para enviar correo
        properties.put("mail.smtp.auth", "true");          // Habilita autenticación SMTP
        properties.put("mail.smtp.starttls.enable", "true"); // Activa STARTTLS para conexión segura
        properties.put("mail.debug", "true");              // Activa el modo debug para ver los logs

        // Devuelve el objeto configurado
        return mailSender;
    }
}