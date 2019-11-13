package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Documento;

@Repository
public interface IDocumentoRepository extends CrudRepository<Documento, Integer> {

}
