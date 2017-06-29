package br.dpacanhella.miguelopolis.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
import br.dpacanhella.miguelopolis.util.task.AppAsyncTask;

/**
 * Created by infra on 29/06/17.
 */

public class UtilitariosAdapter extends RecyclerView.Adapter<UtilitariosAdapter.ViewHolder> {
    List<Utilitario> utilitarioList;
    View view;
    public ImageView image;
    private LayoutInflater mLayoutInflater;
    private float scale;
    private int width;
    private int height;

    public UtilitariosAdapter(List<Utilitario> doctorList) {
        this.utilitarioList = doctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_utilitarios_card, parent, false);
        UtilitariosAdapter.ViewHolder viewHolder = new UtilitariosAdapter.ViewHolder(view);

        scale = view.getResources().getDisplayMetrics().density;
        width = view.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(utilitarioList.get(position));

//        myViewHolder.tvModel.setText(mList.get(position).getModel());
//        myViewHolder.tvBrand.setText(mList.get(position).getBrand());
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            myViewHolder.ivCar.setImageResource(mList.get(position).getPhoto());
//        }
//        else{
//            Bitmap bitmap = BitmapFactory.decodeResource( mContext.getResources(), mList.get(position).getPhoto());
//            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
//
//            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 4, width, height, false, false, true, true);
//            myViewHolder.ivCar.setImageBitmap(bitmap);
//        }
//
//        try{
//            YoYo.with(Techniques.Tada)
//                    .duration(700)
//                    .playOn(myViewHolder.itemView);
//        }
//        catch(Exception e){}
    }

    @Override
    public int getItemCount() {
        return utilitarioList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
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

        }public void populate(Utilitario utilitario) {
            txtName.setText(utilitario.getNome());
            txtDescricao.setText(utilitario.getDescricao());

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
}
