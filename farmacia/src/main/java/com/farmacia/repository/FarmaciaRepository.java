package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Farmacia;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

    Farmacia findByPlantao(boolean b);

    Farmacia findById(Integer id);
    
    @Query("select far from Farmacia far "  
            + " where far.plantao = ?1")
    List<Farmacia> findByPlantaoFalse(boolean b);

}
