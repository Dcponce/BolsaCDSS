package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Empresa;
import com.cdspool.main.repository.IEmpresaRepository;
import com.cdspool.main.repository.IMunicipiosRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class EmpresaService {

	@Autowired
	IEmpresaRepository rEmpresa;
	
	@Autowired
	IUsuarioRepository rUsuario;
	
	@Autowired
	IMunicipiosRepository rMunicipio;
	
	public List<Empresa> listar(){
		return (List<Empresa>) rEmpresa.findAll();
	}
	
	public void saveEmp(Empresa empresa) {
		rEmpresa.save(empresa);
	}
	
	public void deleteEmp(Integer id) {
		rEmpresa.deleteById(id);
	}
	
	public Empresa findById(Integer id) {
		return rEmpresa.findById(id).get();
	}
}