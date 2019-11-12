package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Habilidad;

@Repository
public interface IHabilidadRepository extends CrudRepository<Habilidad, Integer>{

}