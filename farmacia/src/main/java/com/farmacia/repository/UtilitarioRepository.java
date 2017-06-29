package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Utilitario;

@Repository
public interface UtilitarioRepository extends JpaRepository<Utilitario, Long>{

}
