package com.farmacia.mapper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.ImagemLojaDTO;
import com.farmacia.domain.ImagemLoja;

@Component
public class ImagemLojaMapper extends BaseMapper<ImagemLoja, ImagemLojaDTO> {

    @Autowired
    private LojaMapper lojaMapper;

    @Override
    public ImagemLojaDTO toDTO(ImagemLoja entity) throws IOException {
        ImagemLojaDTO dto = new ImagemLojaDTO();

        dto.setId(entity.getId());
        dto.setLojaDTO(lojaMapper.toDTO(entity.getLoja()));
        dto.setDescricaoProduto(entity.getDescricaoProduto());
        dto.setImagemProduto(entity.getImagemProduto());

        return dto;
    }

    @Override
    public ImagemLoja toEntity(ImagemLojaDTO dto) {
        ImagemLoja entity = new ImagemLoja();

        entity.setId(dto.getId());
        entity.setDescricaoProduto(dto.getDescricaoProduto());
        entity.setImagemProduto(dto.getImagemProduto());
        entity.setLoja(lojaMapper.toEntity(dto.getLojaDTO()));

        return entity;
    }

}
