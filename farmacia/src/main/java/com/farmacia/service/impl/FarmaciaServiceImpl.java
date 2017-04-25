package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.Farmacia;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.service.FarmaciaService;

@Service
public class FarmaciaServiceImpl implements FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;
    
    @Override
    public List<Farmacia> getAll() {
        return farmaciaRepository.findAll();
    }

}
