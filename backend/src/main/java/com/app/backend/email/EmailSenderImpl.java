package com.app.backend.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender mailSender;

    //execute in a separate thread
    @Async
    @Override
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("dawid.jscript@gmail.com");

            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            log.error(String.format("failed to send email: %s", e));
            throw new IllegalStateException("failed to send email");
        }

    }
}
