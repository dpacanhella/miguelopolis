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

import com.farmacia.controller.dto.RestauranteDTO;
import com.farmacia.domain.Restaurante;
import com.farmacia.mapper.RestauranteMapper;
import com.farmacia.service.RestauranteService;

@RestController
@Component
@Transactional
@RequestMapping("/restaurantes")
public class RestauranteController {
    
    @Autowired
    private RestauranteService restauranteService;
    
    @Autowired
    private RestauranteMapper restauranteMapper;

    @GetMapping
    public List<RestauranteDTO> getAll() throws IOException {
        List<Restaurante> entity = restauranteService.getAll();
        
        Collections.shuffle(entity);
        return restauranteMapper.toListDTO(entity);
    }
    
    @GetMapping("/{id}")
    public RestauranteDTO getById(@PathVariable Integer id) throws IOException {
        Restaurante entity = restauranteService.getById(id);
        
        return restauranteMapper.toDTO(entity);
    }
}
