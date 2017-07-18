package com.farmacia.service;

import java.util.List;

import com.farmacia.domain.Loja;

public interface LojaService {

    List<Loja> getAll();

    Loja getById(Integer id);

}
