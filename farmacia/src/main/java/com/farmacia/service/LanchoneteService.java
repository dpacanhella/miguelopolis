package com.farmacia.service;

import java.util.List;

import com.farmacia.domain.Lanchonete;

public interface LanchoneteService {

    List<Lanchonete> getAll();

    Lanchonete getById(Integer id);

}