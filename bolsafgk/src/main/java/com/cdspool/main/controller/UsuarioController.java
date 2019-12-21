package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@GetMapping()
	public List<Usuario> lista() {
		return uService.findAll();
	}

	@CrossOrigin
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
	
	@PostMapping("alumno")
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
	
	@PostMapping("empresa")
	public void saveEmpresa(@RequestBody Usuario usu) {
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

	@GetMapping("usu/{codigo}")
	public Credencial byCodigo(@PathVariable String codigo) {
		return uService.findByCodigo(codigo);
	}

	@GetMapping("getId/{email}")
	public Usuario getId(@PathVariable String email) {
		return uService.getId(email);
	}
}
