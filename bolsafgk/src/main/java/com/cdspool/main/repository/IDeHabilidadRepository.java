package com.cdspool.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cdspool.main.model.Detalle_habilidades;
import com.cdspool.main.model.Habilidad;
import com.cdspool.main.model.Usuario;

@Repository
public interface IDeHabilidadRepository extends CrudRepository<Detalle_habilidades, Integer> {

	// @Query("SELECT d FROM Detalle_habilidades d where d.id_habilidad.id = :id")
	// public List<Detalle_habilidades> findById_Habilidad(@Param("id") Integer id);

	// @Query("SELECT h FROM Detalle_habilidades h WHERE h.habilidadLIKE
	// %:habilidad%")
	// List<Detalle_habilidades> searchByHabilidadLike(@Param("habilidad") Habilidad
	// habilidad);

	// public List<Detalle_habilidades>findByHabilidad(Habilidad habilidad);

	// @Query("SELECT d FROM Detalle_habilidades d WHERE d.habilidad.descripcionLike
	// %:habilidad%")
	// public List<Detalle_habilidades> findByHabilidadLike(@PathVariable String
	// habilidad);

	// @Query("select h from Habidad h where h.descripcion like %?1")
	// List<Habilidad> findByDescripcionEndsWith(String firstname);

	List<Detalle_habilidades> findByHabilidad(Habilidad habilidad);

	public List<Detalle_habilidades> findByUsuarios(Usuario usuario);
}