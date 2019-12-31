package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Documento;
import com.cdspool.main.model.Usuario;

@Repository
public interface IDocumentoRepository extends CrudRepository<Documento, Integer> {

	// Query para listar documento por usuario
	public Documento findByUsuario(Usuario usuario);

}
