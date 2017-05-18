package redspark.io.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 09/05/17.
 */

@Data
public class FarmaciaDetalhes {

    private int id;

    private String razao;

    private String nomeProprietario;

    private String telefone;

    private String endereco;

    private String imagem;

    private Boolean plantao;

}
