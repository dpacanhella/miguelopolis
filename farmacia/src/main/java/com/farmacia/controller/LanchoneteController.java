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

import com.farmacia.controller.dto.LanchoneteDTO;
import com.farmacia.domain.Lanchonete;
import com.farmacia.mapper.LanchoneteMapper;
import com.farmacia.service.LanchoneteService;

@RestController
@Component
@Transactional
@RequestMapping("lanchonetes")
public class LanchoneteController {
    
    @Autowired
    private LanchoneteService lanchoneteService;
    
    @Autowired
    private LanchoneteMapper lanchoneteMapper;
    
    @GetMapping
    public List<LanchoneteDTO> getAll() throws IOException {
        List<Lanchonete> entity = lanchoneteService.getAll();
        
        Collections.shuffle(entity);
        return lanchoneteMapper.toListDTO(entity);
    }
    
    @GetMapping("/{id}")
    public LanchoneteDTO getById(@PathVariable Integer id) throws IOException {
        Lanchonete entity = lanchoneteService.getById(id);
        
        return lanchoneteMapper.toDTO(entity);
    }

}
