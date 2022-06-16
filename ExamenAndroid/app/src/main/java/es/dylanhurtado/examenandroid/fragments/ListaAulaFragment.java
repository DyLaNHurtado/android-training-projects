package es.dylanhurtado.examenandroid.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import es.dylanhurtado.examenandroid.R;
import es.dylanhurtado.examenandroid.adapters.AdapterDato;
import es.dylanhurtado.examenandroid.database.RoomDB;
import es.dylanhurtado.examenandroid.model.Dato;

import java.util.ArrayList;
import java.util.List;


public class ListaAulaFragment extends Fragment {

    FloatingActionButton fab;
    RoomDB database;
    List<Dato> aulaList = new ArrayList<>();
    AdapterDato adapterView;
    RecyclerView rv;
    LinearLayoutManager llm;


    public ListaAulaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RoomDB.getInstance(getContext());

        aulaList = database.dataDao().selectAllDatos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_lista_aula, container, false);
        Button backButton = view.findViewById(R.id.backButtonListaAula);
        rv=view.findViewById(R.id.rv);

        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapterView = new AdapterDato(aulaList, getActivity());
        rv.setAdapter(adapterView);
        fab=view.findViewById(R.id.fabListaAula);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // -- CARGAMOS DIALOGO --
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.aula_dialog);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setAttributes(lp);
                dialog.show();


                // -- VARIABLES DIALOGO --
                EditText edDia = dialog.findViewById(R.id.edDia);
                EditText edHora = dialog.findViewById(R.id.edHora);
                EditText edModulo = dialog.findViewById(R.id.edModulo);
                EditText edAula = dialog.findViewById(R.id.edAula);
                Button addButton = dialog.findViewById(R.id.addButtonDialog);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dato item = new Dato();

                        item.setDia(edDia.getText().toString());
                        item.setHora(edHora.getText().toString());
                        item.setModulo(edModulo.getText().toString());
                        item.setAula(edAula.getText().toString());

                        dialog.dismiss();
                        long resultado = database.dataDao().insert(item);
                        Log.i("insert() = ", "" + resultado); // Comprobacion
                        aulaList = database.dataDao().selectAllDatos();
                        adapterView = new AdapterDato(aulaList, getActivity());
                        rv.setAdapter(adapterView);

                        Toast.makeText(getContext(), "Aula Agregada!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });


        return view;
    }
}