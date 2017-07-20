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

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.messaging.FirebaseMessaging;
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

import br.dpacanhella.miguelopolis.adapter.TipoAnuncioAdapter;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.TipoAnuncio;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskExecutor;
import br.dpacanhella.miguelopolis.util.task.AsyncTaskResult;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by infra on 13/06/17.
 */

public class UtilitarioActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Drawer result = null;

    private RecyclerView recyclerTipos;
    private TipoAnuncioAdapter anunciosAdapter;

    private AsyncTaskExecutor taskExecutor;
    private FarmaciaBO farmaciaBO;

    @Bind(R.id.progress_bar_utilitarios)
    CircularProgressView mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.activity_utilitarios);

        FirebaseMessaging.getInstance().subscribeToTopic("TODOS");

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("  Utilitários");
        mToolbar.setLogo(R.drawable.home);
        setSupportActionBar(mToolbar);

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
                .withSelectedItem(1)
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
                                intent = new Intent(UtilitarioActivity.this, FarmaciaActivity.class);
                            }else if(drawerItem.getIdentifier() == 454){
                                intent = new Intent(UtilitarioActivity.this, OnibusActivity.class);
                            }else if(drawerItem.getIdentifier() == 1){
                                intent = new Intent(UtilitarioActivity.this, UtilitarioActivity.class);
                            }else if(drawerItem.getIdentifier() == 896){
                                intent = new Intent(UtilitarioActivity.this, RestauranteActivity.class);
                            }else if(drawerItem.getIdentifier() == 222){
                                intent = new Intent(UtilitarioActivity.this, LanchoneteActivity.class);
                            }
                            else if(drawerItem.getIdentifier() == 987){
                                intent = new Intent(UtilitarioActivity.this, LojaActivity.class);
                            }

                        }

                        if (intent != null) {
                            UtilitarioActivity.this.startActivity(intent);
                        }

                        return false;
                    }
                })
                .build();

        ButterKnife.bind(this);

        this.taskExecutor = new AsyncTaskExecutor();
        this.farmaciaBO = new FarmaciaBO();

        recyclerTipos = (RecyclerView) findViewById(R.id.main_recycler_tipos);
        recyclerTipos.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerTipos.setLayoutManager(llm);
        showTipos();

    }

    private void showTipos() {
        taskExecutor.startExecutor(new AppAsyncTask<List<TipoAnuncio>>() {
            @Override
            public AsyncTaskResult<List<TipoAnuncio>> onStart() {
                try {
                    return new AsyncTaskResult<>(farmaciaBO.getAllAnuncios());
                } catch (BusinessException e) {
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            public void onFinish(AsyncTaskResult<List<TipoAnuncio>> result) {
                if (result.error() == null) {
                    anunciosAdapter = new TipoAnuncioAdapter(result.response());
                    recyclerTipos.setLayoutManager(new LinearLayoutManager(UtilitarioActivity.this));
                    recyclerTipos.setAdapter(anunciosAdapter);
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UtilitarioActivity.this, R.style.DialogTheme);
                    builder.setTitle(R.string.dialog_title_error)
                            .setMessage(R.string.getAll_utilitarios_error)
                            .setPositiveButton(R.string.label_ok, null);
                    builder.show();
                }
            }
        });
    }
}
