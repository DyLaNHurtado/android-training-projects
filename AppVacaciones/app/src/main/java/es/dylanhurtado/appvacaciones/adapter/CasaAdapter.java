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
import es.dylanhurtado.appvacaciones.model.Casa;

public class CasaAdapter extends RecyclerView.Adapter<CasaAdapter.ViewHolder>{

    List<Casa> casaList;
    Context context;
    OnClickListener onClickListener;

    public CasaAdapter(List<Casa> casaList, Context context, OnClickListener onClickListener) {
        this.casaList = casaList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CasaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CasaAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_casa_lista,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull CasaAdapter.ViewHolder holder, int position) {
        Casa item = casaList.get(position);

        holder.imageViewCasa.setImageResource(item.getImagen());
        holder.textViewNombreCasa.setText(item.getNombre());
        holder.textViewPaisCasa.setText(item.getPais());
        holder.textViewPrecioCasa.setText("Precio: " + item.getPrecio() + " €");

    }

    @Override
    public int getItemCount() {
        return casaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewCasa;
        TextView textViewNombreCasa, textViewPaisCasa, textViewPrecioCasa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewCasa = itemView.findViewById(R.id.imageViewCasa);
            textViewNombreCasa = itemView.findViewById(R.id.textViewNombreCasa);
            textViewPaisCasa = itemView.findViewById(R.id.textViewPaisCasa);
            textViewPrecioCasa = itemView.findViewById(R.id.textViewPrecioCasa);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onClickListener.onItemClick(getAdapterPosition());
        }
    }
}
