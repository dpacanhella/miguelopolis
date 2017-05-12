package redspark.io.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 09/05/17.
 */
@Data
public class Farmacia {

    private Long id;

    private String razao;

    private String nomeProprietario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }
}
