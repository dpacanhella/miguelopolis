package br.dpacanhella.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class Loja {

    private int id;
    private String nome;
    private String descricao;
    private String imagemLogo;
}
