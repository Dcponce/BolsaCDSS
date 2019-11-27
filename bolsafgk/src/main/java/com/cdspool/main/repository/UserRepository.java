package com.cdspool.main.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Usuario;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Usuario, Integer>{

	public Usuario findByEmail(String email);
}
