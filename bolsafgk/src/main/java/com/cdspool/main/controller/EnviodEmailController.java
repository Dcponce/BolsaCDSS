package com.cdspool.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IClaveTeRepository;
import com.cdspool.main.repository.IUsuarioRepository;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping(value = "envio")
public class EnviodEmailController {

	public String random() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	IUsuarioRepository rUsuario;
	
	@Autowired
	IClaveTeRepository rTemporal;

	@GetMapping
	public void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo("david.cordova@proyectosfgk.org");

		helper.setSubject("Prueba con diseño");

		// default = text/plain
		// helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("", true);

		// hard coded a file path
		// FileSystemResource file = new FileSystemResource(new
		// File("C:\\Users\\david.poncefgkss\\Desktop\\Bolsa CDS\\map-marker.png"));

//        helper.addAttachment("my_photo.png", new ClassPathResource("map-marker.png"));

		javaMailSender.send(msg);

	}

	@PostMapping
	public boolean sendPassword(String email) throws MessagingException, IOException {
		ClaveTemporal ct = new ClaveTemporal();
		
		Usuario correo = rUsuario.findByEmail(email);
		String var = correo.getEmail();

		if (email.equals(var)) {
			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(email);

			helper.setSubject("Solicitud de restablecimiento de contraseña");

			helper.setText("Esta es su contraseña temporal <b>" + random() + "</b>", true);

			javaMailSender.send(msg);
			
			ct.setUsuario(correo);
			ct.setClavet(Base64Utils.encodeToString(random().getBytes()));
			
			rTemporal.save(ct);
			
			return true;
		} else {
			return false;
		}

	}

}
