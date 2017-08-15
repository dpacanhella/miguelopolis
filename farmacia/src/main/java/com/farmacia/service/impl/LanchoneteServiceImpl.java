package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.CardapioLanchonete;
import com.farmacia.domain.Lanchonete;
import com.farmacia.repository.LanchoneteCardapioRepositoty;
import com.farmacia.repository.LanchoneteRepository;
import com.farmacia.service.LanchoneteService;

@Service
public class LanchoneteServiceImpl implements LanchoneteService {
    
    @Autowired
    private LanchoneteRepository lanchoneteRepository;
    
    @Autowired
    private LanchoneteCardapioRepositoty cardapioRepository;

    @Override
    public List<Lanchonete> getAll() {
        return lanchoneteRepository.findAll();
    }

    @Override
    public Lanchonete getById(Integer id) {
       
        return lanchoneteRepository.findById(id);
    }

    @Override
    public List<CardapioLanchonete> getCardapioById(Integer id) {
       List<CardapioLanchonete> cardapios = cardapioRepository.findByLanchoneteId(id);
       
        return cardapios;
    }

}
