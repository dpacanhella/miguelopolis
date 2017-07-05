package br.dpacanhella.miguelopolis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.Serializable;

import br.dpacanhella.miguelopolis.adapter.PromocoesAdapter;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by infra on 05/07/17.
 */

public class RestauranteDetalhesActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Bind(R.id.progress_bar_restaurante)
    CircularProgressView mProgressBar;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.restaurante_detalhes);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Detalhes Restaurantes");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("detalhes_restaurantes", bundle);

        mToolbar = (Toolbar) findViewById(R.id.tb_main_restaurante);
        mToolbar.setTitle("Testeeeeee");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
    }
}
