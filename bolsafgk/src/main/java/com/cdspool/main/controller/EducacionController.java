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

import com.cdspool.main.model.Certificacion;
import com.cdspool.main.model.Educacion;
import com.cdspool.main.service.EducacionService;

@RestController
@RequestMapping(value = "educacion")
public class EducacionController {

	@Autowired
	EducacionService eService;

	@GetMapping
	@Secured("ROLE_ADMIN")
	public List<Educacion> lista() {

		List<Educacion> list = eService.findAll();

		return list;
	}

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void delete(@PathVariable Integer id) {
		eService.delete(id);
	}

	@PostMapping
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO" })
	public void add(@RequestBody Educacion edu) {
		eService.save(edu);
	}

	@PutMapping
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO" })
	public void update(@RequestBody Educacion edu) {
		eService.save(edu);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("api/certi")
	public List<Certificacion> listaCerty() {

		return eService.findAllCerti();

	}

	@GetMapping("usuario/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO", "ROLE_EMPRESA" })
	public Educacion idUsuario(@PathVariable Integer id) {
		return eService.findByUsuario(id);
	}

}
