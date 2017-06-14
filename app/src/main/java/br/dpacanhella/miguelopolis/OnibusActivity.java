package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
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

import butterknife.ButterKnife;

/**
 * Created by infra on 13/06/17.
 */

public class OnibusActivity extends AppCompatActivity {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;
    private Toolbar mToolbar;
    private Drawer result = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.onibus_horarios);

        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker("UA-100401477-2");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("  Horários");
        mToolbar.setLogo(R.drawable.bus_icon);
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


        PrimaryDrawerItem home = new PrimaryDrawerItem().withName("Home").withIdentifier(1).withIcon(R.drawable.home);
        SecondaryDrawerItem farmacias = new SecondaryDrawerItem().withName("Plantão/Farmácias").withIdentifier(123).withIcon(R.drawable.ic_launcher);
        SecondaryDrawerItem horariosOnibus = new SecondaryDrawerItem().withName("Horários de ônibus").withIdentifier(454).withIcon(R.drawable.bus_icon);

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withSelectedItem(454)
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
                        horariosOnibus

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        System.out.println(drawerItem);

                        Intent intent = null;

                        if (drawerItem != null){
                            if(drawerItem.getIdentifier() == 123){
                                intent = new Intent(OnibusActivity.this, FarmaciaActivity.class);
                            }else if(drawerItem.getIdentifier() == 454){
                                intent = new Intent(OnibusActivity.this, OnibusActivity.class);
                            }else if(drawerItem.getIdentifier() == 1){
                                intent = new Intent(OnibusActivity.this, HomeActivity.class);
                            }
                        }

                        if (intent != null) {
                            OnibusActivity.this.startActivity(intent);
                        }

                        return false;
                    }
                })
                .build();


        ButterKnife.bind(this);


    }
}
