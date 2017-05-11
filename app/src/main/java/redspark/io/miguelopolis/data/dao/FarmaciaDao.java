package redspark.io.miguelopolis.data.dao;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import redspark.io.miguelopolis.data.api.WebService;
import redspark.io.miguelopolis.data.api.WebServiceClient;
import redspark.io.miguelopolis.data.model.Farmacia;
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


    public void getAll(final IServiceResponse<List<Farmacia>> callback)  {
            Call<List<Farmacia>> call = wsClient.getAll();

            call.enqueue(new Callback<List<Farmacia>>() {
                @Override
                public void onResponse(Call<List<Farmacia>> call, Response<List<Farmacia>> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onError("Erro");
                    }
                }

                @Override
                public void onFailure(Call<List<Farmacia>> call, Throwable t) {
                    callback.onError("Erro ----- ");

                }
            });

    }
}
