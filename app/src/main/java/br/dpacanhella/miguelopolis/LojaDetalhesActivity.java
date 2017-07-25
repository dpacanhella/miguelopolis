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

import com.bumptech.glide.Glide;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.Serializable;
import java.util.ArrayList;

import br.dpacanhella.miguelopolis.adapter.ImagemAdapter;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.ImagemLoja;
import br.dpacanhella.miguelopolis.data.model.LojaDetalhes;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by infra on 20/07/17.
 */

public class LojaDetalhesActivity extends AppCompatActivity {

    TabHost tabHos;
    private RecyclerView recyclerImagens;
    private ImagemAdapter imagemAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;
    private FirebaseAnalytics mFirebaseAnalytics;

    private Toolbar mToolbar;

    Serializable id = null;

    @Bind(R.id.progress_bar_loja_detalhes)
    CircularProgressView mProgressBar;

    TextView txtNome;
    TextView txtEndereco;
    TextView txtDescricao;
    TextView txtTelefone;
    ImageView imageView;
    Button botaoLigar;
    TextView txtWhatApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.loja_detalhes);

        ButterKnife.bind(this);

        final LojaDetalhes detalhes = (LojaDetalhes) getIntent().getExtras().getSerializable("minhaclasse");

        txtNome = (TextView) findViewById(R.id.loja_nome);
        txtEndereco = (TextView) findViewById(R.id.loja_endereco);
        txtDescricao = (TextView) findViewById(R.id.loja_descricao);
        txtTelefone = (TextView) findViewById(R.id.loja_telefone);
        imageView = (ImageView) findViewById(R.id.loja_imagem_estabelecimento);
        botaoLigar = (Button) findViewById(R.id.loja_btnCall);
        txtWhatApp = (TextView) findViewById(R.id.loja_whatsApp);

        mToolbar = (Toolbar) findViewById(R.id.tb_main_loja_detalhes);
        mToolbar.setTitle("  " + detalhes.getNome().toString());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Detalhes Lanchonetes");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");

        txtNome.setText(detalhes.getNome().toString());
        String lblTelefone = "Telefone: (16) ";
        String lblEndereco = "Endereço: ";
        String lblWhats = "WhatsApp: (16) ";
        txtEndereco.setText(lblEndereco + detalhes.getEndereco().toString());
        txtDescricao.setText(detalhes.getDescricao().toString());
        txtTelefone.setText(lblTelefone + detalhes.getTelefone().toString());
        txtWhatApp.setText(lblWhats + detalhes.getCelular().toString());

        loadImageFromURL(detalhes.getImagemEstabelecimento().toString());

        botaoLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String telefoneLigar = "tel:" + detalhes.getTelefone().toString();
                intent.setData(Uri.parse(telefoneLigar));
                startActivity(intent);
            }
        });

        final TabHost host = (TabHost) findViewById(R.id.tabHost_loja);
        host.setup();

        //TAB DETALHES
        TabHost.TabSpec spec = host.newTabSpec("Detalhes");
        spec.setContent(R.id.tab1_loja);
        spec.setIndicator("Detalhes");
        host.addTab(spec);

        spec = host.newTabSpec("Fotos");
        spec.setContent(R.id.tab2_loja);
        spec.setIndicator("Fotos");
        host.addTab(spec);

        if(detalhes.getImagensLojas().isEmpty()){
            TextView txtNotPromocoes = (TextView) findViewById(R.id.semImagensCadastradas);

            txtNotPromocoes.setText("Não há imagens cadastradas no momento.");
        }

        host.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#097369"));
        TextView tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(host, detalhes.getNome().toString());
            }
        });

        //Chamar cardápios
        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerImagens = (RecyclerView) findViewById(R.id.main_recycler_imagens_loja);
        recyclerImagens.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerImagens.setLayoutManager(llm);
        showImagens((ArrayList<ImagemLoja>) detalhes.getImagensLojas());
    }

    private void showImagens(final ArrayList<ImagemLoja> imagens) {
        taskExecutor.startExecutor(new AppAsyncTask<ArrayList<ImagemLoja>>() {
            @Override
            public AsyncTaskResult<ArrayList<ImagemLoja>> onStart() {
                return new AsyncTaskResult<>(imagens);
            }

            @Override
            public void onFinish(AsyncTaskResult<ArrayList<ImagemLoja>> result) {
                if (result.error() == null) {
                    imagemAdapter = new ImagemAdapter(result.response());
                    recyclerImagens.setLayoutManager(new LinearLayoutManager(LojaDetalhesActivity.this));
                    recyclerImagens.setAdapter(imagemAdapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LojaDetalhesActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_imagens_error)
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
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Imagens");
            String nomeFormatado = nome.replaceAll(" ", "_");
            mFirebaseAnalytics.logEvent("imagem_loja_" + nomeFormatado, bundle);

        }
    }

    private void loadImageFromURL(String s) {
        Glide.with(this)
                .load(s)
                .placeholder(R.drawable.imageplaceholder)
                .error(R.drawable.imagenotfound)
                .into(imageView);
    }
}
