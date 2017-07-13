package br.dpacanhella.miguelopolis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import br.dpacanhella.miguelopolis.DetalhesActivity;
import br.dpacanhella.miguelopolis.ItemUtilitarioActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Promocao;
import br.dpacanhella.miguelopolis.data.model.TipoAnuncio;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by infra on 11/07/17.
 */

public class TipoAnuncioAdapter extends RecyclerView.Adapter<TipoAnuncioAdapter.ViewHolder>{

    List<TipoAnuncio> tipoAnuncioList;
    private TipoAnuncio tipoAnuncio = null;
    private FirebaseAnalytics mFirebaseAnalytics;

    private FarmaciaBO farmaciaBO;

    public TipoAnuncioAdapter(List<TipoAnuncio> doctorList) {
        this.tipoAnuncioList = doctorList;
    }

    @Override
    public TipoAnuncioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tipos_utilitarios, parent, false);
        TipoAnuncioAdapter.ViewHolder viewHolder = new TipoAnuncioAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TipoAnuncioAdapter.ViewHolder holder, final int position) {
        holder.populate(tipoAnuncioList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tipoAnuncio = tipoAnuncioList.get(position);


                    farmaciaBO = new FarmaciaBO();

                    Callback callback = new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.i("info", "sucesso");
                            List<Utilitario>  utilitarios = (List<Utilitario>) response.body();


                            showDetalhes(holder.itemView.getContext(), utilitarios);

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("info", "erro ao consultar utilitarios por tipo");
                        }
                    };

                    String anuncio = tipoAnuncio.getDescricao();
                    farmaciaBO.getAllUtilitarios(anuncio, callback);

                } catch (BusinessException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void showDetalhes(Context context, List<Utilitario> utilitarios) {
        Intent intent = new Intent(context, ItemUtilitarioActivity.class);
        ArrayList list = new ArrayList();

        for (Utilitario uti: utilitarios) {
            list.add(new Utilitario(uti.getId(), uti.getNome(), uti.getDescricao(), uti.getEndereco(), uti.getTelefone(), uti.getCelular(), uti.getImagem()));
        }

        intent.putParcelableArrayListExtra("utilitarios", list);

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return tipoAnuncioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtQtdeAnuncio;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.item_tipo_utilitario);
            txtQtdeAnuncio = (TextView) itemView.findViewById(R.id.qtde_item_utilitario);
        }

        public void populate(TipoAnuncio tipoAnuncio) {
            txtName.setText(tipoAnuncio.getDescricao());
            String qtdeAnuncio = Integer.toString(tipoAnuncio.getQtdeUtilitario());
            txtQtdeAnuncio.setText(qtdeAnuncio);
        }
    }
}
