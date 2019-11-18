package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Empresa;

@Repository
public interface IEmpresaRepository extends CrudRepository<Empresa, Integer>{

}
