package br.dpacanhella.miguelopolis;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.Serializable;
import java.util.ArrayList;

import br.dpacanhella.miguelopolis.adapter.PromocoesAdapter;
import br.dpacanhella.miguelopolis.adapter.UtilitariosAdapter;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Promocao;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by infra on 11/07/17.
 */

public class ItemUtilitarioActivity extends AppCompatActivity {

    private RecyclerView recyclerUtilitarios;
    private UtilitariosAdapter utilitariosAdapter;
    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;
    private Toolbar mToolbar;

    @Bind(R.id.progress_bar_item_utilitarios)
    CircularProgressView mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.activity_item_utilitarios);

        ButterKnife.bind(this);

        ArrayList<Utilitario> utilitarios = getIntent().getParcelableArrayListExtra("utilitarios");

        mToolbar = (Toolbar) findViewById(R.id.tb_main_item_utilitarios);
        mToolbar.setTitle("  Utilitários");
        mToolbar.setLogo(R.drawable.home);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerUtilitarios = (RecyclerView) findViewById(R.id.main_recycler_item_utilitarios);
        recyclerUtilitarios.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerUtilitarios.setLayoutManager(llm);

        showUtilitarios(utilitarios);

    }

    private void showUtilitarios(final ArrayList<Utilitario> utilitarios) {
        taskExecutor.startExecutor(new AppAsyncTask<ArrayList<Utilitario>>() {
            @Override
            public AsyncTaskResult<ArrayList<Utilitario>> onStart() {
                return new AsyncTaskResult<>(utilitarios);
            }

            @Override
            public void onFinish(AsyncTaskResult<ArrayList<Utilitario>> result) {
                if (result.error() == null) {
                    utilitariosAdapter = new UtilitariosAdapter(result.response());
                    recyclerUtilitarios.setLayoutManager(new LinearLayoutManager(ItemUtilitarioActivity.this));
                    recyclerUtilitarios.setAdapter(utilitariosAdapter);
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ItemUtilitarioActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_utilitarios_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }
}
