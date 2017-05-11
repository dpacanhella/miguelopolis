package redspark.io.miguelopolis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import redspark.io.miguelopolis.data.api.WebServiceClient;
import redspark.io.miguelopolis.data.business.farmacia.FarmaciaBO;
import redspark.io.miguelopolis.data.dao.FarmaciaDao;
import redspark.io.miguelopolis.data.dao.IServiceResponse;
import redspark.io.miguelopolis.data.model.Farmacia;

public class MainActivity extends AppCompatActivity {

    private FarmaciaBO farmaciaBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.farmaciaBO = new FarmaciaBO();


        //colocar dentro de um metodo async
        new FarmaciaDao().getAll(new IServiceResponse<List<Farmacia>>() {
            @Override
            public void onSuccess(List<Farmacia> data) {
                Log.i("Hello: ", data.get(0).getNomeProprietario());
            }

            @Override
            public void onError(String error) {
                Log.i("no: ", "malHord");
            }
        });
    }
}
