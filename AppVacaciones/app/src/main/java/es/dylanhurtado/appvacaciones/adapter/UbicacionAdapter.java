package es.dylanhurtado.appvacaciones.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import es.dylanhurtado.appvacaciones.OnClickListener;
import es.dylanhurtado.appvacaciones.R;
import es.dylanhurtado.appvacaciones.model.Ubicacion;

public class UbicacionAdapter extends RecyclerView.Adapter<UbicacionAdapter.ViewHolder>{


    List<Ubicacion> ubicacionList;
    Context context;
    OnClickListener onClickListener;

    public UbicacionAdapter(List<Ubicacion> ubicacionList, Context context, OnClickListener onClickListener) {
        this.ubicacionList = ubicacionList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public UbicacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_ubicacion_lista,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UbicacionAdapter.ViewHolder holder, int position) {
        Ubicacion item = ubicacionList.get(position);

        holder.imageViewUbi.setImageResource(item.getImagen());
        holder.textViewNombreUbi.setText(item.getNombre());
        holder.textViewPaisUbi.setText(item.getPais());
        holder.textViewCodigoUbi.setText("ID: " + item.getCodigoId());

    }

    @Override
    public int getItemCount() {
        return ubicacionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageViewUbi;
        TextView textViewNombreUbi, textViewPaisUbi, textViewCodigoUbi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewUbi = itemView.findViewById(R.id.imageViewUbi);
            textViewNombreUbi = itemView.findViewById(R.id.textViewNombreUbi);
            textViewPaisUbi = itemView.findViewById(R.id.textViewPaisUbi);
            textViewCodigoUbi = itemView.findViewById(R.id.textViewCodigoUbi);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onItemClick(getAdapterPosition());
        }
    }
}
