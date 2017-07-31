package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.Promocao;

/**
 * Created by infra on 21/06/17.
 */

public class PromocoesAdapter extends RecyclerView.Adapter<PromocoesAdapter.ViewHolder> {

    List<Promocao> promocaoList;
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
        ImageView imageView;

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


            if(promocao.getId() < 7) {
                Glide.with(view.getContext())
                        .load(promocao.getImagemProduto().toString())
                        .placeholder(R.drawable.imageplaceholder)
                        .error(R.drawable.imagenotfound)
                        .into(imageView);
            }else{
                Glide.with(view.getContext())
                        .load(promocao.getImage64())
                        .placeholder(R.drawable.imageplaceholder)
                        .error(R.drawable.imagenotfound)
                        .into(imageView);
            }

        }
    }

}
