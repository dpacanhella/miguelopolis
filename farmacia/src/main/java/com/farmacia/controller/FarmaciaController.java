package com.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.FarmaciaDTO;
import com.farmacia.domain.Farmacia;
import com.farmacia.mapper.FarmaciaMapper;
import com.farmacia.service.FarmaciaService;

@RestController
@RequestMapping("farmacias")
public class FarmaciaController {
    
    @Autowired
    private FarmaciaService farmaciaService;
    
    @Autowired
    private FarmaciaMapper farmaciaMapper;
    
    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public List<FarmaciaDTO> getAll() {
        List<Farmacia> entity = farmaciaService.getAll();
        return farmaciaMapper.toListDTO(entity);
    }

}
