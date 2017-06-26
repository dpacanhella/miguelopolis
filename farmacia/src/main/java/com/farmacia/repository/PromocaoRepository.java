package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

    List<Promocao> findByFarmaciaId(Integer id);

    Promocao findById(Integer id);

}
