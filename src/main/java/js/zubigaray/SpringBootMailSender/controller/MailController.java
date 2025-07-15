package js.zubigaray.SpringBootMailSender.controller;

import js.zubigaray.SpringBootMailSender.domain.EmailDTO;
import js.zubigaray.SpringBootMailSender.domain.EmailFileDTO;
import js.zubigaray.SpringBootMailSender.service.IEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST encargado de manejar las solicitudes para el envío de correos electrónicos.
 * Define endpoints para enviar mensajes simples y con archivos adjuntos.
 */
@RestController
@RequestMapping("/v1")
public class MailController {

    /**
     * Servicio de envío de correos, inyectado por constructor.
     */
    private final IEmailService emailService;

    /**
     * Constructor que inyecta la dependencia del servicio de correo.
     *
     * @param emailService implementación de IEmailService usada para enviar correos.
     */
    public MailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Endpoint para recibir y procesar una solicitud de envío de correo simple (sin archivo).
     *
     * @param emailDTO objeto con la información del correo: destinatarios, asunto y mensaje.
     * @return ResponseEntity con estado HTTP 200 y un mensaje de confirmación.
     */
    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDTO) {

        System.out.println("Message sent " + emailDTO); // Log (opcional)

        // Envía el correo usando el servicio
        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

        // Retorna respuesta con estado "sent"
        Map<String, String> response = new HashMap<>();
        response.put("status", "sent");

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para enviar un correo electrónico con archivo adjunto.
     * Recibe datos a través de un formulario tipo multipart/form-data.
     *
     * @param emailFileDTO objeto con destinatarios, asunto, mensaje y archivo adjunto.
     * @return ResponseEntity con estado HTTP 200 y detalles del archivo enviado.
     */
    @PostMapping("/sendMessageWithFile")
    // @ModelAttribute le dice a Spring: Tomá los campos enviados en el formulario multipart/form-data y mapealos a este objeto.”
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO) {

        try {
            // Obtiene el nombre del archivo enviado con la extension
            String fileName = emailFileDTO.getFile().getOriginalFilename();

            // Define la ruta donde se guardará temporalmente el archivo
            Path path = Paths.get("src/mail/resource/files" + fileName);

            // Crea los directorios si no existen
            Files.createDirectories(path.getParent());

            // Copia el archivo recibido a la ruta especificada
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Convierte la ruta a un objeto File
            File file = path.toFile();

            // Envía el correo con el archivo adjunto
            emailService.sendEmailWithFile(
                    emailFileDTO.getToUser(),
                    emailFileDTO.getSubject(),
                    emailFileDTO.getMessage(),
                    file
            );

            // Respuesta exitosa
            Map<String, String> response = new HashMap<>();
            response.put("status", "sent");
            response.put("fileName", fileName);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // En caso de error, lanza una excepción genérica
            throw new RuntimeException("Error sending email with file. " + e.getMessage());
        }
    }
}