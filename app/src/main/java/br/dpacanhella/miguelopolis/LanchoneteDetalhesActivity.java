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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import br.dpacanhella.miguelopolis.adapter.CardapioAdapter;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Cardapio;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by infra on 07/07/17.
 */

public class LanchoneteDetalhesActivity extends AppCompatActivity {

    @Bind(R.id.progress_bar_lanchonete)
    CircularProgressView mProgressBar;

    private FirebaseAnalytics mFirebaseAnalytics;

    TabHost tabHos;
    private RecyclerView recyclerCardapios;
    private CardapioAdapter cardapioAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

    private Toolbar mToolbar;

    Serializable id = null;

    TextView txtNomeProprietario;
    TextView txtEndereco;
    TextView txtNome;
    TextView txtTelefone;
    ImageView imageView;
    Button botaoLigar;
    TextView txtWhatApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.lanchonete_detalhes);

        ButterKnife.bind(this);

        id = getIntent().getSerializableExtra("id");
        Serializable endereco = getIntent().getSerializableExtra("endereco");
        Serializable nomeProprietario = getIntent().getSerializableExtra("nomeProprietario");
        final Serializable nome = getIntent().getSerializableExtra("nome");
        Serializable imagem = getIntent().getSerializableExtra("imagemEstabelecimento");
        final Serializable telefone = getIntent().getSerializableExtra("telefone");
        ArrayList<Cardapio> cardapios = getIntent().getParcelableArrayListExtra("cardapios");
        Serializable whatApp = getIntent().getSerializableExtra("whatsApp");

        txtNome = (TextView) findViewById(R.id.lanchonete_nome);
        txtEndereco = (TextView) findViewById(R.id.lanchonete_endereco);
        txtTelefone = (TextView) findViewById(R.id.lanchonete_telefone);
        txtWhatApp = (TextView) findViewById(R.id.lanchonete_whatsApp);
        txtNomeProprietario = (TextView) findViewById(R.id.lanchonete_proprietario);
        imageView = (ImageView) findViewById(R.id.lanchonete_imagem_estabelecimento);
        botaoLigar = (Button) findViewById(R.id.lanchonete_btnCall);

        mToolbar = (Toolbar) findViewById(R.id.tb_main_lanchonete);
        mToolbar.setTitle("  " + nome.toString());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNomeProprietario.setText(nomeProprietario.toString());
        String lblTelefone = "Telefone: (16) ";
        String lblEndereco = "Endereço: ";
        String lblWhats = "WhatsApp: (16) ";
        txtEndereco.setText(lblEndereco + endereco.toString());
        txtNome.setText(nome.toString());
        txtTelefone.setText(lblTelefone + telefone.toString());
        txtWhatApp.setText(lblWhats + whatApp.toString());

        loadImageFromURL(imagem.toString());

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Detalhes Lanchonetes");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("detalhes_" + nome.toString(), bundle);

        botaoLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String telefoneLigar = "tel:" + telefone.toString();
                intent.setData(Uri.parse(telefoneLigar));
                startActivity(intent);
            }
        });

        final TabHost host = (TabHost) findViewById(R.id.tabHost_lanchonete);
        host.setup();

        //TAB DETALHES
        TabHost.TabSpec spec = host.newTabSpec("Detalhes");
        spec.setContent(R.id.tab1_lanchonete);
        spec.setIndicator("Detalhes");
        host.addTab(spec);

        spec = host.newTabSpec("Cardápio");
        spec.setContent(R.id.tab2_lanchonete);
        spec.setIndicator("Cardápio");
        host.addTab(spec);


        host.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#097369"));
        TextView tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(host, nome.toString());
            }
        });

        //Chamar cardápios
        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerCardapios = (RecyclerView) findViewById(R.id.main_recycler_cardapios_lanchonete);
        recyclerCardapios.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCardapios.setLayoutManager(llm);
        showPromocoes(cardapios);
    }

    private void showPromocoes(final ArrayList<Cardapio> cardapios) {
        taskExecutor.startExecutor(new AppAsyncTask<ArrayList<Cardapio>>() {
            @Override
            public AsyncTaskResult<ArrayList<Cardapio>> onStart() {
                return new AsyncTaskResult<>(cardapios);
            }

            @Override
            public void onFinish(AsyncTaskResult<ArrayList<Cardapio>> result) {
                if (result.error() == null) {
                    cardapioAdapter = new CardapioAdapter(result.response());
                    recyclerCardapios.setLayoutManager(new LinearLayoutManager(LanchoneteDetalhesActivity.this));
                    recyclerCardapios.setAdapter(cardapioAdapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LanchoneteDetalhesActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_cardapios_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }

    private void setTabColor(TabHost tabhost, String nome) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#028478"));
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#000000"));
        }

        if(tabhost.getCurrentTab()==0) {

            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369"));
            TextView tv = (TextView) tabhost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
            tv.setTextColor(Color.parseColor("#ffffff"));

        }else {
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369"));
            TextView tv = (TextView) tabhost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
            tv.setTextColor(Color.parseColor("#ffffff"));

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Cardápios");
            mFirebaseAnalytics.logEvent("cardápio_" + nome.toString(), bundle);

        }
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
