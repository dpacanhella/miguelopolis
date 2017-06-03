package br.dpacanhella.miguelopolis;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.adapter.FarmaciasAdapter;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;

public class MainActivity extends AppCompatActivity {

//    @Bind(R.id.farmacia_swipe_refresh)
//    SwipeRefreshLayout mSwipeRefresh;
//    @Bind(R.id.farmacia_recycler_view)
//    RecyclerView mRecyclerView;

    private List<Farmacia> mFarmaciaList;

    private RecyclerView recyclerFarmacias;
    private FarmaciasAdapter farmaciaAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("     Farma Migue");
        mToolbar.setSubtitle("       Plantão da semana");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerFarmacias = (RecyclerView) findViewById(R.id.main_recycler_farmacias);
        recyclerFarmacias.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFarmacias.setLayoutManager(llm);
        showFarmacias();
    }

    private void showFarmacias() {
        taskExecutor.startExecutor(new AppAsyncTask<List<Farmacia>>() {
            @Override
            public AsyncTaskResult<List<Farmacia>> onStart() {
                try {
                    return new AsyncTaskResult<>(farmaciaBO.getAll());
                } catch (BusinessException e) {
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            public void onFinish(AsyncTaskResult<List<Farmacia>> result) {
                if (result.error() == null) {
                    farmaciaAdapter = new FarmaciasAdapter(result.response(), null);
                    recyclerFarmacias.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerFarmacias.setAdapter(farmaciaAdapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_farmacias_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }
}
