package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.Utilitario;

/**
 * Created by infra on 29/06/17.
 */

public class UtilitariosAdapter extends RecyclerView.Adapter<UtilitariosAdapter.ViewHolder> {
    List<Utilitario> utilitarioList;
    View view;
    private ImageView image;

    public UtilitariosAdapter(List<Utilitario> doctorList) {
        this.utilitarioList = doctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_utilitarios_card, parent, false);
        UtilitariosAdapter.ViewHolder viewHolder = new UtilitariosAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UtilitariosAdapter.ViewHolder holder, int position) {
        holder.populate(utilitarioList.get(position));
    }

    @Override
    public int getItemCount() {
        return utilitarioList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtDescricao;
        private TextView txtEndereco;
        private TextView txtTelefone;
        private TextView txtCelular;


        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.nome_utilitarios);
            txtDescricao = (TextView) itemView.findViewById(R.id.descricao_utilitarios);
            image = (ImageView) itemView.findViewById(R.id.image_utilitarios);

        }public void populate(Utilitario utilitario) {
            txtName.setText(utilitario.getNome());
            txtDescricao.setText(utilitario.getDescricao());

            loadImageFromURL(utilitario.getImagem().toString());
        }

    }

    private void loadImageFromURL(String s) {
        Picasso.with(view.getContext()).load(s)
                .error(R.mipmap.ic_launcher)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
