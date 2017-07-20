package com.farmacia.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagemLojaDTO {
    
    private Integer id;
    private String descricao;
    private String imagem;
    private LojaDTO lojaDTO;
    private byte[] imageByte;

}
