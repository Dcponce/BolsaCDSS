package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.Departamentos;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.repository.IDepartamentosRepository;
import com.cdspool.main.repository.IMunicipiosRepository;

@Service
@Transactional
public class MunicipiosService {
	
	@Autowired 
	IMunicipiosRepository rMunicipio;
	
	@Autowired 
	IDepartamentosRepository rDepartamento;


	public Municipios porMunicipio(Integer id) {
		return rMunicipio.findById(id).get();
	}
	
	public List<Municipios> listarM(Integer id){
		Departamentos departamento = rDepartamento.findById(id).get();
		return (List<Municipios>) rMunicipio.findByDepartamento(departamento);
	}
	
	public List<Departamentos>lista(){
		return (List<Departamentos>) rDepartamento.findAll();
	}
	
	public Departamentos porDepartamentos(Integer id) {
		return rDepartamento.findById(id).get();
	}
}
