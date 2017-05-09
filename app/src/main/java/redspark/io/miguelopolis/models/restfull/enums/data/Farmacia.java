package redspark.io.miguelopolis.models.restfull.enums.data;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by infra on 09/05/17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Farmacia {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("razao")
    private String razao;

    @JsonProperty("nomeProprietario")
    private String nomeProprietario;
}
