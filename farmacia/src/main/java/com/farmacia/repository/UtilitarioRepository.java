package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Utilitario;

@Repository
public interface UtilitarioRepository extends JpaRepository<Utilitario, Long>{

    List<Utilitario> findByTipoAnuncio(String tipoAnuncio);

}
