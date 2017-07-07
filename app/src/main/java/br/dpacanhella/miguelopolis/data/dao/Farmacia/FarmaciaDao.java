package br.dpacanhella.miguelopolis.data.dao.Farmacia;

import java.io.IOException;
import java.util.List;

import br.dpacanhella.miguelopolis.data.api.WebServiceClient;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.api.WebService;
import br.dpacanhella.miguelopolis.data.dao.DaoException;
import br.dpacanhella.miguelopolis.data.model.Lanchonete;
import br.dpacanhella.miguelopolis.data.model.Restaurante;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by infra on 10/05/17.
 */

public class FarmaciaDao {

    private WebService wsClient;

    public FarmaciaDao(){
        wsClient = new WebServiceClient().build();
    }


    public List<Farmacia> getAll() throws DaoException {
        try {
            Call<List<Farmacia>> call = wsClient.getAll();
            Response<List<Farmacia>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new DaoException(response.code(), response.message());
            }
        } catch (IOException e) {
            throw new DaoException(DaoException.API_CALL_ERROR, e.getMessage());
        }
    }

    public void getById(int id, Callback callback) throws DaoException {
        wsClient.getById(id).enqueue(callback);
    }

    public List<Utilitario> getAllUtilitarios() throws DaoException {
        try {
            Call<List<Utilitario>> call = wsClient.getAllUtilitarios();
            Response<List<Utilitario>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new DaoException(response.code(), response.message());
            }
        } catch (IOException e) {
            throw new DaoException(DaoException.API_CALL_ERROR, e.getMessage());
        }
    }

    public List<Restaurante> getAllRestaurantes() throws DaoException {
        try {
            Call<List<Restaurante>> call = wsClient.getAllRestaurantes();
            Response<List<Restaurante>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new DaoException(response.code(), response.message());
            }
        } catch (IOException e) {
            throw new DaoException(DaoException.API_CALL_ERROR, e.getMessage());
        }
    }

    public void getByIdRestaurante(int id, Callback callback) throws DaoException {
        wsClient.getByIdRestaurantes(id).enqueue(callback);
    }

    public List<Lanchonete> getAllLanchonetes() throws DaoException {
        try {
            Call<List<Lanchonete>> call = wsClient.getAllLanchonetes();
            Response<List<Lanchonete>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new DaoException(response.code(), response.message());
            }
        } catch (IOException e) {
            throw new DaoException(DaoException.API_CALL_ERROR, e.getMessage());
        }
    }

    public void getByIdLanchonetes(int id, Callback callback) throws DaoException {
        wsClient.getByIdLanchonetes(id).enqueue(callback);
    }
}
