package es.dylanhurtado.appvacaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCasaActivity extends AppCompatActivity {
    ImageView imageViewCasaD;
    TextView textViewNombreCasaD, textViewPaisCasaD, textViewPrecioD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_casa);
        imageViewCasaD = findViewById(R.id.imageViewCasaD);
        textViewNombreCasaD = findViewById(R.id.textViewNombreCasaD);
        textViewPaisCasaD = findViewById(R.id.textViewPaisCasaD);
        textViewPrecioD = findViewById(R.id.textViewPrecioD);

        int imagen = 0;
        String nombre = "";
        String pais = "";
        double precio = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            imagen = extras.getInt("imagen");
            nombre = extras.getString("nombre");
            pais = extras.getString("pais");
            precio = extras.getDouble("precio");
        }
        imageViewCasaD.setImageResource(imagen);
        textViewNombreCasaD.setText(nombre);
        textViewPaisCasaD.setText("Pais: " + pais);
        textViewPrecioD.setText("Precio: " + precio + " €");
    }
}