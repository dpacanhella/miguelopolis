package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.TipoAnuncio;

@Repository
public interface TipoAnuncioRepository extends JpaRepository<TipoAnuncio, Long>{

    List<TipoAnuncio> findAllByOrderByIdAsc();

}
