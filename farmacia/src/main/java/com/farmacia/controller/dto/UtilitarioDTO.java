package com.farmacia.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilitarioDTO {
    
    private Integer id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private String celular;
    private String imagem;

}
