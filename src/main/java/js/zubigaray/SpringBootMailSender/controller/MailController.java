package js.zubigaray.SpringBootMailSender.controller;

import js.zubigaray.SpringBootMailSender.domain.EmailDTO;
import js.zubigaray.SpringBootMailSender.service.IEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MailController {

    private final IEmailService emailService;

    public MailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDTO) {

        System.out.println("Message sent " + emailDTO);

        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("status", "sent");

        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
