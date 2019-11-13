package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Detalle_habilidades;

@Repository
public interface IDeHabilidadRepository extends CrudRepository<Detalle_habilidades, Integer> {

	// @Query("SELECT d FROM Detalle_habilidades d where d.id_habilidad.id = :id")
	// public List<Detalle_habilidades> findById_Habilidad(@Param("id") Integer id);
}