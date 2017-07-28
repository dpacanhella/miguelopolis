package br.dpacanhella.miguelopolis.data.model;



import java.io.Serializable;

import lombok.Data;

/**
 * Created by infra on 20/06/17.
 */

@Data
public class Promocao implements Serializable {

    private int id;
    private String imagemProduto;
    private String nomeProduto;
    private String precoInicial;
    private String precoFinal;
    private String image64;
    private String imageByte;

}
