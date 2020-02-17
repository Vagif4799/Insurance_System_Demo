package com.insurance_system.utilities;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtilForPdf {

    private JavaMailSender sender;

    public void sendPDF(String toAddress, String path) {
        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toAddress);
            helper.setSubject("PDF for your Insurance");
            helper.setText("Please, find your PDF attached.");
            helper.addAttachment("PDF", new File(path));
            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
