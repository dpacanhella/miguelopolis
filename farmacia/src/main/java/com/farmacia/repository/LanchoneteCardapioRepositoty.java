package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.CardapioLanchonete;

@Repository
public interface LanchoneteCardapioRepositoty extends JpaRepository<CardapioLanchonete, Long>{

    List<CardapioLanchonete> findByLanchoneteId(Integer id);

}
