package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import br.dpacanhella.miguelopolis.adapter.PromocoesAdapter;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Promocao;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diegoPacanhella on 20/05/17.
 */

public class DetalhesActivity extends AppCompatActivity {

    TabHost tabHos;
    private RecyclerView recyclerPromocoes;
    private PromocoesAdapter promocaoAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

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
        ArrayList<Promocao> promocoes = getIntent().getParcelableArrayListExtra("promocoes");

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

        final TabHost host = (TabHost) findViewById(R.id.tabHost);
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

        host.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#097369"));

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(host);
            }
        });
        
        //Chamar promoções
        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerPromocoes = (RecyclerView) findViewById(R.id.main_recycler_promocoes);
        recyclerPromocoes.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerPromocoes.setLayoutManager(llm);
        showPromocoes(promocoes);

    }

    private void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#009688")); //unselected

        if(tabhost.getCurrentTab()==0)
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369")); //1st tab selected
        else
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369")); //2nd tab selected
    }

    private void showPromocoes(final ArrayList<Promocao> promocoes) {
        taskExecutor.startExecutor(new AppAsyncTask<ArrayList<Promocao>>() {
            @Override
            public AsyncTaskResult<ArrayList<Promocao>> onStart() {
                return new AsyncTaskResult<>(promocoes);
            }

            @Override
            public void onFinish(AsyncTaskResult<ArrayList<Promocao>> result) {
                if (result.error() == null) {
                    promocaoAdapter = new PromocoesAdapter(result.response());
                    recyclerPromocoes.setLayoutManager(new LinearLayoutManager(DetalhesActivity.this));
                    recyclerPromocoes.setAdapter(promocaoAdapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetalhesActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_promocao_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
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

