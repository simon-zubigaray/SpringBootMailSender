package js.zubigaray.SpringBootMailSender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO para representar un correo electrónico simple sin archivo adjunto.
 * Se utiliza cuando se envía un email en formato JSON.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {

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
}