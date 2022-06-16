package es.dylanhurtado.examenandroid.fragments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import es.dylanhurtado.examenandroid.R;
import es.dylanhurtado.examenandroid.adapters.AdapterDato;
import es.dylanhurtado.examenandroid.database.RoomDB;
import es.dylanhurtado.examenandroid.model.Dato;

import java.util.ArrayList;
import java.util.List;


public class DiasListFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;
    RoomDB database;
    List<Dato> aulaList = new ArrayList<>();
    AdapterDato adapterView;
    RecyclerView rv;
    LinearLayoutManager llm;

    public DiasListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RoomDB.getInstance(getContext());
        aulaList = database.dataDao().getDatoByDia("Lunes");
    }

    private void menuListeners(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menuLunes:
                    aulaList.clear();
                    aulaList= database.dataDao().getDatoByDia("Lunes");
                    adapterView = new AdapterDato(aulaList, getActivity());
                    rv.setAdapter(adapterView);
                    Toast.makeText(getContext(), "Lunes Seleccionado!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menuMartes:
                    aulaList.clear();
                    aulaList= database.dataDao().getDatoByDia("Martes");
                    adapterView = new AdapterDato(aulaList, getActivity());
                    rv.setAdapter(adapterView);
                    Toast.makeText(getContext(), "Martes Seleccionado!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menuMiercoles:
                    aulaList.clear();
                    aulaList= database.dataDao().getDatoByDia("Miercoles");
                    adapterView = new AdapterDato(aulaList, getActivity());
                    rv.setAdapter(adapterView);
                    Toast.makeText(getContext(), "Miercoles Seleccionado!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menuJueves:
                    aulaList.clear();
                    aulaList= database.dataDao().getDatoByDia("Jueves");
                    adapterView = new AdapterDato(aulaList, getActivity());
                    rv.setAdapter(adapterView);
                    Toast.makeText(getContext(), "Jueves Seleccionado!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menuViernes:
                    aulaList.clear();
                    aulaList= database.dataDao().getDatoByDia("Viernes");
                    adapterView = new AdapterDato(aulaList, getActivity());
                    rv.setAdapter(adapterView);
                    Toast.makeText(getContext(), "Viernes Seleccionado!", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dias_list, container, false);
        bottomNavigationView = view.findViewById(R.id.bnv);
        bottomNavigationView.setSelectedItemId(R.id.menuLunes );
        menuListeners();
        Button backButton = view.findViewById(R.id.backButtonDias);
        rv = view.findViewById(R.id.rvDias);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapterView = new AdapterDato(aulaList, getActivity());
        rv.setAdapter(adapterView);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }
}