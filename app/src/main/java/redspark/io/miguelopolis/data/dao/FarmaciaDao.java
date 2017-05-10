package redspark.io.miguelopolis.data.dao;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import redspark.io.miguelopolis.data.api.WebService;
import redspark.io.miguelopolis.data.api.WebServiceClient;
import redspark.io.miguelopolis.data.model.Farmacia;
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


    public List<Farmacia> getAll()  {
        try {
            Call<List<Farmacia>> call = wsClient.getAll();
            Response<List<Farmacia>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                System.out.println(response.errorBody());
            }
        } catch (IOException e) {
            System.out.println("Erro ao chamar WebService " + e);
        }
        return null;
    }
}
