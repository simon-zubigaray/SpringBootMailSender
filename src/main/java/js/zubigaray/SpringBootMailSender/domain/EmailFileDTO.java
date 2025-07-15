package js.zubigaray.SpringBootMailSender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO para representar un correo electrónico con archivo adjunto.
 * Se utiliza cuando se envía un email que incluye un archivo vía formulario multipart.
 */
@Getter
@Setter // Necesario para que Spring pueda poblar el DTO al recibir un @ModelAttribute
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileDTO {

    /**
     * Arreglo de direcciones de correo de los destinatarios.
     */
    private String[] toUser;

    /**
     * Asunto del correo electrónico.
     */
    private String subject;

    /**
     * Cuerpo del mensaje del correo electrónico.
     */
    private String message;

    /**
     * Archivo adjunto que se enviará junto con el correo.
     */
    private MultipartFile file;
}