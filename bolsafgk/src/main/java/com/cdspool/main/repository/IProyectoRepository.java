package com.cdspool.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Proyecto;
import com.cdspool.main.model.Usuario;

@Repository
public interface IProyectoRepository extends CrudRepository<Proyecto, Integer> {

	public List<Proyecto> findByUsuario(Usuario usuario);
}
