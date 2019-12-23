package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.ClaveTemporal;

@Repository
public interface IClaveTeRepository extends CrudRepository<ClaveTemporal, Integer> {
	
	public ClaveTemporal findByClavet(String clavet);

}
