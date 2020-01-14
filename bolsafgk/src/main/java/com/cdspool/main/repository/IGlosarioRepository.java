package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Glosario;

@Repository
public interface IGlosarioRepository extends CrudRepository<Glosario, Integer> {

}
