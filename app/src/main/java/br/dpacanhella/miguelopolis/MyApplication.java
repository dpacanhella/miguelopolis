package br.dpacanhella.miguelopolis;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.HashMap;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
