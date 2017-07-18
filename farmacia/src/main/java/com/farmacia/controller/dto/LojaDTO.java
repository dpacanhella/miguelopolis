package com.farmacia.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LojaDTO {
    
    private Integer id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private String celular;
    private String imagemEstabelecimento;
    private String imagemLogo;
    private List<ImagemLojaDTO> imagensLojas;

}
