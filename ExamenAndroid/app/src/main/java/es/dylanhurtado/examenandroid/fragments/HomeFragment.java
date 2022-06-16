package es.dylanhurtado.examenandroid.fragments;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.dylanhurtado.examenandroid.R;


public class HomeFragment extends Fragment {

    ImageView imageAula,imageReloj;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        imageAula= view.findViewById(R.id.imageViewAula);
        imageReloj= view.findViewById(R.id.imageViewReloj);
        imageAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ListaAulaFragment()).addToBackStack(null).commit();
            }
        });
        imageReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new DiasListFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}