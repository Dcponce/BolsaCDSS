package com.cdspool.main.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Credencial;
import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuario;

import com.cdspool.main.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService uService;

	@Autowired
	private JavaMailSender javaMailSender;

	// Listar Usuarios
	@GetMapping()
	@Secured("ROLE_ADMIN")
	public List<Usuario> lista() {
		return uService.findAll();
	}

	// Eliminar Usuario
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void delete(@PathVariable Integer id) {
		uService.delete(id);
	}

	// Agregar Usuario
	@PostMapping()
	public void save(@RequestBody Usuario usu) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		Usuario usua = new Usuario();

		usua.setEmail(usu.getEmail());
		usua.setId_credencial(usu.getId_credencial());
		usua.setClave(bCryptPasswordEncoder.encode(usu.getClave()));
		usua.setId_tipo(usu.getId_tipo());
		usua.setEstado(usu.getEstado());
		usua.setActivo(usu.getActivo());

		uService.save(usua);
	}

	// Editar Usuario(Administrador)
	@PutMapping()
	public void updateUsu(@RequestBody Usuario usu) {
		Usuario usuario = uService.findById(usu.getId());
		
		Usuario usua = new Usuario();
		
		usua.setId(usuario.getId());
		usua.setEmail(usuario.getEmail());
		usua.setId_credencial(usuario.getId_credencial());
		usua.setClave(usuario.getClave());
		usua.setId_tipo(usuario.getId_tipo());
		if(usuario.getEstado()== true) {
			usua.setEstado(false);
		}else {
			usua.setEstado(true);
		}
		
		usua.setActivo(usuario.getActivo());
		
		uService.save(usua);
	}

	// Envio de Activacion de Usuario
	@PostMapping("/activacion")
	public Usuario sendEmailWithAttachment(@RequestBody Usuario usu) throws MessagingException, IOException {

		Usuario usua = uService.findByEmail(usu.getEmail());

		String asunto = "Activacion de Usuario";

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
				"                                        <h2>Activación de Usuario</h2>\r\n" + 
				"                                    </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"color: #1c1c1c; line-height: 24px;\">\r\n" + 
				"                                       <p>Para poder activar el usuario se hace la respectiva validación de\r\n" + 
				"                                        correo electronico. De click en el enlace para activar su usuario.</p> \r\n" + 
				"                                    </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                                <tr>\r\n" + 
				"                                    <td align=\"center\" style=\"font-size: 17px; color: #1c1c1c; line-height: 24px;\">\r\n" + 
				"                                        <a href='http://jobapplicantpool.com/activacion.html?id="+usua.getId()+"'>Click Aquí</a>\r\n" + 
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
				"                                        Copyright © 2019. Todos los derechos reservados<a\r\n" + 
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

		helper.setTo(usua.getEmail());

		helper.setSubject(asunto);

		helper.setText(contenido, true);

		javaMailSender.send(msg);

		return usua;
		
	}
	
	// Autorizacion de Alumno
	@GetMapping("autAlumno")
	@Secured("ROLE_ALUMNO")
	public String autAlumno() {
		return "{'mensaje':'Bienvenido Alumno'}";
	}

	// Autorizacion de Empresa
	@GetMapping("autEmpresa")
	@Secured("ROLE_EMPRESA")
	public String autEmpresa() {
		return "{'mensaje':'Bienvenido Empresa'}";
	}

	// Autorizacion de Administrador
	@GetMapping("autAdmin")
	@Secured("ROLE_ADMIN")
	public String autAdmin() {
		return "{'mensaje':'Bienvenido Administrador'}";
	}

	// Activacion de Usuario
	@GetMapping("usuario/{id}")
	public void findByEmail(@PathVariable Integer id) throws URISyntaxException {

		Usuario usuario = uService.findById(id);
		update(usuario);

	}

	// Cambio de Activo para Activar el Usuario
	public void update(Usuario usu) throws URISyntaxException {
		
		usu.setActivo("true");
		uService.save(usu);
		
	}

	// Reemplazar Clave por Clave Temporal
	@PutMapping("clavet")
	public void UpdateClavet(@RequestBody Usuario usu) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		Usuario usuario = new Usuario();

		usuario.setId(usu.getId());
		usuario.setEmail(usu.getEmail());
		usuario.setId_credencial(usu.getId_credencial());
		usuario.setClave(bCryptPasswordEncoder.encode(usu.getClave()));
		usuario.setId_tipo(usu.getId_tipo());
		usuario.setEstado(usu.getEstado());
		usuario.setActivo(usu.getActivo());

		uService.save(usuario);

	}
	
	// Editar Usuario (Empresa o Alumno)
	@PutMapping("editU")
	public void edit(@RequestBody Usuario usu) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		Usuario usua = uService.findById(usu.getId());
		
		Usuario usuario = new Usuario();

		usuario.setId(usu.getId());
		usuario.setEmail(usu.getEmail());
		usuario.setId_credencial(usu.getId_credencial());
		
		if(usua.getClave().equals(usu.getClave())) {
			usuario.setClave(usu.getClave());
			
		}else {
			usuario.setClave(bCryptPasswordEncoder.encode(usu.getClave()));
		}
			
		usuario.setId_tipo(usu.getId_tipo());
		usuario.setEstado(true);
		usuario.setActivo("false");

		uService.save(usuario);

	}

	// Listar Tipos de Usuarios 
	@GetMapping("api/listaTipo")
	public List<TipoUsuario> listaTipo() {
		return uService.findAllTipo();
	}

	// Lista de Credenciales
	@GetMapping("api/cred")
	public List<Credencial> findAllCred() {
		return uService.findAllCred();
	}

	// Listar Credencial por codigo
	@GetMapping("usu/{codigo}")
	public Credencial byCodigo(@PathVariable String codigo) {
		return uService.findByCodigo(codigo);
	}

	// Listar Usuario por Email
	@GetMapping("getId/{email}")
	public Usuario getId(@PathVariable String email) {
		return uService.findByEmail(email);
	}

	// Listar Usuario por id
	@GetMapping("getUsu/{id}")
	public Usuario findById(@PathVariable Integer id) {
		return uService.findById(id);
	}

}
