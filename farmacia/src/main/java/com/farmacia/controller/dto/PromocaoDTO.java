package com.farmacia.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromocaoDTO {

    private Integer id;
    private String imagemProduto;
    private String nomeProduto;
    private String precoInicial;
    private String precoFinal;
    private FarmaciaDTO farmaciaDTO;
}
