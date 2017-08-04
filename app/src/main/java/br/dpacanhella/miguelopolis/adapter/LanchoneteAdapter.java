package br.dpacanhella.miguelopolis.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import br.dpacanhella.miguelopolis.LanchoneteDetalhesActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Cardapio;
import br.dpacanhella.miguelopolis.data.model.Lanchonete;
import br.dpacanhella.miguelopolis.data.model.LanchoneteDetalhes;
import retrofit2.Call;
import retrofit2.Response;

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
    MaterialDialog dialog;

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
    public void onBindViewHolder(final LanchoneteAdapter.ViewHolder holder, final int position) {
        holder.populate(lanchoneteList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    lanchonete = lanchoneteList.get(position);


                    farmaciaBO = new FarmaciaBO();

                    retrofit2.Callback callback = new retrofit2.Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.i("info", "sucesso");
                            LanchoneteDetalhes lan = (LanchoneteDetalhes) response.body();

                            montaResumoAnalytics(lan, v);

                            dialog.dismiss();

                            showDetalhes(holder.itemView.getContext(), lan);

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("info", "erro ao consultar detalhes");
                        }
                    };

                    dialog = new MaterialDialog.Builder(holder.itemView.getContext())
                            .title("Aguarde")
                            .content("Carregando...")
                            .progress(true, 0)
                            .cancelable(false)
                            .show();

                    int id = lanchonete.getId();
                    LanchoneteDetalhes lanchonete = farmaciaBO.getByIdLanchonetes(id, callback);

                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDetalhes(Context c, LanchoneteDetalhes lan) {
        Intent intent = new Intent(c, LanchoneteDetalhesActivity.class);

        intent.putExtra("id", lan.getId());
        intent.putExtra("nome", lan.getNome());
        intent.putExtra("nomeProprietario", lan.getNomeProprietario());
        intent.putExtra("endereco", lan.getEndereco());
        intent.putExtra("imagemEstabelecimento", lan.getImagemEstabelecimento());
        intent.putExtra("telefone", lan.getTelefone());
        intent.putExtra("whatsApp", lan.getWhatsapp());

        List<Cardapio> cardapios = lan.getCardapios();

        ArrayList list = new ArrayList();

        for (Cardapio cardapio : cardapios) {
            list.add(new Cardapio(cardapio.getId(), cardapio.getDescricao(), cardapio.getImagem()));
        }

        intent.putParcelableArrayListExtra("cardapios", list);

        c.startActivity(intent);
    }

    private void montaResumoAnalytics(LanchoneteDetalhes lan, View v) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(v.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(lan.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, lan.getNome());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(lan.getNome(), bundle);
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
            txtTelefone.setText(lblTelefone + lanchonete.getWhatsapp());

            loadImageFromURL(lanchonete.getImagemLogo().toString());
        }
    }

    private void loadImageFromURL(String s) {
        Glide.with(view.getContext())
                .load(s)
                .placeholder(R.drawable.imageplaceholder)
                .error(R.drawable.imagenotfound)
                .into(imageLogo);
    }
}
