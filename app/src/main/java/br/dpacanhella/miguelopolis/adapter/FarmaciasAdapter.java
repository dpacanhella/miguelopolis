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

import java.util.List;

import br.dpacanhella.miguelopolis.data.business.farmacia.FarmaciaBO;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.DetalhesActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ricardocardoso on 11/05/17.
 */

public class FarmaciasAdapter extends RecyclerView.Adapter<FarmaciasAdapter.ViewHolder> {

    List<Farmacia> farmaciaList;
    private FarmaciaListenner listener;
    private Farmacia farmacia = null;

    private FarmaciaBO farmaciaBO;

    public FarmaciasAdapter(List<Farmacia> doctorList, FarmaciaListenner listener) {
        this.farmaciaList = doctorList;
        this.listener = listener;
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

                            showDetalhes(holder.itemView.getContext(), farm);

//                            AQUI QUERO JOGAR PARA UM OUTRO LAYOUT


                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("info", "erro");
                        }
                    };

                    int id = farmacia.getId();
                    FarmaciaDetalhes farmacia = farmaciaBO.getById(id, callback);
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
        intent.putExtra("endereco", farm.getEndereco());
        intent.putExtra("nomeProprietario", farm.getNomeProprietario());
        intent.putExtra("razao", farm.getRazao());
        intent.putExtra("telefone", farm.getTelefone());
        intent.putExtra("imagem", farm.getImagem());

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
        private Boolean plantao;
        private ImageView image;
//        private TextView txtEndereco;


        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.item_farmacia_text_name);
            txtNomeProprietario = (TextView) itemView.findViewById(R.id.item_farmacia_text_nomeProprietario);
            image = (ImageView) itemView.findViewById(R.id.item_image_view);
            txtTelefone = (TextView) itemView.findViewById(R.id.item_farmacia_text_telefone);
//            txtEndereco = (TextView) itemView.findViewById(R.id.item_farmacia_text_endereco);
        }

        public void populate(Farmacia farmacia) {

            txtName.setText(farmacia.getRazao());
            txtNomeProprietario.setText(farmacia.getNomeProprietario());
            String lblTelefone = "Telefone: (16) ";
            txtTelefone.setText(lblTelefone + farmacia.getTelefone());
//            String lblEndereço = "Endereço: ";
//            txtEndereco.setText(farmacia.getEndereco());

            if(farmacia.getPlantao()){
//                image.setImageResource(R.mipmap.certo);
                //Retirar imagem e alterar cor de fundo, cor do texto, tamanho e borda
//                txtName.setTextSize(19);
                itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                txtName.setTextColor(Color.parseColor("#ff0000"));
            }else{
//                image.setImageResource(R.mipmap.errado);
                itemView.setBackgroundColor(Color.parseColor("#c4c4c4"));
            }
        }
    }

    public interface FarmaciaListenner {
        public void onItemListSelected(Farmacia farmacia);
    }
}