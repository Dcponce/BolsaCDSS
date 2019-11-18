package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Denuncia;

@Repository
public interface IDenunciaRepository extends CrudRepository<Denuncia, Integer> { //Repositorio

}
