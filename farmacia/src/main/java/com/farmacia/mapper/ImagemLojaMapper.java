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
//        dto.setLojaDTO(lojaMapper.toDTO(entity.getLoja()));
        dto.setDescricao(entity.getDescricaoProduto());
        dto.setImagem(entity.getImagemProduto());
        dto.setImage64(entity.getImage64());

        return dto;
    }

    @Override
    public ImagemLoja toEntity(ImagemLojaDTO dto) {
        ImagemLoja entity = new ImagemLoja();

        entity.setId(dto.getId());
        entity.setDescricaoProduto(dto.getDescricao());
        entity.setImagemProduto(dto.getImagem());
        entity.setImage64(dto.getImage64());
        entity.setLoja(lojaMapper.toEntity(dto.getLojaDTO()));

        return entity;
    }

}
