package redspark.io.miguelopolis;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
    ImageView imageView;
    Button botaoLigar;
    private Toolbar mToolbar;

    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.farmacia_detalhes);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("     Migue Farmácias");
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


        txtNomeProprietario.setText(nomeProprietario.toString());
        String lblTelefone = "Telefone: (16) ";
        String lblEndereco = "Endereço: ";
        txtEndereco.setText(lblEndereco + endereco.toString());
        txtRazao.setText(razao.toString());
        txtTelefone.setText(lblTelefone + telefone.toString());
        
        loadImageFromURL(imagem.toString());


//        imageView.setImageBitmap(bitmap);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mProgressBar.setVisibility(View.GONE);
//            }
//        }, 2500);




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

