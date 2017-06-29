package com.farmacia.mapper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;

@Component
public class PromocaoMapper extends BaseMapper<Promocao, PromocaoDTO> {
    
    @Autowired
    private FarmaciaMapper farmaciaMapper;

    @Override
    public PromocaoDTO toDTO(Promocao entity) throws IOException {
        PromocaoDTO dto = new PromocaoDTO();
        
        dto.setId(entity.getId());
        dto.setImagemProduto(entity.getImagemProduto());
        dto.setNomeProduto(entity.getNomeProduto());
        dto.setPrecoInicial(entity.getPrecoInicial());
        dto.setPrecoFinal(entity.getPrecoFinal());
        dto.setFarmaciaDTO(farmaciaMapper.toDTO(entity.getFarmacia()));
        dto.setImage64(entity.getImage64());
        
        return dto;
    }

    @Override
    public Promocao toEntity(PromocaoDTO dto) {
        Promocao entity = new Promocao();
        
        entity.setImagemProduto(dto.getImagemProduto());
        entity.setNomeProduto(dto.getNomeProduto());
        entity.setPrecoInicial(dto.getPrecoInicial());
        entity.setPrecoFinal(dto.getPrecoFinal());
        entity.setImage64(dto.getImage64());
        entity.setFarmacia(farmaciaMapper.toEntity(dto.getFarmaciaDTO()));
        
        return entity;
    }

}