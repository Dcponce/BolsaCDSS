package com.cdspool.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cdspool.main.model.Detalle_habilidades;
import com.cdspool.main.model.Habilidad;
import com.cdspool.main.model.Usuario;

@Repository
public interface IDeHabilidadRepository extends CrudRepository<Detalle_habilidades, Integer> {

	// Query para listar detalle de habilidad por habilidad
	List<Detalle_habilidades> findByHabilidad(Habilidad habilidad);

	// Query para listar detalle de habilidad por usuario
	public List<Detalle_habilidades> findByUsuarios(Usuario usuario);
	
	//Query para eliminar detalle de habilidades por usuario
	public void deleteByUsuarios(Usuario usuario);
}