package com.farmacia.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.FarmaciaDTO;
import com.farmacia.domain.Farmacia;
import com.farmacia.mapper.FarmaciaMapper;
import com.farmacia.service.FarmaciaService;

@RestController
@Component
@Transactional
@RequestMapping("farmacias")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private FarmaciaMapper farmaciaMapper;

    @Scheduled(cron = "0 2 3 ? * SAT")
    // @Scheduled(fixedRate = 2000)
    public List<FarmaciaDTO> setFarmacias() throws IOException {
        List<Farmacia> entity = farmaciaService.updateAll();
        return farmaciaMapper.toListDTO(entity);
    }

    @GetMapping
    public List<FarmaciaDTO> getAll() throws IOException {
        List<Farmacia> entity = farmaciaService.getAll();
        return farmaciaMapper.toListDTO(entity);
    }

    @GetMapping("/{id}")
    public FarmaciaDTO getById(@PathVariable Integer id) throws IOException {
        Farmacia entity = farmaciaService.getById(id);
        
        return farmaciaMapper.toDTO(entity);
    }

}
