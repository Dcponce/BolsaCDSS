package com.cdspool.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Email;
import com.cdspool.main.model.Empresa;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.service.AlumnoService;
import com.cdspool.main.service.ClaveTeService;
import com.cdspool.main.service.EmailService;
import com.cdspool.main.service.EmpresaService;
import com.cdspool.main.service.UsuarioService;

import org.springframework.mail.javamail.MimeMessageHelper;

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
	UsuarioService uService;

	@Autowired
	ClaveTeService rTemporal;

	@Autowired
	EmailService rEmail;

	@Autowired
	EmpresaService rEmpresa;

	@Autowired
	AlumnoService rAlumno;

	@PostMapping("/propuesta")
	public void sendEmailWithAttachment(@RequestBody Email email) throws MessagingException, IOException {

		Alumno alu = rAlumno.findById(email.getReceptor().getId());
		Usuario usua = uService.findById(alu.getId_usuario().getId());

		Empresa emp = rEmpresa.findById(email.getEmisor().getId());

		Email correos = new Email();

		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo(usua.getEmail());

		helper.setSubject(email.getAsunto());

		helper.setText(email.getContenido(), true);

		javaMailSender.send(msg);

		correos.setAsunto(email.getAsunto());
		correos.setEmisor(emp);
		correos.setContenido(email.getContenido());
		correos.setReceptor(alu);
		correos.setEstado("A");

		rEmail.save(correos);
	}

	@PostMapping("/temporal")
	public boolean sendPassword(@RequestBody Usuario usu) throws MessagingException, IOException {
		ClaveTemporal ct = new ClaveTemporal();

		Usuario correo = uService.findByEmail(usu.getEmail());

		if (correo.getEmail() != "") {

			String clave = random();

			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(correo.getEmail());

			helper.setSubject("Solicitud de restablecimiento de contraseña");

			helper.setText("Esta es su contraseña temporal <b>" + clave + "</b>", true);

			javaMailSender.send(msg);

			ct.setUsuario(correo);
			ct.setClavet(clave);

			rTemporal.guardar(ct);

			return true;
		} else {
			return false;
		}

	}

}
