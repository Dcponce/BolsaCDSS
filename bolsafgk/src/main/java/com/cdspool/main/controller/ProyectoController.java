package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Proyecto;
import com.cdspool.main.service.ProyectoService;

@RestController
@RequestMapping(value = "proyecto")
public class ProyectoController {

	@Autowired
	ProyectoService sProyecto;

	// Listar Proyectos
	@GetMapping
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO" })
	public List<Proyecto> listar() {
		return (List<Proyecto>) sProyecto.listar();
	}

	// Eliminar Proyecto
	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO" })
	public void eliminar(@PathVariable Integer id) {
		sProyecto.eliminar(id);
	}

	// Agregar Proyecto
	@PostMapping
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO" })
	public void agregar(@RequestBody Proyecto proyecto) {
		sProyecto.agregar(proyecto);
	}

	// Actualizar Proyecto
	@PutMapping
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO" })
	public void Actualizar(@RequestBody Proyecto proyecto) {
		sProyecto.agregar(proyecto);
	}

	// Listar Proyectos por id Usuario
	@GetMapping("usuario/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO", "ROLE_EMPRESA" })
	public List<Proyecto> idUsuario(@PathVariable Integer id) {
		return sProyecto.findByUsuario(id);
	}

}
