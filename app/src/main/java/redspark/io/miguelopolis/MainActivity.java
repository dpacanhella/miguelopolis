package redspark.io.miguelopolis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import redspark.io.miguelopolis.data.business.farmacia.FarmaciaBO;

public class MainActivity extends AppCompatActivity {

    private FarmaciaBO farmaciaBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.farmaciaBO = new FarmaciaBO();
        farmaciaBO.getAll();
    }
}
