package es.dylanhurtado.appvacaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import es.dylanhurtado.appvacaciones.adapter.CasaAdapter;
import es.dylanhurtado.appvacaciones.adapter.UbicacionAdapter;
import es.dylanhurtado.appvacaciones.model.Casa;
import es.dylanhurtado.appvacaciones.model.Ubicacion;

public class MainActivity extends AppCompatActivity {

    List<Ubicacion> ubicacionList = new ArrayList<>();
    List<Casa> casaList = new ArrayList<>();
    RecyclerView recyclerViewCasa, recyclerViewUbi;
    UbicacionAdapter recyclerUbicacionAdapter;
    CasaAdapter recyclerCasaAdapter;
    LinearLayoutManager layoutCasaManager, layoutUbiManager;
    OnClickListener clickListener, clickListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verCasaDetalle();
        verUbicacionDetalle();
        rellenarListas();
        cargarListas();
    }

    private void rellenarListas(){
        Ubicacion ubi1 = new Ubicacion(R.drawable.estados_unidos, "South Park", "Estados Unidos", 69);
        Ubicacion ubi2 = new Ubicacion(R.drawable.dinamarca, "Dynamo Park", "Dinamarca", 420);
        Ubicacion ubi3 = new Ubicacion(R.drawable.polonia, "Paul Park", "Polonia", 233);
        Ubicacion ubi5 = new Ubicacion(R.drawable.suiza, "Frank Park", "Suiza", 500);
        //Ubicacion ubi = new Ubicacion();

        ubicacionList.add(ubi1);
        ubicacionList.add(ubi2);
        ubicacionList.add(ubi3);
        ubicacionList.add(ubi5);

        Casa casa1 = new Casa(R.drawable.casa_dinamarca, "Casa Dinamarca", "Dinamarca", 20.099);
        Casa casa2 = new Casa(R.drawable.casa_suiza1, "Casa Suiza 1", "Suiza", 25.099);
        Casa casa3 = new Casa(R.drawable.casa_suiza2, "Casa Suiza 2", "Suiza", 23.099);
        Casa casa4 = new Casa(R.drawable.casa_polonia, "Casa Polonia", "Polonia", 30.099);
        Casa casa5 = new Casa(R.drawable.casa_japon, "Casa Japón", "Japón", 27.099);
        Casa casa6 = new Casa(R.drawable.casa_estadosunidos, "Casa EEUU", "EEUU", 25.599);
;

        casaList.add(casa1);
        casaList.add(casa2);
        casaList.add(casa3);
        casaList.add(casa4);
        casaList.add(casa5);
        casaList.add(casa6);
    }

    private void cargarListas() {

        recyclerViewCasa = findViewById(R.id.recyclerCasa);
        layoutCasaManager = new LinearLayoutManager(recyclerViewCasa.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCasa.setLayoutManager(layoutCasaManager);
        recyclerCasaAdapter = new CasaAdapter(casaList, getApplicationContext(), clickListener);
        recyclerViewCasa.setAdapter(recyclerCasaAdapter);

        recyclerViewUbi = findViewById(R.id.recyclerUbi);
        layoutUbiManager = new LinearLayoutManager(recyclerViewUbi.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewUbi.setLayoutManager(layoutUbiManager);
        recyclerUbicacionAdapter = new UbicacionAdapter(ubicacionList, getApplicationContext(), clickListener2);
        recyclerViewUbi.setAdapter(recyclerUbicacionAdapter);

    }

    private void verCasaDetalle(){
        clickListener = posicion -> {
            Intent intent = new Intent(getApplicationContext(), DetalleCasaActivity.class);
            intent.putExtra("imagen", casaList.get(posicion).getImagen());
            intent.putExtra("nombre", casaList.get(posicion).getNombre());
            intent.putExtra("pais", casaList.get(posicion).getPais());
            intent.putExtra("precio", casaList.get(posicion).getPrecio());
            startActivity(intent);
        };
    }
    private void verUbicacionDetalle() {
        clickListener2 = posicion -> {
            Intent intent = new Intent(getApplicationContext(), DetalleUbicacionActivity.class);
            intent.putExtra("imagen", ubicacionList.get(posicion).getImagen());
            intent.putExtra("nombre", ubicacionList.get(posicion).getNombre());
            intent.putExtra("pais", ubicacionList.get(posicion).getPais());
            intent.putExtra("id", ubicacionList.get(posicion).getCodigoId());
            startActivity(intent);
        };
    }

}