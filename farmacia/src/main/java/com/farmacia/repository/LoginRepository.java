package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Usuario;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Long>{

}
