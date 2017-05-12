package redspark.io.miguelopolis.data.dao;

import java.util.List;

import redspark.io.miguelopolis.data.api.WebService;
import redspark.io.miguelopolis.data.api.WebServiceClient;
import redspark.io.miguelopolis.data.model.Farmacia;
import retrofit2.Call;

/**
 * Created by infra on 10/05/17.
 */

public class FarmaciaDao {

    private WebService wsClient;

    public FarmaciaDao(){
        wsClient = new WebServiceClient().build();
    }


    public void getAll()  {
            Call<List<Farmacia>> call = wsClient.getAll();

    }
}
