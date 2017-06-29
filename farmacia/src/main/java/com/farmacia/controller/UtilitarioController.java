package com.farmacia.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.UtilitarioDTO;
import com.farmacia.domain.Utilitario;
import com.farmacia.mapper.UtilitarioMapper;
import com.farmacia.service.UtilitarioService;

@RestController
@Component
@Transactional
@RequestMapping("/utilitarios")
public class UtilitarioController {
    
    @Autowired
    private UtilitarioService utilitarioService;
    
    @Autowired
    private UtilitarioMapper utilitarioMapper;
    
    @GetMapping
    public List<UtilitarioDTO> getAll() throws IOException {
        List<Utilitario> entity = utilitarioService.getAll();
        return utilitarioMapper.toListDTO(entity);
    }

}
