package com.farmacia.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoAnuncioDTO {
    
    private Integer id;
    private String descricao;
    private Integer qtdeUtilitario;

}
