package br.dpacanhella.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 20/06/17.
 */

@Data
public class Promocao {

    private int id;
    private String imagemProduto;
    private String nomeProduto;
    private String precoInicial;
    private String precoFinal;
}
