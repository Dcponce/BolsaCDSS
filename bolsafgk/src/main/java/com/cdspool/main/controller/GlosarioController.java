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

import com.cdspool.main.model.Glosario;
import com.cdspool.main.repository.IGlosarioRepository;

@RestController
@RequestMapping(value = "glosario")
public class GlosarioController {

	@Autowired
	IGlosarioRepository iGlosa;

	@GetMapping
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public List<Glosario> lista() {
		return (List<Glosario>) iGlosa.findAll();
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public void eliminar(@PathVariable Integer id) {
		iGlosa.deleteById(id);
	}

	@PostMapping
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public void agregar(@RequestBody Glosario glosario) {
		iGlosa.save(glosario);
	}

	@PutMapping
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public void editar(@RequestBody Glosario glosario) {
		iGlosa.save(glosario);
	}

	@GetMapping("/glosa/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public Glosario buscarById(@PathVariable Integer id) {
		return iGlosa.findById(id).get();
	}
}
