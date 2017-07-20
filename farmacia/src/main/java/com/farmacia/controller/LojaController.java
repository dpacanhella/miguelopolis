package com.farmacia.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.LojaDTO;
import com.farmacia.domain.Loja;
import com.farmacia.mapper.LojaMapper;
import com.farmacia.service.LojaService;

@RestController
@Component
@Transactional
@RequestMapping("lojas")
public class LojaController {
    
    @Autowired
    private LojaService lojaService;
    
    @Autowired
    private LojaMapper lojaMapper;
    
    @GetMapping
    public List<LojaDTO> getAll() throws IOException {
        List<Loja> entity = lojaService.getAll();
        
        Collections.shuffle(entity);
        
        return lojaMapper.toListDTO(entity);
    }
    
    @GetMapping("/{id}")
    public LojaDTO getById(@PathVariable Integer id) throws IOException {
        Loja entity = lojaService.getById(id);
        
        return lojaMapper.toDTO(entity);
    }

}
