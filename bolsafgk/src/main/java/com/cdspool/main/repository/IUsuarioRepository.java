package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Usuarios;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuarios, Integer>{

}
