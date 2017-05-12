package redspark.io.miguelopolis;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import redspark.io.miguelopolis.adapter.FarmaciasAdapter;
import redspark.io.miguelopolis.data.business.BusinessException;
import redspark.io.miguelopolis.data.business.farmacia.FarmaciaBO;
import redspark.io.miguelopolis.data.model.CellObjetct;
import redspark.io.miguelopolis.data.model.Farmacia;
import redspark.io.miguelopolis.data.model.MoviesAdapter;
import redspark.io.miguelopolis.util.task.AppAsyncTask;
import redspark.io.miguelopolis.util.task.AsyncTaskExecutor;
import redspark.io.miguelopolis.util.task.AsyncTaskResult;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.farmacia_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Bind(R.id.farmacia_recycler_view)
    RecyclerView mRecyclerView;

    private List<Farmacia> mFarmaciaList;
    private List<CellObjetct<Farmacia>> mCellObjects;
    private MoviesAdapter mAdapter;


    private RecyclerView recyclerFarmacias;
    private FarmaciasAdapter farmaciaAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

//        setupToolbar();

        recyclerFarmacias = (RecyclerView) findViewById(R.id.main_recycler_farmacias);

        showFarmacias();
//        setupRecyclerView();
//        setupSwipeRefresh();

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
                    farmaciaAdapter = new FarmaciasAdapter(result.response());
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

//    private void setupSwipeRefresh() {
//        mSwipeRefresh.setColorSchemeResources(R.color.sesc_primary);
//        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getPendingMatriculation();
//            }
//        });
//    }

//    private void getPendingMatriculation() {
//        if(ConnectionUtils.isConnected(getApplicationContext())){
//    new FarmaciaDao().getAll(new IServiceResponse<List<Farmacia>>() {
//        @Override
//        public void onSuccess(List<Farmacia> data) {
//
//            mSwipeRefresh.setRefreshing(false);
//
//            mAdapter = new MoviesAdapter(mFarmaciaList);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//            recycleView.setLayoutManager(mLayoutManager);
//            recycleView.setItemAnimator(new DefaultItemAnimator());
//            recycleView.setAdapter(mAdapter);
//
//            //interando a lista com as informações
//            mFarmaciaList = data;
//            mAdapter.notifyDataSetChanged();
//
//            mRecyclerView.setVisibility(View.VISIBLE);
//        }
//
//
//        @Override
//        public void onError(String error) {
//            mSwipeRefresh.setRefreshing(false);
//            Log.i("ERROR: ", "NÃO FOI POSSÍVEL TRADUZIR MAINACTIVITY");
//        }
//    });
//        }
//    }

    private void setupRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    private void setupToolbar() {
        ActionBar actionBar = ((AppCompatActivity) getApplicationContext()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.frag_farmacia_toolbar_title);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
