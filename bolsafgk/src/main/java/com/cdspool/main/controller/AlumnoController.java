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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.service.AlumnoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(value = "alumnos")
public class AlumnoController {

	@Autowired
	AlumnoService sAlumno;

	@GetMapping
	public List<Alumno> lista() {
		return (List<Alumno>) sAlumno.findAll();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		sAlumno.delete(id);
	}

	@PostMapping
	public void add(@RequestBody Alumno alumno) {
		sAlumno.save(alumno);
	}

	@PutMapping
	public void update(@RequestBody Alumno alumno) {
		sAlumno.save(alumno);
	}

	@GetMapping("usuario/{id}")
	public Alumno idUsuario(@PathVariable Integer id) {
		return sAlumno.findByUsuario(id);
	}
}
