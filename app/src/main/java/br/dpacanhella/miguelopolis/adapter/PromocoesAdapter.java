package br.dpacanhella.miguelopolis.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.DetalhesActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.Promocao;

/**
 * Created by infra on 21/06/17.
 */

public class PromocoesAdapter extends RecyclerView.Adapter<PromocoesAdapter.ViewHolder> {

    List<Promocao> promocaoList;
    ImageView imageView;
    View view;

    @Override
    public PromocoesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promocoes, parent, false);
        PromocoesAdapter.ViewHolder viewHolder = new PromocoesAdapter.ViewHolder(view);
        return viewHolder;
    }

    public PromocoesAdapter(List<Promocao> doctorList) {
        this.promocaoList = doctorList;
    }

    @Override
    public void onBindViewHolder(PromocoesAdapter.ViewHolder holder, final int position) {
        holder.populate(promocaoList.get(position));
    }

    @Override
    public int getItemCount() {
        return promocaoList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNomeProduto;
        private TextView txtDescricao;
        private TextView txtValorFinal;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeProduto = (TextView) itemView.findViewById(R.id.item_promocao_nome);
            txtDescricao = (TextView) itemView.findViewById(R.id.item_promocao_valor_inicial);
            txtValorFinal = (TextView) itemView.findViewById(R.id.item_promocao_valor_final);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void populate(Promocao promocao) {

            txtNomeProduto.setText(promocao.getNomeProduto());

            txtDescricao.setText(promocao.getPrecoInicial());

            txtValorFinal.setText(promocao.getPrecoFinal());

            loadImageFromURL(promocao.getImagemProduto().toString());
        }
    }

    private void loadImageFromURL(String s) {
        Picasso.with(view.getContext()).load(s)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
