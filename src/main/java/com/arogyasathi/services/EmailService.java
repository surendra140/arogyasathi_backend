package com.arogyasathi.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(String toEmail, String subject, String name, String confirmationLink) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("your-email@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);

        String htmlContent = """
                <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;padding:20px;border:1px solid #ddd;border-radius:8px;">
                    <div style="text-align:center;">
                        <img src="https://img.icons8.com/ios-filled/50/000000/new-post.png" alt="email icon" width="50" height="50"/>
                        <h2>Welcome, %s</h2>
                    </div>
                    <p>You're receiving this message because you recently signed up for an account.</p>
                    <p>Confirm your email address by clicking the button below. This step adds extra security to your business by verifying you own this email.</p>
                    <div style="text-align:center;margin:30px 0;">
                        <a href="%s" style="background-color:#7f42ff;color:#fff;text-decoration:none;padding:12px 24px;border-radius:6px;display:inline-block;">Confirm email</a>
                    </div>
                    <p>Thanks,<br>ArogyaSathi</p>
                    <hr/>
                    <div style="text-align:center;margin-top:20px;">
                        <a href="#"><img src="https://img.icons8.com/color/48/facebook-new.png" width="24"/></a>
                        <a href="#"><img src="https://img.icons8.com/color/48/twitter--v1.png" width="24"/></a>
                        <a href="#"><img src="https://img.icons8.com/color/48/linkedin.png" width="24"/></a>
                        <a href="#"><img src="https://img.icons8.com/color/48/instagram-new.png" width="24"/></a>
                        <a href="#"><img src="https://img.icons8.com/color/48/pinterest--v1.png" width="24"/></a>
                        <a href="#"><img src="https://img.icons8.com/color/48/youtube-play.png" width="24"/></a>
                    </div>
                </div>
                """.formatted(name, confirmationLink);

        helper.setText(htmlContent, true); // true = isHtml
        mailSender.send(message);
    }

}
