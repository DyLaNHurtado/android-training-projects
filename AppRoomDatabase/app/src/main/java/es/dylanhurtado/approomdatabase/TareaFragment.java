package es.dylanhurtado.approomdatabase;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.dylanhurtado.approomdatabase.db.RoomDB;
import es.dylanhurtado.approomdatabase.db.TareaEntity;


public class TareaFragment extends Fragment {

    ImageView imageAdd;

    RoomDB database;

    RecyclerView recycler;
    List<TareaEntity> tareaEntities = new ArrayList<>();
    TareaRecyclerViewAdapter adapterView;
    LinearLayoutManager llm;

    String opcionSpinner;

    public TareaFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RoomDB.getInstance(getContext());

        tareaEntities = database.dataDao().selectTareas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarea, container, false);
        imageAdd = view.findViewById(R.id.imageViewAdd);
        recycler = view.findViewById(R.id.recycler);

        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);
        adapterView = new TareaRecyclerViewAdapter(tareaEntities, getActivity());
        recycler.setAdapter(adapterView);

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // -- CARGAMOS DIALOGO --
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialogo_add);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setAttributes(lp);
                dialog.show();


                // -- VARIABLES DIALOGO --
                EditText editTextTitulo = dialog.findViewById(R.id.editTextTitulo);
                EditText editTextDescripcion = dialog.findViewById(R.id.editTextDescripcion);

                // spinner
                Spinner spinnerPrioridad = dialog.findViewById(R.id.spinnerPrioridad);
                String prioridades[] = new String[]{"Prioridad Baja", "Prioridad Media", "Prioridad Alta"};
                ArrayAdapter<String> adapterArray = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, prioridades);
                spinnerPrioridad.setAdapter(adapterArray);

                spinnerPrioridad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        opcionSpinner = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                // datePicker
                DatePicker datePicker = dialog.findViewById(R.id.datePicker);

                // botones + listeners
                Button btnCancelDialogo = dialog.findViewById(R.id.btnCancelDialogo);
                Button btnAddDialogo = dialog.findViewById(R.id.btnAddDialogo);

                btnCancelDialogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnAddDialogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TareaEntity tarea = new TareaEntity();

                        tarea.setTitulo(editTextTitulo.getText().toString());
                        tarea.setDescripcion(editTextDescripcion.getText().toString());
                        tarea.setFecha(datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear());
                        tarea.setPrioridad(opcionSpinner);

                        dialog.dismiss();
                        long resultado = database.dataDao().insert(tarea);
                        Log.i("insert() = ", "" + resultado); // Comprobacion

                        tareaEntities = database.dataDao().selectTareas();
                        adapterView = new TareaRecyclerViewAdapter(tareaEntities, getActivity());
                        recycler.setAdapter(adapterView);

                        Toast.makeText(getContext(), "Tarea Agregada", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return view;
    }
}