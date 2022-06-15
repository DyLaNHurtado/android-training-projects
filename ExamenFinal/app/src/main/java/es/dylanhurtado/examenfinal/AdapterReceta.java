package es.dylanhurtado.examenfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.dylanhurtado.examenfinal.db.RoomDB;
import es.dylanhurtado.examenfinal.model.Category;
import es.dylanhurtado.examenfinal.model.Receta;

public class AdapterReceta extends RecyclerView.Adapter<AdapterReceta.ViewHolder>{

    private List<Receta> recetaList;
    private Activity context;
    private RoomDB database;

    public AdapterReceta(List<Receta> recetaList, Activity context) {
        this.recetaList = recetaList;
        this.context = context;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterReceta.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new AdapterReceta.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReceta.ViewHolder holder, int position) {
        Receta item = recetaList.get(position);

        holder.nameRec.setText(item.getName());
        holder.photoRec.setImageResource(item.getPhoto());

    }

    @Override
    public int getItemCount() {
        return recetaList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutReceta;
        TextView nameRec;
        ImageView photoRec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutReceta = itemView.findViewById(R.id.layout);
            nameRec = itemView.findViewById(R.id.nameReceta);
            photoRec = itemView.findViewById(R.id.imageReceta);
            onClickDetalle();
        }
        private void onClickDetalle(){
            layoutReceta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ElementoSelecionado.getInstance().setReceta(recetaList.get(getAdapterPosition()));

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new VerReceta()).addToBackStack(null).commit();
                }
            });
        }
    }


}
