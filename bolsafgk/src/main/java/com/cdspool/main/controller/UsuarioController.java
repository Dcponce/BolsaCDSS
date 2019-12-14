package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/lista")
	public List<Usuario> lista() {
		return uService.findAll();
	}

	@CrossOrigin
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		uService.delete(id);
	}

	@PostMapping()
	public void save(@RequestParam String credencial, String email, String clave, Integer tipo) {

		Credencial cred = uService.findByCodigo(credencial);
		String codigo = cred.getCodigo();
		TipoUsuario usuario = uService.findByIdTipo(tipo);

		if (credencial.equals(codigo)) {
			Usuario usu = new Usuario();
			usu.setEmail(email);
			usu.setClave(clave);
			usu.setId_tipo(usuario);
			usu.setId_credencial(cred);
			usu.setEstado(true);
			usu.setActivo(false);

			uService.save(usu);

		}
	}

	@PutMapping
	public void update(@RequestBody Usuario usu) {
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
	
	@GetMapping("getId/{email}")
	public Usuario getId(@PathVariable String email) {
		return uService.getId(email);
	}

}
