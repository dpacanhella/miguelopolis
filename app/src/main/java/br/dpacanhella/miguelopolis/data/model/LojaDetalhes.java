package br.dpacanhella.miguelopolis.data.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class LojaDetalhes implements Serializable{

    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private String celular;
    private String imagemEstabelecimento;
    private List<ImagemLoja> imagensLojas;

}
