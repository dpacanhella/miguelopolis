package com.farmacia.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.UtilitarioDTO;
import com.farmacia.domain.Utilitario;
import com.farmacia.utils.MapperUtils;

@Component
public class UtilitarioMapper extends BaseMapper<Utilitario, UtilitarioDTO>{
    
    private MapperUtils<Utilitario, UtilitarioDTO> convert = new MapperUtils<Utilitario, UtilitarioDTO>(Utilitario.class, UtilitarioDTO.class);

    @Override
    public UtilitarioDTO toDTO(Utilitario entity) throws IOException {
        return convert.toDTO(entity);
    }

    @Override
    public Utilitario toEntity(UtilitarioDTO dto) {
        return convert.toEntity(dto);
    }

}
