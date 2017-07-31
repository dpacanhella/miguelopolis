package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.ImagemLoja;

/**
 * Created by infra on 21/07/17.
 */

public class ImagemAdapter extends RecyclerView.Adapter<ImagemAdapter.ViewHolder> {

    List<ImagemLoja> imagemList;
    View view;

    public ImagemAdapter(ArrayList<ImagemLoja> doctorList) {
        this.imagemList = doctorList;
    }

    @Override
    public ImagemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_imagens_lojas, parent, false);
        ImagemAdapter.ViewHolder viewHolder = new ImagemAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImagemAdapter.ViewHolder holder, int position) {
        holder.populate(imagemList.get(position));
    }

    @Override
    public int getItemCount() {
        return imagemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtDescricao;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imagens_lojas);
            txtDescricao = (TextView) itemView.findViewById(R.id.descricao_imagem);
        }

        public void populate(ImagemLoja imagemLoja) {
            txtDescricao.setText(imagemLoja.getDescricao());

            Glide.with(view.getContext())
                    .load(imagemLoja.getImage64())
                    .placeholder(R.drawable.imageplaceholder)
                    .error(R.drawable.imagenotfound)
                    .into(imageView);
        }
    }
}
