package com.farmacia.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanchoneteDTO {
    
    private Integer id;
    private String nome;
    private String nomeProprietario;
    private String endereco;
    private String telefone;
    private String whatsapp;
    private String imagemEstabelecimento;
    private String imagemLogo;
    private List<CardapioLanchoneteDTO> cardapios = new ArrayList<CardapioLanchoneteDTO>();

}
