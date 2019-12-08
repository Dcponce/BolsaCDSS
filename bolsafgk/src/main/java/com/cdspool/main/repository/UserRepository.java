package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Usuario;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);
}
