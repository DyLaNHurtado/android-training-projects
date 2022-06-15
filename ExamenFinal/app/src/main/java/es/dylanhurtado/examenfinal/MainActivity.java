package es.dylanhurtado.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.dylanhurtado.examenfinal.fragments.CategoryFragment;
import es.dylanhurtado.examenfinal.fragments.RecetaFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enlazarComponentes();
        remplazarFragmentos(new CategoryFragment());
        menuListeners();
    }

    // Metodo que enlaca los componentes de la vista
    private void enlazarComponentes(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_1 );
    }

    // Metodo que reemplaza los fragmentos en el FrameLayout creado
    private void remplazarFragmentos(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    private void menuListeners(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_1:
                    remplazarFragmentos(new CategoryFragment());
                    break;
                case R.id.menu_2:
                    remplazarFragmentos(new RecetaFragment());
            }
            return true;
        });
    }
}