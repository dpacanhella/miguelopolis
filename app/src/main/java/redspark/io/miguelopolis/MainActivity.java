package redspark.io.miguelopolis;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import redspark.io.miguelopolis.data.dao.FarmaciaDao;
import redspark.io.miguelopolis.data.dao.IServiceResponse;
import redspark.io.miguelopolis.data.model.ConnectionUtils;
import redspark.io.miguelopolis.data.model.Farmacia;
import redspark.io.miguelopolis.data.model.CellObjetct;
import redspark.io.miguelopolis.data.model.MoviesAdapter;
import redspark.io.miguelopolis.data.model.SimpleTextListCellObject;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.farmacia_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Bind(R.id.farmacia_recycler_view)
    RecyclerView mRecyclerView;

    private List<Farmacia> mFarmaciaList;
    private List<CellObjetct<Farmacia>> mCellObjects;
    private MoviesAdapter mAdapter;
    private RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupRecyclerView();
        setupSwipeRefresh();

    }

    private void setupSwipeRefresh() {
        mSwipeRefresh.setColorSchemeResources(R.color.sesc_primary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPendingMatriculation();
            }
        });
    }

    private void getPendingMatriculation() {
        if(ConnectionUtils.isConnected(getApplicationContext())){
    new FarmaciaDao().getAll(new IServiceResponse<List<Farmacia>>() {
        @Override
        public void onSuccess(List<Farmacia> data) {

            mSwipeRefresh.setRefreshing(false);

            mAdapter = new MoviesAdapter(mFarmaciaList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycleView.setLayoutManager(mLayoutManager);
            recycleView.setItemAnimator(new DefaultItemAnimator());
            recycleView.setAdapter(mAdapter);

            //interando a lista com as informações
            mFarmaciaList = data;
            mAdapter.notifyDataSetChanged();

            mRecyclerView.setVisibility(View.VISIBLE);
        }


        @Override
        public void onError(String error) {
            mSwipeRefresh.setRefreshing(false);
            Log.i("ERROR: ", "NÃO FOI POSSÍVEL TRADUZIR MAINACTIVITY");
        }
    });
        }
    }

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
