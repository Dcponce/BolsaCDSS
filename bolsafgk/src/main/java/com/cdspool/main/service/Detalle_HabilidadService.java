package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Detalle_habilidades;
import com.cdspool.main.repository.IDeHabilidadRepository;
import com.cdspool.main.repository.IHabilidadRepository;

@Service
@Transactional
public class Detalle_HabilidadService {

	@Autowired
	IHabilidadRepository rHabilidad;
	
	@Autowired
	IDeHabilidadRepository rDetalle;
	
	public List<Detalle_habilidades> listar(){
		return (List<Detalle_habilidades>) rDetalle.findAll();
	}
	
	public void eliminarDe(Integer id) {
		rDetalle.deleteById(id);
	}
	
	public void saveDe(Detalle_habilidades detalle) {
		rDetalle.save(detalle);
	}
}