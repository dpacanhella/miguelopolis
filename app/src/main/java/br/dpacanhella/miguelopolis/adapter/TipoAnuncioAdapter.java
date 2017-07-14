package br.dpacanhella.miguelopolis.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import br.dpacanhella.miguelopolis.ItemUtilitarioActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.TipoAnuncio;

/**
 * Created by infra on 11/07/17.
 */

public class TipoAnuncioAdapter extends RecyclerView.Adapter<TipoAnuncioAdapter.ViewHolder> {

    List<TipoAnuncio> tipoAnuncioList;
    private TipoAnuncio tipoAnuncio = null;
    private FirebaseAnalytics mFirebaseAnalytics;
    View view;

    public TipoAnuncioAdapter(List<TipoAnuncio> doctorList) {
        this.tipoAnuncioList = doctorList;
    }

    @Override
    public TipoAnuncioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tipos_utilitarios, parent, false);
        TipoAnuncioAdapter.ViewHolder viewHolder = new TipoAnuncioAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TipoAnuncioAdapter.ViewHolder holder, final int position) {
        holder.populate(tipoAnuncioList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoAnuncio = tipoAnuncioList.get(position);


                String anuncio = tipoAnuncio.getDescricao();

                montaResumoAnalytics(anuncio);

                showDetalhes(holder.itemView.getContext(), anuncio);


            }
        });
    }

    private void montaResumoAnalytics(String anuncio) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(view.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, anuncio);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("Anuncio_"+ anuncio, bundle);
    }

    private void showDetalhes(Context context, String anuncio) {
        Intent intent = new Intent(context, ItemUtilitarioActivity.class);

        intent.putExtra("anuncio", anuncio);

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return tipoAnuncioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtQtdeAnuncio;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.item_tipo_utilitario);
            txtQtdeAnuncio = (TextView) itemView.findViewById(R.id.qtde_item_utilitario);
        }

        public void populate(TipoAnuncio tipoAnuncio) {
            txtName.setText(tipoAnuncio.getDescricao());
            String qtdeAnuncio = Integer.toString(tipoAnuncio.getQtdeUtilitario());
            txtQtdeAnuncio.setText(qtdeAnuncio);
        }
    }
}
