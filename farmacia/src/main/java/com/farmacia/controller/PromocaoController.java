package com.farmacia.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    @Transactional
    public PromocaoDTO salvar(@RequestParam(value = "farmaciaId", required = true) Integer farmaciaId,
            @RequestParam(value = "nomeProduto", required = true) String nomeProduto,
            @RequestParam(value = "descricaoProduto", required = true) String descricaoProduto,
            @RequestParam(value = "precoProduto", required = true) String precoProduto,
            @RequestPart("file") MultipartFile file) throws FileNotFoundException, IOException {

        Promocao promocao = promocaoService.salvar(farmaciaId, nomeProduto, descricaoProduto, precoProduto, file);

        return promocaoMapper.toDTO(promocao);
    }

    @PutMapping("/{id}")
    public PromocaoDTO update(@PathVariable Integer id,
            @RequestParam(value = "nomeProduto", required = true) String nomeProduto,
            @RequestParam(value = "descricaoProduto", required = true) String descricaoProduto,
            @RequestParam(value = "precoProduto", required = true) String precoProduto,
            @RequestPart("file") MultipartFile file) throws FileNotFoundException, IOException {
        Promocao promocao = promocaoService.update(id, nomeProduto, descricaoProduto, precoProduto, file);

        return promocaoMapper.toDTO(promocao);
    }

    @GetMapping("/farmaciaId/{id}")
    public List<PromocaoDTO> getAll(@PathVariable Integer id) throws IOException {
        List<Promocao> entity = promocaoService.getAll(id);

        List<PromocaoDTO> listDTO = promocaoMapper.toListDTO(entity);

        Collections.shuffle(listDTO);
        
        return listDTO;
    }

    @GetMapping("/{id}")
    public PromocaoDTO getById(@PathVariable Integer id) throws IOException {
        Promocao entity = promocaoService.getById(id);
        return promocaoMapper.toDTO(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        promocaoService.delete(id);
    }

}
