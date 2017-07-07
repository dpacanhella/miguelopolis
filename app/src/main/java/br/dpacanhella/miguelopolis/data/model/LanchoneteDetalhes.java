package br.dpacanhella.miguelopolis.data.model;

import java.util.List;

import lombok.Data;

/**
 * Created by infra on 07/07/17.
 */

@Data
public class LanchoneteDetalhes {

    private int id;
    private String nome;
    private String nomeProprietario;
    private String endereco;
    private String telefone;
    private String whatsapp;
    private String imagemEstabelecimento;
    private String imagemLogo;
    private List<Cardapio> cardapios;
}
