package com.farmacia.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.ImagemLojaDTO;
import com.farmacia.controller.dto.LojaDTO;
import com.farmacia.domain.ImagemLoja;
import com.farmacia.domain.Loja;
import com.farmacia.utils.MapperUtils;

@Component
public class LojaMapper extends BaseMapper<Loja, LojaDTO> {

    private MapperUtils<Loja, LojaDTO> convert = new MapperUtils<Loja, LojaDTO>(Loja.class, LojaDTO.class);

    @Override
    public LojaDTO toDTO(Loja entity) throws IOException {
        LojaDTO dto = convert.toDTO(entity);

        List<ImagemLojaDTO> listImagems = new ArrayList<ImagemLojaDTO>();
        for (ImagemLoja img : entity.getImagensLojas()) {
            ImagemLojaDTO imgDTO = new ImagemLojaDTO();

            imgDTO.setId(img.getId());
            imgDTO.setDescricao(img.getDescricaoProduto());
            imgDTO.setImagem(img.getImagemProduto());
            imgDTO.setImage64("http://guiamiguelopolis.com/assets/imagens/" + img.getImage64());
            
            listImagems.add(imgDTO);
        }

        dto.setImagensLojas(listImagems);

        return dto;
    }

    @Override
    public Loja toEntity(LojaDTO dto) {
        return convert.toEntity(dto);
    }

}
