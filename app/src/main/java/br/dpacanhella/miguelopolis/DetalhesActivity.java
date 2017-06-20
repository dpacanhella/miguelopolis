package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import br.dpacanhella.miguelopolis.adapter.FarmaciasAdapter;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Promocao;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diegoPacanhella on 20/05/17.
 */

public class DetalhesActivity extends AppCompatActivity {

    TabHost tabHos;
    private RecyclerView recyclerPromocoes;
    private FarmaciasAdapter farmaciaAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

    public static GoogleAnalytics analytics;
    public static Tracker tracker;
    private Toolbar mToolbar;

    @Bind(R.id.progress_bar)
    CircularProgressView mProgressBar;

    TextView txtNomeProprietario;
    TextView txtEndereco;
    TextView txtRazao;
    TextView txtTelefone;
    TextView txtObservacao;
    TextView lblObservacao;
    ImageView imageView;
    Button botaoLigar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.farmacia_detalhes);

        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker("UA-100401477-2");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("  Farmácia");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Serializable endereco = getIntent().getSerializableExtra("endereco");
        Serializable nomeProprietario = getIntent().getSerializableExtra("nomeProprietario");
        Serializable razao = getIntent().getSerializableExtra("razao");
        Serializable imagem = getIntent().getSerializableExtra("imagem");
        final Serializable telefone = getIntent().getSerializableExtra("telefone");

        txtNomeProprietario = (TextView) findViewById(R.id.proprietario);
        txtEndereco = (TextView) findViewById(R.id.endereco);
        txtRazao = (TextView) findViewById(R.id.razao);
        txtTelefone = (TextView) findViewById(R.id.telefone);
        imageView = (ImageView) findViewById(R.id.imagem);
        botaoLigar = (Button) findViewById(R.id.btnCall);
        txtObservacao = (TextView) findViewById(R.id.observacao);
        lblObservacao = (TextView) findViewById(R.id.lblObservacao);


        txtNomeProprietario.setText(nomeProprietario.toString());
        String lblTelefone = "Telefone: (16) ";
        String lblEndereco = "Endereço: ";
        txtEndereco.setText(lblEndereco + endereco.toString());
        txtRazao.setText(razao.toString());
        txtTelefone.setText(lblTelefone + telefone.toString());

        if (telefone.equals("3835 5555")) {
            txtObservacao.setText("- O plantão da Morifarma é realizado na Drograria Total - Centro");
        } else {
            txtObservacao.setVisibility(View.GONE);
            lblObservacao.setVisibility(View.GONE);
        }

        loadImageFromURL(imagem.toString());

        botaoLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String telefoneLigar = "tel:" + telefone.toString();
                intent.setData(Uri.parse(telefoneLigar));
                startActivity(intent);
            }
        });

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //TAB DETALHES
        TabHost.TabSpec spec = host.newTabSpec("Detalhes");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Detalhes");
        host.addTab(spec);

        //TABLE PROMOCOES
        spec = host.newTabSpec("Promoções");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Promoções");
        host.addTab(spec);

        //Chamar promoções
        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerPromocoes = (RecyclerView) findViewById(R.id.main_recycler_promocoes);
        recyclerPromocoes.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerPromocoes.setLayoutManager(llm);
        showPromocoes();

    }

    private void showPromocoes() {

    }

    private void loadImageFromURL(String s) {
        Picasso.with(this).load(s)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

}

