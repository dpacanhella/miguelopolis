package com.farmacia.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.LanchoneteDTO;
import com.farmacia.domain.Lanchonete;
import com.farmacia.utils.MapperUtils;

@Component
public class LanchoneteMapper extends BaseMapper<Lanchonete, LanchoneteDTO> {
    
    private MapperUtils<Lanchonete, LanchoneteDTO> convert = new MapperUtils<Lanchonete, LanchoneteDTO>(Lanchonete.class, LanchoneteDTO.class);

    @Override
    public LanchoneteDTO toDTO(Lanchonete entity) throws IOException {
        return convert.toDTO(entity);
    }

    @Override
    public Lanchonete toEntity(LanchoneteDTO dto) {
        return convert.toEntity(dto);
    }

}
