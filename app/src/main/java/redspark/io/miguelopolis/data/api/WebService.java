package redspark.io.miguelopolis.data.api;

import java.util.List;

import redspark.io.miguelopolis.data.model.Farmacia;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by infra on 10/05/17.
 */

public interface WebService {

    @GET("farmacias")
    Call<List<Farmacia>> getAll();
}
