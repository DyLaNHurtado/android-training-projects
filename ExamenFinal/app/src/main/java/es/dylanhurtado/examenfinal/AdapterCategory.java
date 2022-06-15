package es.dylanhurtado.examenfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.dylanhurtado.examenfinal.db.RoomDB;
import es.dylanhurtado.examenfinal.model.Category;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder>{

    private List<Category> categoryList;
    private Activity context;
    private RoomDB database;

    public AdapterCategory(List<Category> categoryList, Activity context) {
        this.categoryList = categoryList;
        this.context = context;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, int position) {
        Category item = categoryList.get(position);

        holder.nameCat.setText(item.getName());
        holder.photoCat.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameCat;
        ImageView photoCat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameCat = itemView.findViewById(R.id.cat_name);
            photoCat = itemView.findViewById(R.id.cat_photo);

        }
    }
}
