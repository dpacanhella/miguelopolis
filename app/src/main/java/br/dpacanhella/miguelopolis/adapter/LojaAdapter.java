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

import java.util.List;

import br.dpacanhella.miguelopolis.LojaDetalhesActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Loja;
import br.dpacanhella.miguelopolis.data.model.LojaDetalhes;
import retrofit2.Call;
import retrofit2.Response;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.populate(lojaList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    loja = lojaList.get(position);

                    farmaciaBO = new FarmaciaBO();

                    retrofit2.Callback callback = new retrofit2.Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.i("info", "sucesso");
                            LojaDetalhes loj = (LojaDetalhes) response.body();

                            montaResumoAnalytics(loj, v);

                            dialog.dismiss();

                            showDetalhes(holder.itemView.getContext(), loj);

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

                    int id = loja.getId();
                    LojaDetalhes lojaDetalhes = farmaciaBO.getByIdLojas(id, callback);

                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDetalhes(Context c, LojaDetalhes loj) {
        Intent intent = new Intent(c, LojaDetalhesActivity.class);

//        intent.putExtra("id", loj.getId());
//        intent.putExtra("nome", loj.getNome());
//        intent.putExtra("descricao", loj.getDescricao());
//        intent.putExtra("endereco", loj.getEndereco());
//        intent.putExtra("imagemEstabelecimento", loj.getImagemEstabelecimento());
//        intent.putExtra("telefone", loj.getTelefone());
//        intent.putExtra("whatsApp", loj.getCelular());
//
//        List<ImagemLoja> imagens = loj.getImagensLojas();
//
//        ArrayList list = new ArrayList();
//
//        for (ImagemLoja imagem : imagens) {
//            list.add(new ImagemLoja(imagem.getId(), imagem.getDescricao(), imagem.getImagem(), imagem.getImageByte()));
//        }
//
//        intent.putParcelableArrayListExtra("imagens", list);

        c.startActivity(intent);

    }

    private void montaResumoAnalytics(LojaDetalhes loj, View v) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(v.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(loj.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, loj.getNome());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        String nomeFormatado = loj.getNome().replaceAll(" ", "_");
        mFirebaseAnalytics.logEvent(nomeFormatado, bundle);
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
        Glide.with(view.getContext())
                .load(s)
                .placeholder(R.drawable.imageplaceholder)
                .error(R.drawable.imagenotfound)
                .into(imageLogo);
    }
}
