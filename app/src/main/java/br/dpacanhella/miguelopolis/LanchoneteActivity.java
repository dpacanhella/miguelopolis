package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import br.dpacanhella.miguelopolis.adapter.LanchoneteAdapter;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Lanchonete;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by infra on 07/07/17.
 */

public class LanchoneteActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FirebaseAnalytics mFirebaseAnalytics;
    private Drawer result = null;

    private List<Lanchonete> mLanchoneteList;

    private RecyclerView recyclerLanchonetes;
    private LanchoneteAdapter lanchoneteAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

    @Bind(R.id.progress_bar_lanchonete)
    CircularProgressView mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.activity_lanchonetes);

        mToolbar = (Toolbar) findViewById(R.id.tb_main_lanchonete);
        mToolbar.setTitle("  Lanchonetes/Salgadarias");
        setSupportActionBar(mToolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Lanchonetes/Salgadarias");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("lanchonetes_salgadarias", bundle);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header4)
                .addProfiles(
                        new ProfileDrawerItem().withName("Guia Miguelópolis").withEmail("dpacanhella@gmail.com").withIcon(getResources().getDrawable(R.mipmap.icon_medicamentos))
                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return true;
                    }
                }).build();


        PrimaryDrawerItem home = new PrimaryDrawerItem().withName("Utilitários").withIdentifier(1).withIcon(R.drawable.home);
        SecondaryDrawerItem farmacias = new SecondaryDrawerItem().withName("Plantão/Farmácias").withIdentifier(123).withIcon(R.drawable.ic_launcher);
        SecondaryDrawerItem horariosOnibus = new SecondaryDrawerItem().withName("Horários de ônibus").withIdentifier(454).withIcon(R.drawable.bus_icon);
        SecondaryDrawerItem bares_restaurantes = new SecondaryDrawerItem().withName("Bares/Restaurantes").withIdentifier(896).withIcon(R.drawable.icon_restaurante);
        SecondaryDrawerItem lanchonetes = new SecondaryDrawerItem().withName("Lanchonetes/Salgadarias").withIdentifier(222).withIcon(R.drawable.icon_fast);
        SecondaryDrawerItem lojas = new SecondaryDrawerItem().withName("Lojas").withIdentifier(987).withIcon(R.drawable.icon_loja);

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withSelectedItem(222)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        home,
                        new DividerDrawerItem(),
                        farmacias,
                        new DividerDrawerItem(),
                        horariosOnibus,
                        new DividerDrawerItem(),
                        bares_restaurantes,
                        new DividerDrawerItem(),
                        lanchonetes,
                        new DividerDrawerItem(),
                        lojas

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        System.out.println(drawerItem);

                        Intent intent = null;

                        if (drawerItem != null){
                            if(drawerItem.getIdentifier() == 123){
                                intent = new Intent(LanchoneteActivity.this, FarmaciaActivity.class);
                            }else if(drawerItem.getIdentifier() == 454){
                                intent = new Intent(LanchoneteActivity.this, OnibusActivity.class);
                            }else if(drawerItem.getIdentifier() == 1){
                                intent = new Intent(LanchoneteActivity.this, UtilitarioActivity.class);
                            }else if(drawerItem.getIdentifier() == 896){
                                intent = new Intent(LanchoneteActivity.this, RestauranteActivity.class);
                            }else if(drawerItem.getIdentifier() == 222){
                                intent = new Intent(LanchoneteActivity.this, LanchoneteActivity.class);
                            }else if(drawerItem.getIdentifier() == 987){
                                intent = new Intent(LanchoneteActivity.this, LojaActivity.class);
                            }
                        }

                        if (intent != null) {
                            LanchoneteActivity.this.startActivity(intent);
                        }

                        return false;
                    }
                })
                .build();
        ButterKnife.bind(this);

        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerLanchonetes = (RecyclerView) findViewById(R.id.main_recycler_lanchonete);
        mLanchoneteList = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerLanchonetes.setLayoutManager(mLayoutManager);
        recyclerLanchonetes.addItemDecoration(new LanchoneteActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerLanchonetes.setItemAnimator(new DefaultItemAnimator());

        showLanchonetes();


    }

    private void showLanchonetes() {
        taskExecutor.startExecutor(new AppAsyncTask<List<Lanchonete>>() {
            @Override
            public AsyncTaskResult<List<Lanchonete>> onStart() {
                try {
                    return new AsyncTaskResult<>(farmaciaBO.getAllLanchonetes());
                } catch (BusinessException e) {
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            public void onFinish(AsyncTaskResult<List<Lanchonete>> result) {
                if (result.error() == null) {
                    lanchoneteAdapter = new LanchoneteAdapter(result.response());
                    recyclerLanchonetes.setAdapter(lanchoneteAdapter);
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LanchoneteActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_lanchonetes_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}