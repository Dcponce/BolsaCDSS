package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.service.AlumnoService;

@RestController
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
	
	@GetMapping("alumno/{id}")
	public Alumno idAlumno(@PathVariable Integer id) {
		return sAlumno.findById(id);
	}

	@GetMapping("filter")
	public List<Alumno> filterEmp(@RequestParam(required = false) String depto,
			@RequestParam(required = false) String certi, @RequestParam(required = false) String habil) {

		/************************************************************************************************************/

		Integer departamento = null;

		if (depto.equals("null")) {

		} else {
			departamento = Integer.parseInt(depto);
		}

		/************************************************************************************************************/

		Integer certificacion = null;

		if (certi.equals("null")) {

		} else {
			certificacion = Integer.parseInt(certi);
		}

		/************************************************************************************************************/

		if (habil.equals("")) {

			return sAlumno.filter(departamento, certificacion, null);

		} else {

			String[] items = habil.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

			Integer[] results = new Integer[items.length];

			for (Integer i = 0; i < items.length; i++) {

				try {
					results[i] = Integer.parseInt(items[i]);

				} catch (NumberFormatException nfe) {
					System.err.println("Error al parsear habilidades");
				}
			}

			return sAlumno.filter(departamento, certificacion, results);

		}

		/************************************************************************************************************/

	}
}
