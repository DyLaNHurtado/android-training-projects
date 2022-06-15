package es.dylanhurtado.examenfinal.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dylanhurtado.examenfinal.AdapterCategory;
import es.dylanhurtado.examenfinal.AdapterReceta;
import es.dylanhurtado.examenfinal.R;
import es.dylanhurtado.examenfinal.db.RoomDB;
import es.dylanhurtado.examenfinal.model.Category;
import es.dylanhurtado.examenfinal.model.Receta;


public class RecetaFragment extends Fragment {

    FloatingActionButton fab;
    RoomDB database;
    List<Receta> recetaList = new ArrayList<>();
    AdapterReceta adapterView;
    RecyclerView rv;
    LinearLayoutManager llm;
    String opcionSpinner;
    int dificultadReceta;


    public RecetaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = RoomDB.getInstance(getContext());

        recetaList = database.dataDao().selectAllRecetas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receta, container, false);
        fab = view.findViewById(R.id.fabRecetas);
        rv = view.findViewById(R.id.rvReceta);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapterView = new AdapterReceta(recetaList, getActivity());
        rv.setAdapter(adapterView);

        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DIALOG

                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_receta);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setAttributes(lp);
                dialog.show();

                //VARIABLES
                EditText edName = dialog.findViewById(R.id.editNameDialog);
                EditText edDuration = dialog.findViewById(R.id.editDuracion);
                EditText ingredients = dialog.findViewById(R.id.ingredientesDialog);
                EditText elaboration = dialog.findViewById(R.id.elaboracionDialog);
                RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
                RadioButton radioBaja = dialog.findViewById(R.id.radioButtonBaja);
                RadioButton radioMedia = dialog.findViewById(R.id.radioButtonMedia);
                RadioButton radioAlta = dialog.findViewById(R.id.radioButtonAlta);
                Spinner spinnerCategoria = dialog.findViewById(R.id.spinnerCategory);

                // ADAPTER NECESARIO PARA EL SPINNER + SU LISTENER
                // PARA RELLENAR EL SPINNER, ES NECESARIO UN STRING []; POR LO QUE UN METODO DAO LO TENDRA QUE DEVOLVER
                ArrayAdapter adapterSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, database.dataDao().getNombreCategorias());
                spinnerCategoria.setAdapter(adapterSpinner);
                spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        opcionSpinner = adapterSpinner.getItem(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                //RADIO BUTTONS

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch(i){
                            case R.id.radioButtonBaja:
                                dificultadReceta = 1;
                                break;
                            case R.id.radioButtonMedia:
                                dificultadReceta = 2 ;
                                break;
                            case R.id.radioButtonAlta:
                                dificultadReceta = 3;
                        }
                    }
                });
                //Botones dialogo
                Button back = dialog.findViewById(R.id.backDialogRec);
                FloatingActionButton add = dialog.findViewById(R.id.fabDialogReceta);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Receta receta = new Receta();
                        receta.setName(edName.getText().toString());
                        receta.setTime(edDuration.getText().toString());
                        receta.setIngredientes(ingredients.getText().toString());
                        receta.setElaboracion(elaboration.getText().toString());
                        receta.setPhoto(R.drawable.ic_launcher_background);

                        if (dificultadReceta == 0) {
                            dificultadReceta = 1;
                            receta.setDificultad(String.valueOf(dificultadReceta));
                        } else if (dificultadReceta == 1 || dificultadReceta == 2 || dificultadReceta == 3) {
                            receta.setDificultad(String.valueOf(dificultadReceta));
                        }

                        dialog.dismiss();

                        long result = database.dataDao().insertReceta(receta);
                        Log.i("category", "" + result);

                        recetaList = database.dataDao().selectAllRecetas();
                        adapterView = new AdapterReceta(recetaList, getActivity());
                        rv.setAdapter(adapterView);
                        Toast.makeText(requireContext(), "Receta inserted!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return view;
    }

}