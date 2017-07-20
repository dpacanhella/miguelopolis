package br.dpacanhella.miguelopolis.data.model;

import java.util.List;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class LojaDetalhes {

    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private String celular;
    private String imagemEstabelecimento;
    private List<ImagemLoja> imagensLojas;

}
