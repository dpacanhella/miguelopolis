package br.dpacanhella.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 29/06/17.
 */

@Data
public class Utilitario {

    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private String celular;
    private String imagem;
}
