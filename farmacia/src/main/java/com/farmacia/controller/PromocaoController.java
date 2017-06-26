package com.farmacia.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;
import com.farmacia.mapper.PromocaoMapper;
import com.farmacia.service.PromocaoService;

@RestController
@Component
@Transactional
@RequestMapping("/promocoes")
public class PromocaoController {
    
    @Autowired
    private PromocaoService promocaoService;
    
    @Autowired
    private PromocaoMapper promocaoMapper;
    
    @PostMapping
    public PromocaoDTO salvar(@RequestBody PromocaoDTO promocaoDTO) {
        Promocao promocao = promocaoService.salvar(promocaoMapper.toEntity(promocaoDTO));

        return promocaoMapper.toDTO(promocao);
    }
    
    @PutMapping("/{id}")
    public PromocaoDTO salvar(@PathVariable Integer id, @RequestBody PromocaoDTO dto) {
        Promocao promocao = promocaoService.update(id, dto);

        return promocaoMapper.toDTO(promocao);
    }

    @GetMapping("/farmaciaId/{id}")
    public List<PromocaoDTO> getAll(@PathVariable Integer id) {
        List<Promocao> entity = promocaoService.getAll(id);
        return promocaoMapper.toListDTO(entity);
    }
    
    @GetMapping("/{id}")
    public PromocaoDTO getById(@PathVariable Integer id) {
        Promocao entity = promocaoService.getById(id);
        return promocaoMapper.toDTO(entity);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        promocaoService.delete(id);
    }
    
}
