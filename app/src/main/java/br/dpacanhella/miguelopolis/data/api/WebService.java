package br.dpacanhella.miguelopolis.data.api;

import java.util.List;

import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by infra on 10/05/17.
 */

public interface WebService {

    @GET("farmacias")
    Call<List<Farmacia>> getAll();

    @GET("farmacias/{id}")
    Call<FarmaciaDetalhes> getById(@Path("id") int id);


}
