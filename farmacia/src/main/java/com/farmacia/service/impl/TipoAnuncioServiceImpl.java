package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.TipoAnuncio;
import com.farmacia.repository.TipoAnuncioRepository;
import com.farmacia.service.TipoAnuncioService;

@Service
public class TipoAnuncioServiceImpl implements TipoAnuncioService{

    @Autowired
    private TipoAnuncioRepository anuncioRepository;
    
    @Override
    public List<TipoAnuncio> getAll() {
        return anuncioRepository.findAllByOrderByIdAsc();
    }

}
