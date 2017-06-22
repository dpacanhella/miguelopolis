package br.dpacanhella.miguelopolis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.Bind;

/**
 * Created by infra on 20/06/17.
 */

public class PromocaoActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Bind(R.id.progress_bar)
    CircularProgressView mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.activity_promocoes);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("  Promoções");
        mToolbar.setLogo(R.drawable.ic_launcher);

        setSupportActionBar(mToolbar);
    }
}
