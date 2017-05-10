package redspark.io.miguelopolis.enums.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by infra on 09/05/17.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FarmaciaDetalhes {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("razao")
    private String razao;

    @JsonProperty("nomeProprietario")
    private String nomeProprietario;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("imagem")
    private String imagem;

    @JsonProperty("plantao")
    private Boolean plantao;

}
