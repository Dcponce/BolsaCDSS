package com.cdspool.main.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

	@GetMapping()
	public List<Usuario> lista() {
		return uService.findAll();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		uService.delete(id);
	}

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

	@PostMapping("ingreso")
	public void saveAlumno(@RequestBody Usuario usu) {
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

	@PutMapping()
	public void updateUsu(@RequestBody Usuario usu) {
		uService.save(usu);
	}

	@PostMapping("/activacion")
	public void sendEmailWithAttachment(@RequestBody Usuario usu) throws MessagingException, IOException {

		Usuario usua = uService.findByEmail(usu.getEmail());

		String asunto = "Activacion de Usuario";

		String contenido = "<a href='http://localhost:8080/usuarios/usuario/" + usua.getId() + "'>Dale Click</a>";

		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo(usua.getEmail());

		helper.setSubject(asunto);

		helper.setText(contenido, true);

		javaMailSender.send(msg);

	}

	@GetMapping("usuario/{id}")
	public void findByEmail(@PathVariable Integer id) throws URISyntaxException {

		Usuario usuario = uService.findById(id);
		update(usuario);

	}

	public void update(Usuario usu) throws URISyntaxException {

		usu.setActivo("true");
		uService.save(usu);
	}

	@GetMapping("api/listaTipo")
	public List<TipoUsuario> listaTipo() {
		return uService.findAllTipo();
	}

	@GetMapping("api/cred")
	public List<Credencial> findAllCred() {
		return uService.findAllCred();
	}

	@GetMapping("usu/{codigo}")
	public Credencial byCodigo(@PathVariable String codigo) {
		return uService.findByCodigo(codigo);
	}

	@GetMapping("getId/{email}")
	public Usuario getId(@PathVariable String email) {
		return uService.findByEmail(email);
	}

	@GetMapping("getUsu/{id}")
	public Usuario findById(@PathVariable Integer id) {
		return uService.findById(id);
	}

}
