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

import com.cdspool.main.model.Documento;
import com.cdspool.main.model.TipoDocumento;
import com.cdspool.main.repository.ITipoDocRepository;
import com.cdspool.main.service.DocumentoService;

@RestController
@RequestMapping(value = "documento")
public class DocumentoController {
	
	@Autowired
	DocumentoService dService;
	
	@Autowired
	ITipoDocRepository itipo;
	
	@GetMapping
	public List<Documento> lista(){
		
		List<Documento> list = dService.findAll();
		
		return list;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		dService.delete(id);
	}
	
	@PostMapping
	public void add(@RequestBody Documento doc) {
		dService.save(doc);
	}
	
	@PutMapping
	public void update(@RequestBody Documento doc) {
		dService.save(doc);
	}
	
	@GetMapping("api/tipodoc")
	public List<TipoDocumento> TipoDocList(){
		
		List<TipoDocumento> list = (List<TipoDocumento>) itipo.findAll();
		
		return list;
	}
	

}
