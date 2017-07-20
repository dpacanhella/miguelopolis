package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by infra on 20/07/17.
 */

public class ImagemLojaAdapter extends RecyclerView.Adapter<ImagemLojaAdapter.ViewHolder> {

    @Override
    public ImagemLojaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ImagemLojaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {

            super(itemView);
        }
    }
}
