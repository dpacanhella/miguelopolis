package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.domain.ImagemLoja;

@Repository
public interface ImagemLojaRepository extends JpaRepository<ImagemLoja, Long> {

    ImagemLoja findById(Integer id);

    List<ImagemLoja> findByLojaId(Integer id);

}
