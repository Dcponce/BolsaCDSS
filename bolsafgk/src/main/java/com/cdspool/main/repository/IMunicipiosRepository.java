package com.cdspool.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cdspool.main.model.Departamentos;
import com.cdspool.main.model.Municipios;

@Repository
public interface IMunicipiosRepository extends CrudRepository<Municipios, Integer> { //Repositorio
	
	public List<Municipios>findByDepartamento(Departamentos departamento);

}
