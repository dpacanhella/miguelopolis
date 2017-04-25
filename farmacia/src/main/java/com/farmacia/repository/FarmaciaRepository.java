package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.domain.Farmacia;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

}
