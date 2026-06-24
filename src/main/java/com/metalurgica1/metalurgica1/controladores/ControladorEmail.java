package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class ControladorEmail {

    private final EmailService emailService;

    public ControladorEmail(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/enviar")
    public ResponseEntity.BodyBuilder sendTest() {
        emailService.sendSimpleEmail(
                "franco.babbicola@gmail.com",
                "Prueba desde Metalúrgica",
                "Hola, este es un email de prueba del sistema."
        );
        return ResponseEntity.status(HttpStatus.OK);
    }
}