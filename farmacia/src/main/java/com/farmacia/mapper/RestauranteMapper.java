package com.farmacia.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.RestauranteDTO;
import com.farmacia.domain.Restaurante;
import com.farmacia.utils.MapperUtils;

@Component
public class RestauranteMapper extends BaseMapper<Restaurante, RestauranteDTO> {
    
    private MapperUtils<Restaurante, RestauranteDTO> convert = new MapperUtils<Restaurante, RestauranteDTO>(Restaurante.class, RestauranteDTO.class);

    @Override
    public RestauranteDTO toDTO(Restaurante entity) throws IOException {
        return convert.toDTO(entity);
    }

    @Override
    public Restaurante toEntity(RestauranteDTO dto) {
        return convert.toEntity(dto);
    }

}
