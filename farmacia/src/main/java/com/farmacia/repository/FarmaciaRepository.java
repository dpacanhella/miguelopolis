package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Farmacia;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

    Farmacia findByPlantao(boolean b);

    Farmacia findById(Integer id);

    List<Farmacia> findAllByOrderByPlantaoDescIdAsc();

}
