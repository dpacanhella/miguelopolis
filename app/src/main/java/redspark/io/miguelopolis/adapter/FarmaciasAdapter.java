package redspark.io.miguelopolis.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import lombok.core.Main;
import redspark.io.miguelopolis.DetalhesActivity;
import redspark.io.miguelopolis.MainActivity;
import redspark.io.miguelopolis.R;
import redspark.io.miguelopolis.data.business.BusinessException;
import redspark.io.miguelopolis.data.business.farmacia.FarmaciaBO;
import redspark.io.miguelopolis.data.dao.Farmacia.FarmaciaDao;
import redspark.io.miguelopolis.data.model.Farmacia;
import redspark.io.miguelopolis.data.model.FarmaciaDetalhes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ricardocardoso on 11/05/17.
 */

public class FarmaciasAdapter extends RecyclerView.Adapter<FarmaciasAdapter.ViewHolder> {

    List<Farmacia> farmaciaList;

    private FarmaciaBO farmaciaBO;

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


                    farmaciaBO = new FarmaciaBO();

                    Callback callback = new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.i("info", "sucesso");
                            FarmaciaDetalhes farm = (FarmaciaDetalhes) response.body();

                            showDetalhes(holder.itemView.getContext(), farm);

//                            AQUI QUERO JOGAR PARA UM OUTRO LAYOUT


                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("info", "erro");
                        }
                    };


                    FarmaciaDetalhes farmacia = farmaciaBO.getById(position + 1, callback);
                    //aqui pega a posicao + 1 mas não acha meu serviço

                } catch (BusinessException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showDetalhes(Context c, FarmaciaDetalhes farm) {
        Intent intent = new Intent(c, DetalhesActivity.class);
        //serializar
//        intent.putExtra("farmacia", (Serializable) farm);
        c.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return farmaciaList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtNomeProprietario;
        private Boolean plantao;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.item_farmacia_text_name);
            txtNomeProprietario = (TextView) itemView.findViewById(R.id.item_farmacia_text_nomeProprietario);
            image = (ImageView) itemView.findViewById(R.id.item_image_view);
        }

        public void populate(Farmacia farmacia) {

            txtName.setText(farmacia.getRazao());
            txtNomeProprietario.setText(farmacia.getNomeProprietario());

            if(farmacia.getPlantao()){
                image.setImageResource(R.mipmap.certo);
            }else{
                image.setImageResource(R.mipmap.errado);
            }
        }
    }

}
