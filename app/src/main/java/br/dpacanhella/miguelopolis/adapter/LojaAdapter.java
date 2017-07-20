package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Loja;

/**
 * Created by infra on 20/07/17.
 */

public class LojaAdapter extends RecyclerView.Adapter<LojaAdapter.ViewHolder>{

    List<Loja> lojaList;
    private Loja loja = null;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FarmaciaBO farmaciaBO;
    MaterialDialog dialog;
    private ImageView imageLogo;
    View view;

    public LojaAdapter(List<Loja> doctorList) {
        this.lojaList = doctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lojas_card, parent, false);
        LojaAdapter.ViewHolder viewHolder = new LojaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LojaAdapter.ViewHolder holder, int position) {
        holder.populate(lojaList.get(position));
    }

    @Override
    public int getItemCount() {
        return lojaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNome;
        private TextView txtDescricao;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.nome_lojas);
            txtDescricao = (TextView) itemView.findViewById(R.id.descricao_lojas);
            imageLogo = (ImageView) itemView.findViewById(R.id.image_lojas);

        }

        public void populate(Loja loja) {
            txtNome.setText(loja.getNome());
            txtDescricao.setText(loja.getDescricao());

            loadImageFromURL(loja.getImagemLogo().toString());
        }
    }

    private void loadImageFromURL(String s) {
        Picasso.with(view.getContext()).load(s)
                .error(R.mipmap.ic_launcher)
                .into(imageLogo, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
