package com.farmacia.service.impl;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Utilitario> utilitarios = utilitarioRepository.findAll();
        
        Collections.shuffle(utilitarios);
        
        return utilitarios;
    }

    @Override
    public List<Utilitario> getByTipoAnuncio(String tipoAnuncio) {
        
        List<Utilitario> utilitarios = new ArrayList<Utilitario>();
        
        if(tipoAnuncio.equals("TODOS")) {
            utilitarios = utilitarioRepository.findAll();
        }else{
            utilitarios = utilitarioRepository.findByTipoAnuncio(tipoAnuncio);
        }
        
        Collections.shuffle(utilitarios);
        return utilitarios;
    }

}
