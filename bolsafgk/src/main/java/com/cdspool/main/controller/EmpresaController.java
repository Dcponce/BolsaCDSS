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

import com.cdspool.main.model.Departamentos;
import com.cdspool.main.model.Empresa;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IDepartamentosRepository;
import com.cdspool.main.service.EmpresaService;
import com.cdspool.main.service.MunicipiosService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "empresa")
public class EmpresaController {

	
	@Autowired//Manda a llamar servicio de empresa
	EmpresaService empresaService;
	
	@Autowired//Manda a llamar el servicio de municipio
	MunicipiosService sMunicipio;
	
    @Autowired//Manda a llamar el repositorio de departamento
    IDepartamentosRepository idepa;
	
	//Ejecución del metodo listar
	@GetMapping
	public List<Empresa> listar(){
		return empresaService.listar();
	}
	
	//Ejecución del metodo agregar
	@PostMapping
	public void addEm(@RequestBody Empresa empresa) {
		empresaService.saveEmp(empresa);
	}
	
	//Ejecución del metodo editar
	@PutMapping
	public void editEm(@RequestBody Empresa empresa) {
		empresaService.saveEmp(empresa);
	}
	
	//Ejecución del metodo eliminar 
	@DeleteMapping("/{id}")
	public void deleteEm(@PathVariable Integer id) {
		empresaService.deleteEmp(id);
	}
	
	///Ejecución del metodo buscar el id de empresa
	@GetMapping("/empresa/{id}")
	public Empresa getEmpresa(@PathVariable Integer id) {
		return empresaService.findById(id);
	}
	
	//METODOS DE LAS ENTIDDES DEPARTAMENTOs Y MUNICIPIOS
	@GetMapping("/municipios/{id}")
	public List<Municipios> getMunicipios(@PathVariable Integer id){
		return sMunicipio.listar();
	}
	
	@GetMapping("/municipio/{id}")
	public Municipios getMunicipio(@PathVariable Integer id) {
		return sMunicipio.porMunicipio(id);
	}
	
	@GetMapping("/departamentos")
	public List<Departamentos> getDepartamento(){
		return sMunicipio.lista();
	}
	
	@GetMapping("/departamento/{id}")
	public Departamentos getDepartamento(@PathVariable Integer id) {
		return sMunicipio.porDepartamentos(id);
	}
	
	//Ejecución del metodo listar usuario
	@GetMapping("/usu")
	public List<Usuario> listarm(){
		return empresaService.findAllUsua();
	}
		
	//Ejecución del metodo buscar el id de usuario
	@GetMapping("/usua/{id}")
	public Usuario getUsuario(@PathVariable Integer id) {
		return empresaService.findByIdUsua(id);
	}
}