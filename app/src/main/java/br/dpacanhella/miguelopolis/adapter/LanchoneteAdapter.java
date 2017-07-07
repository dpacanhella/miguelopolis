package br.dpacanhella.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Lanchonete;
import br.dpacanhella.miguelopolis.data.model.Restaurante;

/**
 * Created by infra on 07/07/17.
 */

public class LanchoneteAdapter extends RecyclerView.Adapter<LanchoneteAdapter.ViewHolder>{
    List<Lanchonete> lanchoneteList;
    private Lanchonete lanchonete = null;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ImageView imageLogo;
    private FarmaciaBO farmaciaBO;
    View view;

    public LanchoneteAdapter(List<Lanchonete> doctorList) {
        this.lanchoneteList = doctorList;
    }

    @Override
    public LanchoneteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lanchonetes, parent, false);
        LanchoneteAdapter.ViewHolder viewHolder = new LanchoneteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LanchoneteAdapter.ViewHolder holder, int position) {
        holder.populate(lanchoneteList.get(position));
    }

    @Override
    public int getItemCount() {
        return lanchoneteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNome;
        private TextView txtTelefone;


        public ViewHolder(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.lanchonete_titulo);
            txtTelefone = (TextView) itemView.findViewById(R.id.lanchonete_telefone);
            imageLogo = (ImageView) itemView.findViewById(R.id.lanchonete_img_logo);

        }

        public void populate(Lanchonete lanchonete) {
            txtNome.setText(lanchonete.getNome());
            String lblTelefone = "(016) ";
            txtTelefone.setText(lblTelefone + lanchonete.getTelefone());

            loadImageFromURL(lanchonete.getImagemLogo().toString());
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
