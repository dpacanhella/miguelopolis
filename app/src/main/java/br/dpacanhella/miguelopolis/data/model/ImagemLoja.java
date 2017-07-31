package br.dpacanhella.miguelopolis.data.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class ImagemLoja implements Serializable{

    private int id;
    private String descricao;
    private String imagem;
    private String image64;

}
