package es.dylanhurtado.approomdatabase;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.dylanhurtado.approomdatabase.db.RoomDB;
import es.dylanhurtado.approomdatabase.db.TareaEntity;

import java.util.List;

public class TareaRecyclerViewAdapter extends RecyclerView.Adapter<TareaRecyclerViewAdapter.ViewHolder> {

    private List<TareaEntity> tareaEntityList;
    private Activity context;
    private RoomDB database;

    public TareaRecyclerViewAdapter(List<TareaEntity> tareaEntityList, Activity context) {
        this.tareaEntityList = tareaEntityList;
        this.context = context;

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tarea_list, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TareaEntity item = tareaEntityList.get(position);

        holder.tituloTLista.setText(item.getTitulo());
        holder.fechaTLista.setText(item.getFecha());
        holder.descripcionTLista.setText(item.getDescripcion());

        // Recoge la opcion spinner del fragmento y la comprueba, segun el dato seleccionado, se pondra una imagen
        String opcionSpinner = item.getPrioridad();

        switch (opcionSpinner) {
            case "Prioridad Baja":
                holder.imagePrioridadLista.setImageResource(R.drawable.icon_circle_green);
                break;
            case "Prioridad Media":
                holder.imagePrioridadLista.setImageResource(R.drawable.icon_circle_yellow);
                break;
            case "Prioridad Alta":
                holder.imagePrioridadLista.setImageResource(R.drawable.icon_circle_red);

        }

        holder.imagePrioridadLista.setOnClickListener(view -> {
            // seleccionamos el id del item que queramos eliminar al pulsar sobre su imagen
            int sId = item.getId();
            // conseguimos la instancia de la base de datos
            database = RoomDB.getInstance(context);
            // ejecutamos el dao, en este caso, escogi eliminarlo en base al id
            database.dataDao().deleteTarea(sId);
            // limpiamos la lista, y volvemos a añadir los elementos restantes de la BBDD
            tareaEntityList.clear();
            tareaEntityList.addAll(database.dataDao().selectTareas());

            Toast.makeText(context, "Tarea Eliminada", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return tareaEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTLista, fechaTLista, descripcionTLista;
        ImageView imagePrioridadLista;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTLista = itemView.findViewById(R.id.tituloTLista);
            fechaTLista = itemView.findViewById(R.id.fechaTLista);
            descripcionTLista = itemView.findViewById(R.id.descripcionTLista);
            imagePrioridadLista = itemView.findViewById(R.id.imagePrioridadLista);

        }
    }
}