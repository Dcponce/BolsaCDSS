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

import com.cdspool.main.model.Usuario;
import com.cdspool.main.model.Alumno;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.service.AlumnoService;

@RestController
@RequestMapping(value = "alumnos")
public class AlumnoController {

	@Autowired
	AlumnoService sAlumno;
	
	@GetMapping
	public List<Alumno> lista(){
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
	
	@GetMapping("api/listaUsu")
	public List<Usuario> listaUsua(){
		return (List<Usuario>) sAlumno.findAllUsua();
	}
	
	@GetMapping("api/listaMuni")
	public List<Municipios> listaMuni(){
		return (List<Municipios>) sAlumno.findAllMuni();
	}
	
	
}
