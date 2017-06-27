package com.farmacia.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    
    @RequestMapping(produces="application/json", consumes={"application/json", "multipart/form-data"}, method=RequestMethod.POST)
    @Transactional
    public PromocaoDTO salvar(@RequestBody PromocaoDTO dto, @RequestPart("file") MultipartFile file) throws FileNotFoundException, IOException {
        
        Promocao promocao = promocaoService.salvar(dto, file);

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
