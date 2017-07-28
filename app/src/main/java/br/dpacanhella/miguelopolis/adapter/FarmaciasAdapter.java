package br.dpacanhella.miguelopolis.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import br.dpacanhella.miguelopolis.DetalhesActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ricardocardoso on 11/05/17.
 */

public class FarmaciasAdapter extends RecyclerView.Adapter<FarmaciasAdapter.ViewHolder> {

    List<Farmacia> farmaciaList;
    private Farmacia farmacia = null;
    private FirebaseAnalytics mFirebaseAnalytics;

    private FarmaciaBO farmaciaBO;
    MaterialDialog dialog;

    public FarmaciasAdapter(List<Farmacia> doctorList) {
        this.farmaciaList = doctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_farmacias, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.populate(farmaciaList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    farmacia = farmaciaList.get(position);
                    farmaciaBO = new FarmaciaBO();

                    Callback callback = new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.i("info", "sucesso");
                            FarmaciaDetalhes farm = (FarmaciaDetalhes) response.body();

                            dialog.dismiss();

                            showDetalhes(holder.itemView.getContext(), farm);

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

                    int id = farmacia.getId();farmaciaBO.getById(id, callback);

                } catch (BusinessException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showDetalhes(Context c, FarmaciaDetalhes farm) {
        Intent intent = new Intent(c, DetalhesActivity.class);

        intent.putExtra("minhaclasse", farm);

        c.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return farmaciaList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtNomeProprietario;
        private TextView txtTelefone;
        private ImageView image;
        private TextView txtHorario;
        private TextView whatsApp;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.item_farmacia_text_name);
            txtNomeProprietario = (TextView) itemView.findViewById(R.id.item_farmacia_text_nomeProprietario);
            image = (ImageView) itemView.findViewById(R.id.item_image_view);
            txtTelefone = (TextView) itemView.findViewById(R.id.item_farmacia_text_telefone);
            txtHorario = (TextView) itemView.findViewById(R.id.item_farmacia_text_horario);
            whatsApp = (TextView) itemView.findViewById(R.id.item_farmacia_text_whats);
        }

        public void populate(Farmacia farmacia) {

            txtName.setText(farmacia.getRazao());
            txtNomeProprietario.setText(farmacia.getNomeProprietario());
            String lblTelefone = "Telefone: (16) ";
            txtTelefone.setText(lblTelefone + farmacia.getTelefone());

            String lblWhats = "WhatsApp: (16) ";
            whatsApp.setText(lblWhats + farmacia.getWhatsApp());

            if(farmacia.getPlantao()){
                itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                txtName.setTextColor(Color.parseColor("#ff0000"));
                txtHorario.setText("Aberto das 08h00 às 22h00");
            }else{
                itemView.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
        }
    }
}
