package com.farmacia.service;

import java.util.List;

import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;

public interface PromocaoService {

    Promocao salvar(Promocao entity);

    List<Promocao> getAll(Integer id);

    Promocao update(Integer id, PromocaoDTO dto);

    void delete(Integer id);

    Promocao getById(Integer id);

}
