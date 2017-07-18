package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

    Loja findById(Integer id);

}
