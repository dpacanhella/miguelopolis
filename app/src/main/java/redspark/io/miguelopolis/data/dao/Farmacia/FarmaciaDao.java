package redspark.io.miguelopolis.data.dao.Farmacia;

import java.io.IOException;
import java.util.List;

import redspark.io.miguelopolis.data.api.WebService;
import redspark.io.miguelopolis.data.api.WebServiceClient;
import redspark.io.miguelopolis.data.dao.DaoException;
import redspark.io.miguelopolis.data.model.Farmacia;
import redspark.io.miguelopolis.data.model.FarmaciaDetalhes;
import retrofit2.Call;
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

    public FarmaciaDetalhes getById(int id) throws DaoException{
        try {
            Call<FarmaciaDetalhes> call = wsClient.getById(id);
            Response<FarmaciaDetalhes> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }else {
                throw new DaoException(response.code(), response.message());
            }
        }catch (IOException e){
            throw new DaoException(DaoException.API_CALL_ERROR, e.getMessage());
        }
    }
}
