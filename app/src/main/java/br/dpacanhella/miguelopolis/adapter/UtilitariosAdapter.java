package br.dpacanhella.miguelopolis.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Utilitario;

/**
 * Created by infra on 29/06/17.
 */

public class UtilitariosAdapter extends RecyclerView.Adapter<UtilitariosAdapter.ViewHolder> {
    List<Utilitario> utilitarioList;
    View view;
    public ImageView image;
    private FirebaseAnalytics mFirebaseAnalytics;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.populate(utilitarioList.get(position));
    }

    @Override
    public int getItemCount() {
        return utilitarioList.size();
    }



    protected class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtDescricao;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.nome_utilitarios);
            txtDescricao = (TextView) itemView.findViewById(R.id.descricao_utilitarios);
            image = (ImageView) itemView.findViewById(R.id.image_utilitarios);

        }

        public void populate(final Utilitario utilitario) {
            txtName.setText(utilitario.getNome());
            txtDescricao.setText(utilitario.getDescricao());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    montaResumoAnalytics(utilitario, v);

                    MaterialDialog dialog = new MaterialDialog.Builder(v.getContext())
                            .title(utilitario.getNome())
                            .customView(R.layout.custom_view, true)
                            .positiveText("Fechar")
                            .positiveColorRes(R.color.materialColor)
                            .build();

                    TextView txtDescricao = (TextView) dialog.findViewById(R.id.model_descricao);
                    txtDescricao.setText(utilitario.getDescricao());

                    TextView txtEndereco = (TextView) dialog.findViewById(R.id.model_endereco);
                    txtEndereco.setText(utilitario.getEndereco());

                    TextView txtTelefone = (TextView) dialog.findViewById(R.id.model_telefone);
                    String lblTelefone = "(016) ";
                    txtTelefone.setText(lblTelefone + utilitario.getTelefone());

                    TextView txtWhatsApp = (TextView) dialog.findViewById(R.id.model_whats);
                    txtWhatsApp.setText(lblTelefone + utilitario.getCelular());

                    dialog.show();
                }
            });

            loadImageFromURL(utilitario.getImagem().toString());
        }
    }

    private void montaResumoAnalytics(Utilitario utilitario, View v) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(v.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(utilitario.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, utilitario.getNome());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        String nomeFormatado = utilitario.getNome().replaceAll(" ", "_");
        mFirebaseAnalytics.logEvent(nomeFormatado, bundle);
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

    public void onClick(View v, int position) {
        Toast.makeText(v.getContext(), "onClickListener(): "+ position, Toast.LENGTH_SHORT).show();
    }

}
