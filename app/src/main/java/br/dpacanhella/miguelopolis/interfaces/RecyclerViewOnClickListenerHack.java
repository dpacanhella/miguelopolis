package br.dpacanhella.miguelopolis.interfaces;

import android.view.View;

/**
 * Created by infra on 30/06/17.
 */

public interface RecyclerViewOnClickListenerHack {
    public void onClickListener(View view, int position);
    public void onLongPressClickListener(View view, int position);
}
