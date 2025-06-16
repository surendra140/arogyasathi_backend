package com.arogyasathi.controller;

import com.arogyasathi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

//    @PostMapping("/send")
//    public ResponseEntity<String> sendEmail(
//            @RequestParam String to,
//            @RequestParam String subject,
//            @RequestParam String body) {
//
//        emailService.sendSimpleEmail(to, subject, body);
//        return ResponseEntity.ok("Email sent to " + to);
//    }

    @GetMapping("/send-html")
    public ResponseEntity<String> sendHtmlMail() {
        try {
            emailService.sendHtmlEmail(
                    "surendrabankar218@gmail.com",
                    "Confirm Your Email",
                    "surendra",
                    "https://your-app.com/verify?token=xyz123"
            );
            return ResponseEntity.ok("HTML email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}
