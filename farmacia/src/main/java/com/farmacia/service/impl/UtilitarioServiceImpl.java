package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.Utilitario;
import com.farmacia.repository.UtilitarioRepository;
import com.farmacia.service.UtilitarioService;

@Service
public class UtilitarioServiceImpl implements UtilitarioService {
    
    @Autowired
    private UtilitarioRepository utilitarioRepository;

    @Override
    public List<Utilitario> getAll() {
        return utilitarioRepository.findAll();
    }

}
