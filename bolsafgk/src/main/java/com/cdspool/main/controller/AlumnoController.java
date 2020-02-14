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
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
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
			@RequestParam(required = false) String certi, @RequestParam(required = false) String habil,
			@RequestParam(required = false) String cent) {

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

		Integer centro = null;

		if (cent.equals("null") || cent.equals("") || cent == null) {

		} else {
			centro = Integer.parseInt(cent);

		}

		/************************************************************************************************************/

		Integer[] results = null;

		if (habil != "" && habil != "null" && habil != null) {

			// Se recibe un arreglo de tipo String y se convierte a un arreglo de tipo
			// Integer

			String[] items = habil.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

			results = new Integer[items.length];

			for (Integer i = 0; i < items.length; i++) {

				try {
					results[i] = Integer.parseInt(items[i]);

				} catch (NumberFormatException nfe) {
					System.err.println("Error al parsear habilidades");
				}
			}

		}
		
		/************************************************************************************************************/
		
		if(departamento == null && certificacion == null && centro == null && results == null) {
			
			return sAlumno.findAll();
			
		}else if(departamento != null && certificacion != null && centro != null && results != null) {
			
			return sAlumno.filter2(departamento, certificacion, results, centro);
			
		}else if(departamento != null && certificacion != null && centro != null) {
			
			return sAlumno.filter3(certificacion, departamento, centro);
			
		}else if(results != null && certificacion != null && departamento != null) {
			
			return sAlumno.filter4(certificacion, departamento, results);
			
		}else if(results != null && centro != null && departamento != null) {
			
			return sAlumno.filter5(departamento, results, centro);
			
		}else if(results != null && centro != null && certificacion != null) {
			
			return sAlumno.filter6(results, centro, certificacion);
			
		}else if(departamento != null && certificacion != null){
			
			return sAlumno.filter7(departamento, certificacion);
			
		}else if(certificacion != null && centro != null) {
			
			return sAlumno.filter8(certificacion, centro);
			
		}else if(centro != null && departamento != null) {
			
			return sAlumno.filter9(centro, departamento);
			
		}else if(results != null && certificacion != null) {
			
			return sAlumno.filter10(results, certificacion);
			
		}else if(results != null && departamento != null) {
			
			return sAlumno.filter11(results, departamento);
			
		}else if(results != null && centro != null) {
			
			return sAlumno.filter12(results, centro);
			
		}else {
			
			return sAlumno.filter(departamento, certificacion, results, centro);
		}

	}
}
