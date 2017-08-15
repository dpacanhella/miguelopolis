package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.CardapioRestaurante;

@Repository
public interface RestauranteCardapioRepository extends JpaRepository<CardapioRestaurante, Long>{

    List<CardapioRestaurante> findByRestauranteId(Integer id);

}
