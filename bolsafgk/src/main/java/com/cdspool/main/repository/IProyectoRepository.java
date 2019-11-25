package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Proyecto;

@Repository
public interface IProyectoRepository extends CrudRepository<Proyecto, Integer> { //Repositorio

}
