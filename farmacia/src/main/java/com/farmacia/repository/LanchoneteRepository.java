package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Lanchonete;

@Repository
public interface LanchoneteRepository extends JpaRepository<Lanchonete, Long> {

    Lanchonete findById(Integer id);

}
