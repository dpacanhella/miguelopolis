package br.dpacanhella.miguelopolis.data.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by infra on 09/05/17.
 */

@Data
public class FarmaciaDetalhes implements Serializable {

    private int id;

    private String razao;

    private String nomeProprietario;

    private String telefone;

    private String endereco;

    private String imagem;

    private Boolean plantao;

    private String whatsApp;

    private List<Promocao> promocoes;

}
