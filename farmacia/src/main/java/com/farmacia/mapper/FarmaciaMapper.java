package com.farmacia.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.FarmaciaDTO;
import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Farmacia;
import com.farmacia.domain.Promocao;
import com.farmacia.utils.MapperUtils;

@Component
public class FarmaciaMapper extends BaseMapper<Farmacia, FarmaciaDTO> {

    private MapperUtils<Farmacia, FarmaciaDTO> convert = new MapperUtils<Farmacia, FarmaciaDTO>(Farmacia.class, FarmaciaDTO.class);
    
    @Override
    public FarmaciaDTO toDTO(Farmacia entity) throws IOException {
        FarmaciaDTO dto = convert.toDTO(entity);
        
        List<PromocaoDTO> listPromocao = new ArrayList<PromocaoDTO>();
        for (Promocao promocao : entity.getPromocoes()) {
            PromocaoDTO promDTO = new PromocaoDTO();
            
            promDTO.setId(promocao.getId());
            promDTO.setNomeProduto(promocao.getNomeProduto());
            promDTO.setPrecoInicial(promocao.getPrecoInicial());
            promDTO.setPrecoFinal(promocao.getPrecoFinal());
            promDTO.setImagemProduto(promocao.getImagemProduto());
            promDTO.setImage64("http://guiamiguelopolis.com/assets/promocoes/" + promocao.getImagemProduto());
            
            listPromocao.add(promDTO);
        }
        
        Collections.shuffle(listPromocao);
        
        dto.setPromocoes(listPromocao);
        
        
        return dto;
    }

    @Override
    public Farmacia toEntity(FarmaciaDTO dto) {
        return convert.toEntity(dto);
    }

}
