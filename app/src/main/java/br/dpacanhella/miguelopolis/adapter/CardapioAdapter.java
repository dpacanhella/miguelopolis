package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.Cardapio;
import br.dpacanhella.miguelopolis.data.model.Promocao;

/**
 * Created by infra on 06/07/17.
 */

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.ViewHolder> {

    List<Cardapio> cardapioList;
    View view;

    public CardapioAdapter(List<Cardapio> doctorList) {
        this.cardapioList = doctorList;
    }

    @Override
    public CardapioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardapios, parent, false);
        CardapioAdapter.ViewHolder viewHolder = new CardapioAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardapioAdapter.ViewHolder holder, int position) {
        holder.populate(cardapioList.get(position));
    }

    @Override
    public int getItemCount() {
        return cardapioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView_cardapio);
        }

        public void populate(Cardapio cardapio) {
            Picasso.with(view.getContext()).load(cardapio.getImagem().toString())
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
}
