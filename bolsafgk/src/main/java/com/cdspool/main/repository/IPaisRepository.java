package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Pais;

@Repository
public interface IPaisRepository extends CrudRepository<Pais, Integer> {

}
