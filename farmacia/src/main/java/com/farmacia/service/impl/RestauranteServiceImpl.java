package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.CardapioRestaurante;
import com.farmacia.domain.Restaurante;
import com.farmacia.repository.RestauranteCardapioRepository;
import com.farmacia.repository.RestauranteRepository;
import com.farmacia.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService {
    
    @Autowired
    private RestauranteRepository restauranteRepository;
    
    @Autowired
    private RestauranteCardapioRepository cardapioRepository;

    @Override
    public List<Restaurante> getAll() {
        List<Restaurante> findAll = restauranteRepository.findAll();
        return findAll;
    }

    @Override
    public Restaurante getById(Integer id) {
        Restaurante findOne = restauranteRepository.findById(id);
        return findOne;
    }

    @Override
    public List<CardapioRestaurante> getCardapioById(Integer id) {
        List<CardapioRestaurante> cardapios = cardapioRepository.findByRestauranteId(id);
        
        return cardapios;
    }

}
