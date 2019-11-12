package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuarios;
import com.cdspool.main.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuariosControlador {

	@Autowired
	UsuarioService sUsuarios;
	
	@GetMapping
	public List<Usuarios> lis(){
		return (List<Usuarios>) sUsuarios.listar();
	}
	
	@GetMapping("api/tipo")
	public List<TipoUsuario>lisT(){
		return (List<TipoUsuario>) sUsuarios.lis();
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		sUsuarios.eliminar(id);
	}
	
	@PostMapping
	public void agregar(@RequestBody Usuarios usuarios) {
		sUsuarios.guardar(usuarios);
	}
	
	@PutMapping
	public void actualizar(@RequestBody Usuarios usuarios) {
		sUsuarios.guardar(usuarios);
	}
}
