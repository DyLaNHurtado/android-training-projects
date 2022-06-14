package es.dylanhurtado.appvacaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleUbicacionActivity extends AppCompatActivity {
    ImageView imageViewUbiD;
    TextView textViewNombreUbiD, textViewPaisUbiD, textViewCodigoD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ubicacion);

        imageViewUbiD = findViewById(R.id.imageViewUbiD);
        textViewNombreUbiD = findViewById(R.id.textViewNombreUbiD);
        textViewPaisUbiD = findViewById(R.id.textViewPaisUbiD);
        textViewCodigoD = findViewById(R.id.textViewCodigoD);

        int imagen = 0;
        String nombre = "";
        String pais = "";
        int codigo = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            imagen = extras.getInt("imagen");
            nombre = extras.getString("nombre");
            pais = extras.getString("pais");
            codigo = extras.getInt("id");
        }
        imageViewUbiD.setImageResource(imagen);
        textViewNombreUbiD.setText(nombre);
        textViewPaisUbiD.setText("Pais: " + pais);
        textViewCodigoD.setText("ID: " + codigo);
    }
}