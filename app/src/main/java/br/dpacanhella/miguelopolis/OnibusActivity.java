package br.dpacanhella.miguelopolis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.ButterKnife;

/**
 * Created by infra on 13/06/17.
 */

public class OnibusActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Drawer result = null;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Add THIS LINE
        setContentView(R.layout.onibus_horarios);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("  Horários");
        mToolbar.setLogo(R.drawable.bus_icon);
        setSupportActionBar(mToolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Ônibus");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("onibus", bundle);

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

        //Notificação
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        long[] vibrate = { 0, 100, 200, 300 };
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(OnibusActivity.this, 0, getIntent(), 0);
//        Notification noti = new Notification.Builder(OnibusActivity.this).setTicker("Farma Migue").setContentTitle("Farma Migue")
//                .setContentText("Atenção: O plantão foi alterado")
//                .setSmallIcon(R.mipmap.icon_medicamentos)
//                .setLargeIcon(((BitmapDrawable) getApplicationContext().getResources().getDrawable(R.mipmap.icon_medicamentos)).getBitmap())
//                .setSound(alarmSound)
//                .setVibrate(vibrate)
//                .setContentIntent(pendingIntent).getNotification();
//
//        noti.flags = Notification.FLAG_AUTO_CANCEL;
//
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(0, noti);

    }
}
