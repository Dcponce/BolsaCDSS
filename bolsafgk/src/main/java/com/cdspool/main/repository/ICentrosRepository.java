package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Centros;

@Repository
public interface ICentrosRepository extends CrudRepository<Centros, Integer>{

}
