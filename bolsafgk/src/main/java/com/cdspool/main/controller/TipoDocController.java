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

import com.cdspool.main.model.TipoDocumento;
import com.cdspool.main.repository.ITipoDocRepository;

@RestController
@RequestMapping(value = "tipoDoc")
public class TipoDocController {

	@Autowired
	ITipoDocRepository iTipo;

	@GetMapping
	public List<TipoDocumento> lista() {

		List<TipoDocumento> list = (List<TipoDocumento>) iTipo.findAll();

		return list;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		iTipo.deleteById(id);
	}

	@PostMapping
	public void add(@RequestBody TipoDocumento tipodoc) {
		iTipo.save(tipodoc);
	}

	@PutMapping
	public void update(@RequestBody TipoDocumento tipodoc) {
		iTipo.save(tipodoc);
	}

}
