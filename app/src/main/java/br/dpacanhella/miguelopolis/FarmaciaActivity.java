package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

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

import java.util.List;

import br.dpacanhella.miguelopolis.adapter.FarmaciasAdapter;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;

import static com.mikepenz.materialdrawer.AccountHeader.OnAccountHeaderListener;

public class FarmaciaActivity extends AppCompatActivity {

    private List<Farmacia> mFarmaciaList;

    private RecyclerView recyclerFarmacias;
    private FarmaciasAdapter farmaciaAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;
    private Toolbar mToolbar;
    private Drawer result = null;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("  Plantão da semana");

        setSupportActionBar(mToolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Farmacia");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("farmacias", bundle);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header4)
                .addProfiles(
                        new ProfileDrawerItem().withName("Guia Miguelópolis").withEmail("dpacanhella@gmail.com").withIcon(getResources().getDrawable(R.mipmap.icon_medicamentos))
                ).withOnAccountHeaderListener(new OnAccountHeaderListener() {
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
                .withSelectedItem(123)
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
                                intent = new Intent(FarmaciaActivity.this, FarmaciaActivity.class);
                            }else if(drawerItem.getIdentifier() == 454){
                                intent = new Intent(FarmaciaActivity.this, OnibusActivity.class);
                            }else if(drawerItem.getIdentifier() == 1){
                                intent = new Intent(FarmaciaActivity.this, UtilitarioActivity.class);
                            }else if(drawerItem.getIdentifier() == 896){
                                intent = new Intent(FarmaciaActivity.this, RestauranteActivity.class);
                            }else if(drawerItem.getIdentifier() == 222){
                                intent = new Intent(FarmaciaActivity.this, LanchoneteActivity.class);
                            }else if(drawerItem.getIdentifier() == 987){
                                intent = new Intent(FarmaciaActivity.this, LojaActivity.class);
                            }
                        }

                        if (intent != null) {
                            FarmaciaActivity.this.startActivity(intent);
                        }

                        return false;
                    }
                })
                .build();

//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


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
                    farmaciaAdapter = new FarmaciasAdapter(result.response());
                    recyclerFarmacias.setLayoutManager(new LinearLayoutManager(FarmaciaActivity.this));
                    recyclerFarmacias.setAdapter(farmaciaAdapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FarmaciaActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_farmacias_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }
}
