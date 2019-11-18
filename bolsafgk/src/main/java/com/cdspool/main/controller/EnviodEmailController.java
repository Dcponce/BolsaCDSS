package com.cdspool.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@RestController
@RequestMapping(value = "envio")
public class EnviodEmailController {

	@Autowired
	private JavaMailSender javaMailSender;

	@GetMapping
	public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo("david.cordova@proyectosfgk.org");

        helper.setSubject("Prueba con diseño");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("", true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("C:\\Users\\david.poncefgkss\\Desktop\\Bolsa CDS\\map-marker.png"));

//        helper.addAttachment("my_photo.png", new ClassPathResource("map-marker.png"));

        javaMailSender.send(msg);

    }

}
