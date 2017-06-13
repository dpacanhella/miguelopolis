package br.dpacanhella.miguelopolis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import butterknife.ButterKnife;

/**
 * Created by infra on 13/06/17.
 */

public class OnibusActivity extends AppCompatActivity {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;
    private Toolbar mToolbar;

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
        mToolbar.setTitle("     São Bento");
        mToolbar.setSubtitle("       Horários");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);


    }
}
