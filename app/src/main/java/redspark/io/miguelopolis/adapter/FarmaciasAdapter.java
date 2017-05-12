package redspark.io.miguelopolis.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import redspark.io.miguelopolis.R;
import redspark.io.miguelopolis.data.model.Farmacia;

/**
 * Created by ricardocardoso on 11/05/17.
 */

public class FarmaciasAdapter extends RecyclerView.Adapter<FarmaciasAdapter.ViewHolder> {

    List<Farmacia> farmaciaList;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(farmaciaList.get(position));
    }

    @Override
    public int getItemCount() {
        return farmaciaList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.item_farmacia_text_name);

        }

        public void populate(Farmacia farmacia) {
            txtName.setText(farmacia.getRazao());
        }
    }

}
