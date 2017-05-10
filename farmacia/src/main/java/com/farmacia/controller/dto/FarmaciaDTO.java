package com.farmacia.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmaciaDTO {
    
    private Integer id;
    private String razao;
    private String nomeProprietario;
    private String telefone;
    private String endereco;
    private String imagem;
    private Boolean plantao;
}
