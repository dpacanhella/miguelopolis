package redspark.io.miguelopolis;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import redspark.io.miguelopolis.data.model.FarmaciaDetalhes;

/**
 * Created by diegoPacanhella on 20/05/17.
 */

public class DetalhesActivity extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);// Add THIS LINE

//        getIntent().getExtras("farmacia");

        setContentView(R.layout.farmacia_detalhes);
    }

}
