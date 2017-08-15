package com.farmacia.service;

import java.util.List;

import com.farmacia.domain.CardapioRestaurante;
import com.farmacia.domain.Restaurante;

public interface RestauranteService {

    List<Restaurante> getAll();

    Restaurante getById(Integer id);

    List<CardapioRestaurante> getCardapioById(Integer id);

}
