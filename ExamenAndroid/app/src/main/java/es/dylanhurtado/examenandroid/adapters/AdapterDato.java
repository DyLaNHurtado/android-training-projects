package es.dylanhurtado.examenandroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import es.dylanhurtado.examenandroid.R;
import es.dylanhurtado.examenandroid.database.RoomDB;
import es.dylanhurtado.examenandroid.model.Dato;

import java.util.List;

public class AdapterDato  extends RecyclerView.Adapter<AdapterDato.ViewHolder> {

    private List<Dato> aulaList;
    private Activity context;
    private RoomDB database;

    public AdapterDato(List<Dato> aulaList, Activity context) {
        this.aulaList = aulaList;
        this.context = context;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterDato.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aula_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDato.ViewHolder holder, int position) {
        Dato item = aulaList.get(position);
        holder.horaTV.setText(item.getHora());
        holder.moduloTV.setText(item.getModulo());
        holder.aulaTV.setText(item.getAula());
    }

    @Override
    public int getItemCount() {
        return aulaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView horaTV, aulaTV, moduloTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            horaTV = itemView.findViewById(R.id.horaTV);
            aulaTV = itemView.findViewById(R.id.aulaTV);
            moduloTV = itemView.findViewById(R.id.moduloTV);

        }
    }
}
