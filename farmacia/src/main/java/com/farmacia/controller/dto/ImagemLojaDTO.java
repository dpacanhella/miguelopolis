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
    private String descricaoProduto;
    private String imagemProduto;
    private byte[] imageByte;

}
