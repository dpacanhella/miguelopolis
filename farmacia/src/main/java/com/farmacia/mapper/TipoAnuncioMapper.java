package com.farmacia.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.TipoAnuncioDTO;
import com.farmacia.domain.TipoAnuncio;
import com.farmacia.utils.MapperUtils;

@Component
public class TipoAnuncioMapper extends BaseMapper<TipoAnuncio, TipoAnuncioDTO> {
    
    private MapperUtils<TipoAnuncio, TipoAnuncioDTO> convert = new MapperUtils<TipoAnuncio, TipoAnuncioDTO>(TipoAnuncio.class, TipoAnuncioDTO.class);

    @Override
    public TipoAnuncioDTO toDTO(TipoAnuncio entity) throws IOException {
        return convert.toDTO(entity);
    }

    @Override
    public TipoAnuncio toEntity(TipoAnuncioDTO dto) {
        return convert.toEntity(dto);
    }

}
