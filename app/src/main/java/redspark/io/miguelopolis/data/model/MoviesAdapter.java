package redspark.io.miguelopolis.data.model;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import redspark.io.miguelopolis.R;

/**
 * Created by infra on 11/05/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>{

    private List<Farmacia> farmaciasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView razao, nomeProprietario;

        public MyViewHolder(View view) {
            super(view);
            razao = (TextView) view.findViewById(R.id.razao);
            nomeProprietario = (TextView) view.findViewById(R.id.nomeProprietario);
        }
    }

    public MoviesAdapter(List<Farmacia> farmaciasList){
        this.farmaciasList = farmaciasList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmacias, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MyViewHolder holder, int position) {
        Farmacia farmacia = farmaciasList.get(position);
        holder.nomeProprietario.setText(farmacia.getNomeProprietario());
        holder.razao.setText(farmacia.getRazao());

    }

    @Override
    public int getItemCount() {
        return farmaciasList.size();
    }
}
