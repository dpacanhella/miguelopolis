package br.dpacanhella.miguelopolis.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
import br.dpacanhella.miguelopolis.interfaces.RecyclerViewOnClickListenerHack;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;

/**
 * Created by infra on 29/06/17.
 */

public class UtilitariosAdapter extends RecyclerView.Adapter<UtilitariosAdapter.ViewHolder> {
    List<Utilitario> utilitarioList;
    View view;
    public ImageView image;

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

        try{
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .playOn(holder.itemView);
        }
        catch(Exception e){}

    }

    @Override
    public int getItemCount() {
        return utilitarioList.size();
    }



    protected class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtDescricao;
        private TextView txtEndereco;
        private TextView txtTelefone;
        private TextView txtCelular;


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
                    boolean wrapInScrollView = true;

                    MaterialDialog dialog = new MaterialDialog.Builder(v.getContext())
                            .title(utilitario.getNome())
                            .customView(R.layout.custom_view, true)
                            .positiveText("Fechar")
                            .positiveColorRes(R.color.materialColor)
                            .build();

                    //SETANDO VALORES
//                    TextView model_descricao = (TextView) dialog.getCustomView();
//                    model_descricao.setText(utilitario.getDescricao());

                    dialog.show();
                }
            });

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

    public void onClick(View v, int position) {
        Toast.makeText(v.getContext(), "onClickListener(): "+ position, Toast.LENGTH_SHORT).show();
    }

}
