package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diegoPacanhella on 20/05/17.
 */

public class DetalhesActivity extends AppCompatActivity {

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
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.farmacia_detalhes);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("     Farma Migue");
        mToolbar.setSubtitle("       Detalhes");
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

