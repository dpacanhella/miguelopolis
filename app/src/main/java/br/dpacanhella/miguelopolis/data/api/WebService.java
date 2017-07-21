package br.dpacanhella.miguelopolis.data.api;

import java.util.List;

import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Lanchonete;
import br.dpacanhella.miguelopolis.data.model.LanchoneteDetalhes;
import br.dpacanhella.miguelopolis.data.model.Loja;
import br.dpacanhella.miguelopolis.data.model.LojaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Restaurante;
import br.dpacanhella.miguelopolis.data.model.RestauranteDetalhes;
import br.dpacanhella.miguelopolis.data.model.TipoAnuncio;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
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

    @GET("utilitarios/{tipoAnuncio}")
    Call<List<Utilitario>> getAllUtilitarios(@Path("tipoAnuncio") String anuncio);

    @GET("restaurantes")
    Call<List<Restaurante>> getAllRestaurantes();

    @GET("restaurantes/{id}")
    Call<RestauranteDetalhes> getByIdRestaurantes(@Path("id") int id);

    @GET("lanchonetes")
    Call<List<Lanchonete>> getAllLanchonetes();

    @GET("lanchonetes/{id}")
    Call<LanchoneteDetalhes> getByIdLanchonetes(@Path("id") int id);

    @GET("utilitarios/anuncios")
    Call<List<TipoAnuncio>> getAllAnuncios();

    @GET("lojas")
    Call<List<Loja>> getAllLojas();

    @GET("lojas/{id}")
    Call<LojaDetalhes> getByIdLojas(@Path("id") int id);

}
