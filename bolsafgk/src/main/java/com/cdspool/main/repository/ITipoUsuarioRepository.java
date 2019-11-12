package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.TipoUsuario;

@Repository
public interface ITipoUsuarioRepository extends CrudRepository<TipoUsuario, Integer> {

}
