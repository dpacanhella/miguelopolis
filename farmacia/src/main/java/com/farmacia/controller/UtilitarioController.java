package com.farmacia.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.TipoAnuncioDTO;
import com.farmacia.controller.dto.UtilitarioDTO;
import com.farmacia.domain.TipoAnuncio;
import com.farmacia.domain.Utilitario;
import com.farmacia.mapper.TipoAnuncioMapper;
import com.farmacia.mapper.UtilitarioMapper;
import com.farmacia.service.TipoAnuncioService;
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
    
    @Autowired
    private TipoAnuncioMapper tipoAnuncioMapper;
    
    @Autowired
    private TipoAnuncioService tipoAnuncioService;
    
    //Buscar por tipo do anúncio
    @GetMapping("/{tipoAnuncio}")
    public List<UtilitarioDTO> getAll(@PathVariable(value = "tipoAnuncio") String tipoAnuncio) throws IOException {
        List<Utilitario> entity = utilitarioService.getByTipoAnuncio(tipoAnuncio);
        return utilitarioMapper.toListDTO(entity);
    }
    
    @GetMapping("/anuncios")
    public List<TipoAnuncioDTO> getAnuncios() throws IOException{
        List<TipoAnuncio> entity = tipoAnuncioService.getAll();
        return tipoAnuncioMapper.toListDTO(entity);
    }

}
