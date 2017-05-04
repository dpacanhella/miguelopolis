package com.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Scheduled(cron="0 2 0 ? * SAT")
    public List<FarmaciaDTO> setFarmacias() {
        List<Farmacia> entity = farmaciaService.updateAll();
        return farmaciaMapper.toListDTO(entity);
    }
    
    @GetMapping("/listAll")
    @Scheduled(cron="0 2 0 ? * SAT")
    public List<FarmaciaDTO> getAll() {
        List<Farmacia> entity = farmaciaService.getAll();
        return farmaciaMapper.toListDTO(entity);
    }

}
