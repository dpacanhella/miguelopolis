package redspark.io.miguelopolis.enums.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by infra on 09/05/17.
 */
@Data
public class Farmacia {

    private Long id;

    private String razao;
    
    private String nomeProprietario;
}
