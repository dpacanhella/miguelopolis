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

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.RestauranteDetalhesActivity;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Cardapio;
import br.dpacanhella.miguelopolis.data.model.Restaurante;
import br.dpacanhella.miguelopolis.data.model.RestauranteDetalhes;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by infra on 05/07/17.
 */

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.ViewHolder> {

    List<Restaurante> restauranteList;
    private Restaurante restaurante = null;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ImageView imageLogo;
    private FarmaciaBO farmaciaBO;
    View view;
    MaterialDialog dialog;

    public RestauranteAdapter(List<Restaurante> doctorList) {
        this.restauranteList = doctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurantes, parent, false);
        RestauranteAdapter.ViewHolder viewHolder = new RestauranteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.populate(restauranteList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    restaurante = restauranteList.get(position);


                    farmaciaBO = new FarmaciaBO();

                    retrofit2.Callback callback = new retrofit2.Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.i("info", "sucesso");
                            RestauranteDetalhes rest = (RestauranteDetalhes) response.body();

                            montaResumoAnalytics(rest, v);

                            dialog.dismiss();

                            showDetalhes(holder.itemView.getContext(), rest);

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("info", "erro ao consultar detalhes do restaurante");
                        }
                    };

                    dialog = new MaterialDialog.Builder(holder.itemView.getContext())
                            .title("Aguarde")
                            .content("Carregando...")
                            .progress(true, 0)
                            .cancelable(false)
                            .show();

                    int id = restaurante.getId();
                    RestauranteDetalhes restaurante = farmaciaBO.getByIdRestaurante(id, callback);

                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void showDetalhes(Context c, RestauranteDetalhes rest) {
        Intent intent = new Intent(c, RestauranteDetalhesActivity.class);

        intent.putExtra("id", rest.getId());
        intent.putExtra("nome", rest.getNome());
        intent.putExtra("nomeProprietario", rest.getNomeProprietario());
        intent.putExtra("descricao", rest.getDescricao());
        intent.putExtra("descricao2", rest.getDescricao2());
        intent.putExtra("descricao3", rest.getDescricao3());
        intent.putExtra("descricao4", rest.getDescricao4());
        intent.putExtra("descricao5", rest.getDescricao5());
        intent.putExtra("endereco", rest.getEndereco());
        intent.putExtra("imagemLogo", rest.getImagemLogo());
        intent.putExtra("imagem1", rest.getImagem1());
        intent.putExtra("imagem2", rest.getImagem2());
        intent.putExtra("imagemEstabelecimento", rest.getImagemEstabelecimento());
        intent.putExtra("telefone", rest.getTelefone());
        intent.putExtra("whatsApp", rest.getWhatsapp());

        List<Cardapio> cardapios = rest.getCardapios();

        ArrayList list = new ArrayList();

        for (Cardapio cardapio : cardapios) {
            list.add(new Cardapio(cardapio.getId(), cardapio.getDescricao(), cardapio.getImagem()));
        }

        intent.putParcelableArrayListExtra("cardapios", list);

        c.startActivity(intent);
    }

    private void montaResumoAnalytics(RestauranteDetalhes restaurante, View v) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(v.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(restaurante.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, restaurante.getNome());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");

        mFirebaseAnalytics.logEvent(restaurante.getNome(), bundle);
    }

    @Override
    public int getItemCount() {
        return restauranteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNome;
        private TextView txtTelefone;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.restaurante_titulo);
            txtTelefone = (TextView) itemView.findViewById(R.id.restaurante_telefone);
            imageLogo = (ImageView) itemView.findViewById(R.id.restaurante_img_logo);

        }

        public void populate(Restaurante restaurante) {
            txtNome.setText(restaurante.getNome());
            String lblTelefone = "(016) ";
            txtTelefone.setText(lblTelefone + restaurante.getTelefone());

            loadImageFromURL(restaurante.getImagemLogo().toString());
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
