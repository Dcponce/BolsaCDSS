package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cdspool.main.model.Departamentos;

@Repository
public interface IDepartamentosRepository extends CrudRepository<Departamentos, Integer> { //Repositorio

}
