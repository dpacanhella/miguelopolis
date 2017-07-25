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
 * Created by infra on 05/07/17.
 */

public class RestauranteDetalhesActivity extends AppCompatActivity {

    @Bind(R.id.progress_bar_restaurante)
    CircularProgressView mProgressBar;

    private FirebaseAnalytics mFirebaseAnalytics;

    TabHost tabHos;
    private RecyclerView recyclerCardapios;
    private CardapioAdapter cardapioAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

    private Toolbar mToolbar;

    Serializable id = null;

    TextView txtNome;
    TextView txtEndereco;
    TextView txtTelefone;
    TextView txtWhatApp;
    TextView txtDescricao;
    TextView txtDescricao2;
    TextView txtDescricao3;
    TextView txtDescricao4;
    TextView txtDescricao5;
    ImageView imageEstabelecimento;
    ImageView image1;
    ImageView image2;
    Button botaoLigar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.restaurantes_detalhes);

        ButterKnife.bind(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Detalhes Restaurantes");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");

        id = getIntent().getSerializableExtra("id");
        final Serializable nome = getIntent().getSerializableExtra("nome");
        Serializable descricao = getIntent().getSerializableExtra("descricao");
        final Serializable proprietario = getIntent().getSerializableExtra("nomeProprietario");
        Serializable descricao2 = getIntent().getSerializableExtra("descricao2");
        Serializable descricao3 = getIntent().getSerializableExtra("descricao3");
        Serializable descricao4 = getIntent().getSerializableExtra("descricao4");
        Serializable descricao5 = getIntent().getSerializableExtra("descricao5");
        Serializable endereco = getIntent().getSerializableExtra("endereco");
        Serializable imagemEstabelecimento = getIntent().getSerializableExtra("imagemEstabelecimento");
        Serializable imagem1 = getIntent().getSerializableExtra("imagem1");
        Serializable imagem2 = getIntent().getSerializableExtra("imagem2");
        final Serializable telefone = getIntent().getSerializableExtra("telefone");
        Serializable whatApp = getIntent().getSerializableExtra("whatsApp");
        ArrayList<Cardapio> cardapios = getIntent().getParcelableArrayListExtra("cardapios");

        txtNome = (TextView) findViewById(R.id.restaurante_nome);
        txtEndereco = (TextView) findViewById(R.id.restaurante_endereco);
        txtTelefone = (TextView) findViewById(R.id.restaurante_telefone);
        txtWhatApp = (TextView) findViewById(R.id.restaurante_whatsApp);
        txtDescricao = (TextView) findViewById(R.id.restaurante_descricao);
        txtDescricao2 = (TextView) findViewById(R.id.restaurante_descricao2);
        txtDescricao3 = (TextView) findViewById(R.id.restaurante_descricao3);
//        txtDescricao4 = (TextView) findViewById(R.id.restaurante_descricao4);
        txtDescricao5 = (TextView) findViewById(R.id.restaurante_descricao5);
        imageEstabelecimento = (ImageView) findViewById(R.id.restaurante_imagem_estabelecimento);
        image1 = (ImageView) findViewById(R.id.restaurante_imagem_2);
        image2 = (ImageView) findViewById(R.id.restaurante_imagem_3);
        botaoLigar = (Button) findViewById(R.id.restaurante_btnCall);

        String lblTelefone = "Telefone: (16) ";
        String lblEndereco = "Endereço: ";
        String lblWhats = "WhatsApp: (16) ";
        String lblRestaurante = "Restaurante ";
        txtNome.setText(lblRestaurante + nome.toString());
        txtEndereco.setText(lblEndereco + endereco);
        txtDescricao.setText(descricao.toString());

        if(descricao2 != null) {
            txtDescricao2.setText(descricao2.toString());
        }else{
            txtDescricao2.setVisibility(View.GONE);
        }

        if(descricao3 != null) {
            txtDescricao3.setText(descricao3.toString());
        }else{
            txtDescricao3.setVisibility(View.GONE);
        }

        if(descricao5 != null) {
            txtDescricao5.setText(descricao5.toString());
        }else{
            txtDescricao5.setVisibility(View.GONE);
        }

        txtTelefone.setText(lblTelefone + telefone);
        txtWhatApp.setText(lblWhats + whatApp.toString());

        mToolbar = (Toolbar) findViewById(R.id.tb_main_restaurante);
        mToolbar.setTitle("  " + lblRestaurante + nome.toString());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadImageFromURLEstabelecimento(imagemEstabelecimento.toString());
        loadImageFromURLImagem1(imagem1.toString());
        loadImageFromURLImagem2(imagem2.toString());

        botaoLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String telefoneLigar = "tel:" + telefone.toString();
                intent.setData(Uri.parse(telefoneLigar));
                startActivity(intent);
            }
        });

        final TabHost host = (TabHost) findViewById(R.id.tabHost_restaurante);
        host.setup();

        //TAB DETALHES
        TabHost.TabSpec spec = host.newTabSpec("Detalhes");
        spec.setContent(R.id.tab1_restaurante);
        spec.setIndicator("Detalhes");
        host.addTab(spec);

        //TABLE PROMOCOES
        if (descricao4 != null) {
            spec = host.newTabSpec(descricao4.toString());
            spec.setContent(R.id.tab2_restaurante);
            spec.setIndicator(descricao4.toString());
            host.addTab(spec);
        }

        spec = host.newTabSpec("Cardápio");
        spec.setContent(R.id.tab3_restaurante);
        spec.setIndicator("Cardápio");
        host.addTab(spec);

        if(cardapios.isEmpty()){
            TextView txtNotPromocoes = (TextView) findViewById(R.id.semCardapiosCadastrados);

            txtNotPromocoes.setText("Não há cardápio cadastrado no momento.");
        }

        host.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#097369"));
        TextView tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(host, proprietario.toString());
            }
        });

        //Chamar cardápios
        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerCardapios = (RecyclerView) findViewById(R.id.main_recycler_cardapios);
        recyclerCardapios.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCardapios.setLayoutManager(llm);
        showPromocoes(cardapios);

    }

    private void setTabColor(TabHost tabhost, String proprietario) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#028478"));
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#000000"));
        }

        if(tabhost.getCurrentTab()==0) {

            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369"));
            TextView tv = (TextView) tabhost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
            tv.setTextColor(Color.parseColor("#ffffff"));
        }else if(tabhost.getCurrentTab()==1){
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369"));
            TextView tv = (TextView) tabhost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
            tv.setTextColor(Color.parseColor("#ffffff"));

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Almoço/Eventos");
            mFirebaseAnalytics.logEvent("almoco_evento_" + proprietario, bundle);

        }else {
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#097369"));
            TextView tv = (TextView) tabhost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
            tv.setTextColor(Color.parseColor("#ffffff"));

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "2");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Cardápios");
            mFirebaseAnalytics.logEvent("cardapio_" + proprietario, bundle);

        }
    }

    private void loadImageFromURLEstabelecimento(String s) {
        Picasso.with(this).load(s)
                .error(R.mipmap.ic_launcher)
                .into(imageEstabelecimento, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void loadImageFromURLImagem1(String s) {
        Picasso.with(this).load(s)
                .error(R.mipmap.ic_launcher)
                .into(image1, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void loadImageFromURLImagem2(String s) {
        Picasso.with(this).load(s)
                .error(R.mipmap.ic_launcher)
                .into(image2, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
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
                    recyclerCardapios.setLayoutManager(new LinearLayoutManager(RestauranteDetalhesActivity.this));
                    recyclerCardapios.setAdapter(cardapioAdapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RestauranteDetalhesActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_cardapios_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }

}
