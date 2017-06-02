package br.dpacanhella.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 09/05/17.
 */
@Data
public class Farmacia {

    private int id;

    private String razao;

    private String nomeProprietario;

    private String telefone;

    private Boolean plantao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String razao) {
        this.telefone = telefone;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

}
