package com.farmacia.mapper;

import org.springframework.stereotype.Component;

import com.farmacia.controller.dto.FarmaciaDTO;
import com.farmacia.domain.Farmacia;
import com.farmacia.utils.MapperUtils;

@Component
public class FarmaciaMapper extends BaseMapper<Farmacia, FarmaciaDTO> {

    private MapperUtils<Farmacia, FarmaciaDTO> convert = new MapperUtils<Farmacia, FarmaciaDTO>(Farmacia.class, FarmaciaDTO.class);
    
    @Override
    public FarmaciaDTO toDTO(Farmacia entity) {
        return convert.toDTO(entity);
    }

    @Override
    public Farmacia toEntity(FarmaciaDTO dto) {
        return convert.toEntity(dto);
    }

}
