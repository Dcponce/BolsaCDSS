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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.service.AlumnoService;

@RestController
@RequestMapping(value = "alumnos")
public class AlumnoController {

	@Autowired
	AlumnoService sAlumno;

	// Listar Alumnos
	@GetMapping
	@Secured({"ROLE_ADMIN","ROLE_EMPRESA"})
	public List<Alumno> lista() {
		return (List<Alumno>) sAlumno.findAll();
	}

	// Eliminar Alumno
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void delete(@PathVariable Integer id) {
		sAlumno.delete(id);
	}

	// Ingresar Alumnos
	@PostMapping
	@Secured({ "ROLE_ALUMNO", "ROLE_ADMIN" })
	public void add(@RequestBody Alumno alumno) {
		sAlumno.save(alumno);
	}

	// Editar Alumnos
	@PutMapping
	@Secured({ "ROLE_ALUMNO", "ROLE_ADMIN" })
	public void update(@RequestBody Alumno alumno) {
		sAlumno.save(alumno);
	}

	// Listar Alumno por id Usuario
	@GetMapping("usuario/{id}")
	@Secured({ "ROLE_ALUMNO", "ROLE_ADMIN" })
	public Alumno idUsuario(@PathVariable Integer id) {
		return sAlumno.findByUsuario(id);
	}

	// Listar Alumno por id
	@GetMapping("alumno/{id}")
	public Alumno idAlumno(@PathVariable Integer id) {
		return sAlumno.findById(id);
	}

	// Filtro de Alumnos
	@GetMapping("filter")
	@Secured({ "ROLE_EMPRESA", "ROLE_ADMIN" })
	public List<Alumno> filterEmp(@RequestParam(required = false) String depto,
			@RequestParam(required = false) String certi, @RequestParam(required = false) String habil) {

		/************************************************************************************************************/

		Integer departamento = null;

		if (depto.equals("null") || depto.equals("") || depto == null) {

		} else {
			departamento = Integer.parseInt(depto);
		}

		/************************************************************************************************************/

		Integer certificacion = null;

		if (certi.equals("null") || certi.equals("") || certi == null) {

		} else {
			certificacion = Integer.parseInt(certi);
		}

		/************************************************************************************************************/

		if (habil.equals("") || habil.equals("null") || habil == null) {

			if (departamento == null && certificacion == null) {

				return sAlumno.findAll();
				
			} else {
				return sAlumno.filter(departamento, certificacion, null);
			}

		} else {

			// Se recibe un arreglo de tipo String y se convierte a un arreglo de tipo
			// Integer

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
