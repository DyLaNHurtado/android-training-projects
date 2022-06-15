package es.dylanhurtado.examenfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import es.dylanhurtado.examenfinal.model.Receta;

public class VerReceta extends Fragment {



    ImageView imageRecetaDetalle, imageDificultadDetalle;
    TextView tituloRecetaDetalle, tiempoRecetaDetalle, ingredientesRecetaDetalle, elaboracionRecetaDetalles;
    Button btnVolverReceta;

    Receta recetaElegida;

    int dificultadReceta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OBTENEMOS LA RECETA ELEGIDA DE LA CLASE "RecetaAdapter" Y GUARDADA EN "ElementoSeleccionado"
        // LA RECETA ELEGIDA VIENE DEL METODO "onClickDetalle".
        recetaElegida = ElementoSelecionado.getInstance().getReceta();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver_receta, container, false);

        // ELEMENTOS FRAGMENTO
        imageRecetaDetalle = v.findViewById(R.id.imageRecetaDetalle);
        imageDificultadDetalle = v.findViewById(R.id.imageDificultadDetalle);

        tituloRecetaDetalle = v.findViewById(R.id.tituloRecetaDetalle);
        tiempoRecetaDetalle = v.findViewById(R.id.tiempoRecetaDetalle);
        ingredientesRecetaDetalle = v.findViewById(R.id.ingredientesRecetaDetalle);
        elaboracionRecetaDetalles = v.findViewById(R.id.elaboracionRecetaDetalle);

        btnVolverReceta = v.findViewById(R.id.btnVolverReceta);

        // SET INFO DE LA RECETA
        imageRecetaDetalle.setImageResource(recetaElegida.getPhoto());

        dificultadReceta = Integer.parseInt(recetaElegida.getDificultad());
        switch (dificultadReceta) {
            case 1:
                imageDificultadDetalle.setImageResource(R.drawable.icon_easy);
                break;
            case 2:
                imageDificultadDetalle.setImageResource(R.drawable.icon_normal);
                break;
            case 3:
                imageDificultadDetalle.setImageResource(R.drawable.icon_hard);
        }

        tituloRecetaDetalle.setText(recetaElegida.getName());
        tiempoRecetaDetalle.setText(recetaElegida.getTime());
        ingredientesRecetaDetalle.setText(recetaElegida.getIngredientes());
        elaboracionRecetaDetalles.setText(recetaElegida.getElaboracion());

        // LISTENER PARA VOLVER AL FRAGMENTO ANTERIOR
        btnVolverReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        return v;
    }
}