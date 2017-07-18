package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.Loja;
import com.farmacia.repository.LojaRepository;
import com.farmacia.service.LojaService;

@Service
public class LojaServiceImpl implements LojaService {
    
    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public List<Loja> getAll() {
        return lojaRepository.findAll();
    }

    @Override
    public Loja getById(Integer id) {
        return lojaRepository.findById(id);
    }

}
