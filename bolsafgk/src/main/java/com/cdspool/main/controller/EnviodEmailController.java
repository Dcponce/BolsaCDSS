package com.cdspool.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import org.springframework.security.access.annotation.Secured;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping(value = "envio")
public class EnviodEmailController {

	// Generar Clave Temporal
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

	// Envio de Propuesta Laboral
	@PostMapping("/propuesta")
	@Secured("ROLE_EMPRESA")
	public void sendEmailWithAttachment(@RequestBody Email email, @RequestParam String contacto, String emailC, String puesto,
			String salario, String direccion, String link, String info) throws MessagingException, IOException {

		Alumno alu = rAlumno.findById(email.getReceptor().getId());
		Usuario usua = uService.findById(alu.getId_usuario().getId());

		Empresa emp = rEmpresa.findByUsuario(email.getEmisor().getId());

		Email correos = new Email();

		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		// Maquetado de Correo Electronico
		String contenido = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"    <title>Active - Responsive Email Template - W3Layouts</title>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
				"    <meta name=\"keywords\" content=\"Email Templates, Newsletters, Marketing Email templates, \r\n" + 
				"	Webdesign, free Newsletter\" />\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"    <style>\r\n" + 
				"        :root {\r\n" + 
				"            --theme-color: #0B8E6A;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        html {\r\n" + 
				"            scroll-behavior: smooth;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .img-responsive {\r\n" + 
				"            max-width: 100%;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .img-icon {\r\n" + 
				"            max-width: 100%;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        body,\r\n" + 
				"        html {\r\n" + 
				"            margin: 0;\r\n" + 
				"            padding: 0;\r\n" + 
				"            font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto,\r\n" + 
				"                Oxygen-Sans, Ubuntu, Cantarell, \"Helvetica Neue\", sans-serif;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        * {\r\n" + 
				"            box-sizing: border-box;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"        a {\r\n" + 
				"            text-decoration: none;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .left-gap-to-view {\r\n" + 
				"            margin-left: 40px;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        a.facebook {\r\n" + 
				"            background: #3b5998;\r\n" + 
				"            color: #fff;\r\n" + 
				"            font-size: 14px;\r\n" + 
				"            padding: 15px 35px;\r\n" + 
				"            border-radius: 4px;\r\n" + 
				"            display: inline-block;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        a.twitter {\r\n" + 
				"            background: #1da1f2;\r\n" + 
				"            color: #fff;\r\n" + 
				"            font-size: 14px;\r\n" + 
				"            padding: 15px 35px;\r\n" + 
				"            border-radius: 4px;\r\n" + 
				"            display: inline-block;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        a.facebook:hover,\r\n" + 
				"        a.twitter:hover {\r\n" + 
				"            opacity: 0.9;\r\n" + 
				"            transition: 0.3s ease;\r\n" + 
				"            ;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        a.facebook img,\r\n" + 
				"        a.twitter img {\r\n" + 
				"            margin-right: 6px;\r\n" + 
				"            vertical-align: middle;\r\n" + 
				"            width: 16px;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        @media only screen and (max-width: 600px) {\r\n" + 
				"            .left-gap-to-view {\r\n" + 
				"                margin-left: 0px;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            table[class=w3l-scale] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            table[class=w3l-scale-300] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                height: 300px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            table[class=w3l-scale-90] {\r\n" + 
				"                width: 90% !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-left] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: left !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-height] {\r\n" + 
				"                height: 70px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-left-bottom] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: left !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-left-top] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: left !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-left-all] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: left !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-both] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-left: 20px !important;\r\n" + 
				"                padding-right: 20px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-bottom] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-top] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-all] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"                padding-left: 20px !important;\r\n" + 
				"                padding-right: 20px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-right] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: right !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-right-bottom] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: right !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-right-top] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: right !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-right-all] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: right !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-bottom-both] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-bottom: 24px !important;\r\n" + 
				"                padding-left: 20px !important;\r\n" + 
				"                padding-right: 20px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-top-both] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-top: 24px !important;\r\n" + 
				"                padding-left: 20px !important;\r\n" + 
				"                padding-right: 20px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=reset] {\r\n" + 
				"                height: 0px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            td[class=w3l-scale-center-topextra] {\r\n" + 
				"                width: 100% !important;\r\n" + 
				"                text-align: center !important;\r\n" + 
				"                padding-top: 84px !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            img[class=\"reset\"] {\r\n" + 
				"                display: inline !important;\r\n" + 
				"            }\r\n" + 
				"\r\n" + 
				"            img[class=\"scale-inline\"] {\r\n" + 
				"                display: inline !important;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
				"        style=\"background-color: rgb(242, 152, 0);\" width=\"100%\">\r\n" + 
				"        <tr>\r\n" + 
				"            <td height=\"75\">&nbsp;</td>\r\n" + 
				"        </tr>\r\n" + 
				"    </table>\r\n" + 
				"\r\n" + 
				"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
				"        style=\"background-color: rgb(242, 152, 0);\" width=\"100%\">\r\n" + 
				"        <tr>\r\n" + 
				"            <td>\r\n" + 
				"                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
				"                    style=\"background-color: #FFFFFF\" width=\"620\">\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td height=\"50\">&nbsp;</td>\r\n" + 
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"center\">\r\n" + 
				"                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale-90\"\r\n" + 
				"                                width=\"490\">\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"font-weight: bold;\">\r\n" + 
				"                                        <h1><span style=\"color: rgb(242, 152, 0);\">Job </span><span style=\"color: rgb(184, 202, 0);\">Applicant </span> <span style=\"color: rgb(225, 0, 26);\">Pool</span></h1>\r\n" + 
				"                                    </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td height=\"1\" style=\"background-color: #E8E8E8\"></td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"color: rgb(123, 124, 126); font-weight: bold;\">\r\n" + 
				"                                        <h2><b>¡La empresa <span style=\"color: rgb(225, 0, 26);\">"+emp.getNombre()+"</span> le comparte la siguiente oferta!</b></h2>\r\n" + 
				"                                    </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td height=\"1\" style=\"background-color: #E8E8E8\"></td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"color: #1c1c1c; line-height: 24px;font-size: 15px;\">\r\n" + 
				"<p>Agradeceríamos te comunicaras al correo que aparece en la oferta para que la empresa\r\n" + 
				"\r\n" + 
				"sepa si estás interesado o no en aceptar la plaza.</p>"+
				"                                        <h2 style=\"color: rgb(245, 152, 1)\">"+puesto+"</h2>\r\n" + 
				"                                        <label><b>Descripcion del puesto:</b></label>\r\n" + 
				"                                        <p>"+email.getContenido()+"</p>\r\n" + 
				"                                        <p><b>Salario: </b>"+salario+"</p>\r\n" + 
				"                                        <label><b>Contacto:</b></label>\r\n" + 
				"                                        <p>"+contacto+"</p>\r\n" + 
				"                                        <label><b>Correo de Contacto:</b></label>\r\n" + 
				"                                        <p>"+emailC+"</p>\r\n" +
				"                                        <label><b>Datos de entrevista:</b></label>\r\n" +
				"                                        <p>"+info+"</p>\r\n" +
				"                                        <label><b>Direccion:</b></label>\r\n" +
				"                                        <p><a href=\""+link+"\" style=\"text-decoration: none; color: rgb(225, 0, 26)\">"+direccion+"</a></p>\r\n" +
				"                                    </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"font-size: 17px; color: #1c1c1c; line-height: 24px;\">\r\n" + 
				"                                       \r\n" + 
				"                                    </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                            </table>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td height=\"50\" valign=\"bottom\">\r\n" + 
				"                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale-90\"\r\n" + 
				"                                width=\"550\">\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td height=\"1\" style=\"background-color: #E8E8E8\"></td>\r\n" + 
				"                                </tr>\r\n" + 
				"                            </table>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                </table>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"    </table>\r\n" + 
				"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
				"        style=\"background-color: rgb(242, 152, 0);\" width=\"100%\">\r\n" + 
				"        <tr>\r\n" + 
				"            <td>\r\n" + 
				"                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
				"                    style=\"background-color: #FFFFFF\" width=\"620\">\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td align=\"center\">\r\n" + 
				"                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
				"                                width=\"560\">\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td height=\"10\">&nbsp;</td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td height=\"5\" style=\"font-size: 1px;\">&nbsp;</td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"font-size: 12px; color: #9b9a9c; line-height: 18px;\">\r\n" + 
				"                                        Copyright © 2020. Todos los derechos reservados<a\r\n" + 
				"                                            style=\"color: #1d44b8; font-weight: bold;\" target=\"_blanck\"\r\n" + 
				"                                            href=\"https://www.fundaciongloriakriete.org/\">&nbsp;FGK</a></td>\r\n" + 
				"                                </tr>\r\n" + 
				"                            </table>\r\n" + 
				"                        </td>\r\n" + 
				"                    </tr>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <td height=\"50\">&nbsp;</td>\r\n" + 
				"                    </tr>\r\n" + 
				"                </table>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td height=\"75\">&nbsp;</td>\r\n" + 
				"        </tr>\r\n" + 
				"    </table>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		String cont = "Puesto: " + puesto + ", Descripción: " + email.getContenido() + " , Salario: " + salario
				+ ", Contacto: " + contacto + " , Correo de contacto: " + emailC + ", Dirección: " + direccion + ", Como llegar: " + link + " ,Más detalles: "
				+ info+"";
		

		helper.setTo(usua.getEmail());

		helper.setSubject(email.getAsunto());

		helper.setText(contenido, true);

		javaMailSender.send(msg);

		correos.setAsunto(email.getAsunto());
		correos.setEmisor(emp);
		correos.setContenido(cont);
		correos.setReceptor(alu);
		correos.setEstado("A");

		rEmail.save(correos);
	}

	// Envio de Clave Temporal
	@PostMapping("/temporal")
	public boolean sendPassword(@RequestBody Usuario usu) throws MessagingException, IOException {
		ClaveTemporal ct = new ClaveTemporal();

		Usuario correo = uService.findByEmail(usu.getEmail());

		if (correo.getEmail() != "") {

			String clave = random();
			
			// Maquetado de Correo Electronico
			String contenido = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"    <title>Active - Responsive Email Template - W3Layouts</title>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
					"    <meta name=\"keywords\" content=\"Email Templates, Newsletters, Marketing Email templates, \r\n" + 
					"	Webdesign, free Newsletter\" />\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body>\r\n" + 
					"    <style>\r\n" + 
					"        :root {\r\n" + 
					"            --theme-color: #0B8E6A;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        html {\r\n" + 
					"            scroll-behavior: smooth;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        .img-responsive {\r\n" + 
					"            max-width: 100%;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        .img-icon {\r\n" + 
					"            max-width: 100%;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        body,\r\n" + 
					"        html {\r\n" + 
					"            margin: 0;\r\n" + 
					"            padding: 0;\r\n" + 
					"            font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto,\r\n" + 
					"                Oxygen-Sans, Ubuntu, Cantarell, \"Helvetica Neue\", sans-serif;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        * {\r\n" + 
					"            box-sizing: border-box;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"        a {\r\n" + 
					"            text-decoration: none;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        .left-gap-to-view {\r\n" + 
					"            margin-left: 40px;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        a.facebook {\r\n" + 
					"            background: #3b5998;\r\n" + 
					"            color: #fff;\r\n" + 
					"            font-size: 14px;\r\n" + 
					"            padding: 15px 35px;\r\n" + 
					"            border-radius: 4px;\r\n" + 
					"            display: inline-block;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        a.twitter {\r\n" + 
					"            background: #1da1f2;\r\n" + 
					"            color: #fff;\r\n" + 
					"            font-size: 14px;\r\n" + 
					"            padding: 15px 35px;\r\n" + 
					"            border-radius: 4px;\r\n" + 
					"            display: inline-block;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        a.facebook:hover,\r\n" + 
					"        a.twitter:hover {\r\n" + 
					"            opacity: 0.9;\r\n" + 
					"            transition: 0.3s ease;\r\n" + 
					"            ;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        a.facebook img,\r\n" + 
					"        a.twitter img {\r\n" + 
					"            margin-right: 6px;\r\n" + 
					"            vertical-align: middle;\r\n" + 
					"            width: 16px;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        @media only screen and (max-width: 600px) {\r\n" + 
					"            .left-gap-to-view {\r\n" + 
					"                margin-left: 0px;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            table[class=w3l-scale] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            table[class=w3l-scale-300] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                height: 300px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            table[class=w3l-scale-90] {\r\n" + 
					"                width: 90% !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-left] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: left !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-height] {\r\n" + 
					"                height: 70px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-left-bottom] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: left !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-left-top] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: left !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-left-all] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: left !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-both] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-left: 20px !important;\r\n" + 
					"                padding-right: 20px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-bottom] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-top] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-all] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"                padding-left: 20px !important;\r\n" + 
					"                padding-right: 20px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-right] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: right !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-right-bottom] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: right !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-right-top] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: right !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-right-all] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: right !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-bottom-both] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-bottom: 24px !important;\r\n" + 
					"                padding-left: 20px !important;\r\n" + 
					"                padding-right: 20px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-top-both] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-top: 24px !important;\r\n" + 
					"                padding-left: 20px !important;\r\n" + 
					"                padding-right: 20px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=reset] {\r\n" + 
					"                height: 0px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            td[class=w3l-scale-center-topextra] {\r\n" + 
					"                width: 100% !important;\r\n" + 
					"                text-align: center !important;\r\n" + 
					"                padding-top: 84px !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            img[class=\"reset\"] {\r\n" + 
					"                display: inline !important;\r\n" + 
					"            }\r\n" + 
					"\r\n" + 
					"            img[class=\"scale-inline\"] {\r\n" + 
					"                display: inline !important;\r\n" + 
					"            }\r\n" + 
					"        }\r\n" + 
					"    </style>\r\n" + 
					"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\" style=\"background-color: rgb(242, 152, 0);\"\r\n" + 
					"        width=\"100%\">\r\n" + 
					"        <tr>\r\n" + 
					"            <td height=\"75\">&nbsp;</td>\r\n" + 
					"        </tr>\r\n" + 
					"    </table>\r\n" + 
					"\r\n" + 
					"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\" style=\"background-color: rgb(242, 152, 0);\"\r\n" + 
					"        width=\"100%\">\r\n" + 
					"        <tr>\r\n" + 
					"            <td>\r\n" + 
					"                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
					"                    style=\"background-color: #FFFFFF\" width=\"620\">\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td height=\"50\">&nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td align=\"center\">\r\n" + 
					"                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale-90\"\r\n" + 
					"                                width=\"490\">\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td align=\"center\" style=\"font-weight: bold;\">\r\n" + 
					"                                        <h1><span style=\"color: rgb(242, 152, 0);\">Job </span><span style=\"color: rgb(184, 202, 0);\">Applicant </span> <span style=\"color: rgb(225, 0, 26);\">Pool</span></h1>\r\n" + 
					"                                    </td>\r\n" + 
					"                                </tr>\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td height=\"1\" style=\"background-color: #E8E8E8\"></td>\r\n" + 
					"                                </tr>\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td align=\"center\" style=\"color: rgb(123, 124, 126); font-weight: bold;\">\r\n" + 
					"                                        <h2>¡Olvide mi Contraseña!</h2>\r\n" + 
					"                                    </td>\r\n" + 
					"                                </tr>\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td align=\"center\" style=\"color: #1c1c1c; line-height: 24px;\">\r\n" + 
					"                                       <p>Su nueva contraseña es: <strong>"+clave+"</strong></p> \r\n" + 
					"                                    </td>\r\n" + 
					"                                </tr>\r\n" + 
					"                            </table>\r\n" + 
					"                        </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td height=\"50\" valign=\"bottom\">\r\n" + 
					"                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale-90\"\r\n" + 
					"                                width=\"550\">\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td height=\"1\" style=\"background-color: #E8E8E8\"></td>\r\n" + 
					"                                </tr>\r\n" + 
					"                            </table>\r\n" + 
					"                        </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                </table>\r\n" + 
					"            </td>\r\n" + 
					"        </tr>\r\n" + 
					"    </table>\r\n" + 
					"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\" style=\"background-color: rgb(242, 152, 0);\"\r\n" + 
					"        width=\"100%\">\r\n" + 
					"        <tr>\r\n" + 
					"            <td>\r\n" + 
					"                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
					"                    style=\"background-color: #FFFFFF\" width=\"620\">\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td align=\"center\">\r\n" + 
					"                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"w3l-scale\"\r\n" + 
					"                                width=\"560\">\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td height=\"10\">&nbsp;</td>\r\n" + 
					"                                </tr>\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td height=\"5\" style=\"font-size: 1px;\">&nbsp;</td>\r\n" + 
					"                                </tr>\r\n" + 
					"                                <tr>\r\n" + 
					"                                    <td align=\"center\" style=\"font-size: 12px; color: #9b9a9c; line-height: 18px;\">\r\n" + 
					"                                        Copyright © 2020. Todos los derechos reservados<a\r\n" + 
					"                                            style=\"color: #1d44b8; font-weight: bold;\" target=\"_blanck\"\r\n" + 
					"                                            href=\"https://www.fundaciongloriakriete.org/\">&nbsp;FGK</a></td>\r\n" + 
					"                                </tr>\r\n" + 
					"                            </table>\r\n" + 
					"                        </td>\r\n" + 
					"                    </tr>\r\n" + 
					"                    <tr>\r\n" + 
					"                        <td height=\"50\">&nbsp;</td>\r\n" + 
					"                    </tr>\r\n" + 
					"                </table>\r\n" + 
					"            </td>\r\n" + 
					"        </tr>\r\n" + 
					"        <tr>\r\n" + 
					"            <td height=\"75\">&nbsp;</td>\r\n" + 
					"        </tr>\r\n" + 
					"    </table>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";

			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(correo.getEmail());

			helper.setSubject("Solicitud de restablecimiento de contraseña");

			helper.setText(contenido, true);

			javaMailSender.send(msg);

			// Se guarda la clave temporal y el usuario 
			ct.setUsuario(correo);
			ct.setClavet(clave);

			rTemporal.guardar(ct);

			return true;
		} else {
			return false;
		}

	}

}
