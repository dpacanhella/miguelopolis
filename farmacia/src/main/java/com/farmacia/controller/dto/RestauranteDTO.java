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
public class RestauranteDTO {
    
    private Integer id;
    private String nome;
    private String nomeProprietario;
    private String endereco;
    private String telefone;
    private String whatsapp;
    private String descricao;
    private String descricao2;
    private String descricao3;
    private String descricao4;
    private String descricao5;
    private String imagemLogo;
    private String imagemEstabelecimento;
    private String imagem1;
    private String imagem2;
    private List<CardapioDTO> cardapios;

}
