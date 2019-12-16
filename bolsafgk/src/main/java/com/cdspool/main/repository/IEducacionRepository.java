package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Educacion;
import com.cdspool.main.model.Usuario;

@Repository
public interface IEducacionRepository extends CrudRepository<Educacion, Integer> {

	public Educacion findByUsuario (Usuario usuario);
}
