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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dylanhurtado.examenfinal.AdapterCategory;
import es.dylanhurtado.examenfinal.R;
import es.dylanhurtado.examenfinal.db.RoomDB;
import es.dylanhurtado.examenfinal.model.Category;


public class CategoryFragment extends Fragment {


    FloatingActionButton fab;
    RoomDB database;
    List<Category> categoryList = new ArrayList<>();
    AdapterCategory adapterView;
    RecyclerView rv;
    LinearLayoutManager llm;


    public CategoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = RoomDB.getInstance(getContext());

        categoryList = database.dataDao().selectAllCategory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_category, container, false);
        fab = view.findViewById(R.id.fabCategory);
        rv = view.findViewById(R.id.rvReceta);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapterView = new AdapterCategory(categoryList, getActivity());
        rv.setAdapter(adapterView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DIALOG
                
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_category);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setAttributes(lp);
                dialog.show();
                
                //VARIABLES
                EditText edName = dialog.findViewById(R.id.nameDialogCategory);
                
                Button back = dialog.findViewById(R.id.cat_dialog_back_button);
                Button add = dialog.findViewById(R.id.cat_dialog_add_btn);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Category category = new Category();
                        category.setName(edName.getText().toString());
                        category.setPhoto(R.drawable.ic_launcher_background);
                        dialog.dismiss();
                        
                        long result = database.dataDao().insertCategory(category);
                        Log.i("category", category.getName() +""+ category.getPhoto()+" "+result);
                        
                        categoryList = database.dataDao().selectAllCategory();
                        adapterView = new AdapterCategory(categoryList,getActivity());
                        rv.setAdapter(adapterView);
                        Toast.makeText(requireContext(), "Category inserted!!", Toast.LENGTH_SHORT).show();
                    }
                });
                
            }
        });

        return view;
    }
}