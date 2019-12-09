package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Credencial;

@Repository
public interface ICredencialRepository extends CrudRepository<Credencial, Integer> {
	
	public Credencial findByCodigo(String codigo);
	
}
