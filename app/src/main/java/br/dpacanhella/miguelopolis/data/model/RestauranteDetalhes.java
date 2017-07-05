package br.dpacanhella.miguelopolis.data.model;

import java.util.List;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class RestauranteDetalhes {

    private int id;
    private String nome;
    private String nomeProprietario;
    private String endereco;
    private String telefone;
    private String whatsapp;
    private String descricao;
    private String descricao2;
    private String imagemLogo;
    private String imagemEstabelecimento;
    private String imagem1;
    private String imagem2;
    private List<Cardapio> cardapios;

}
