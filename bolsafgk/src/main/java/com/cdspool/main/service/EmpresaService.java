package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Empresa;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IEmpresaRepository;
import com.cdspool.main.repository.IMunicipiosRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class EmpresaService {

	@Autowired//Manda a llamar al repositorio de empresa
	IEmpresaRepository rEmpresa;
	
	@Autowired//Manda a llamar al repositorip de Usuario
	IUsuarioRepository rUsuario;
	
	//Metodo listar
	public List<Empresa> listar(){
		return (List<Empresa>) rEmpresa.findAll();
	}
	
	//Metodo guardar
	public void saveEmp(Empresa empresa) {
		rEmpresa.save(empresa);
	}
	
	//Metodo eliminar
	public void deleteEmp(Integer id) {
		rEmpresa.deleteById(id);
	}
	
	//Metodo buscar por id de empresa 
	public Empresa findById(Integer id) {
		return rEmpresa.findById(id).get();
	}
	
	//Metodo de listar por usuario
	public List<Usuario> findAllUsua() {
		return (List<Usuario>) rUsuario.findAll();
	}
	
	//Metodo de buscar id por usuario
	public Usuario findByIdUsua(Integer id) {
		return rUsuario.findById(id).get();
	}
	
	
}