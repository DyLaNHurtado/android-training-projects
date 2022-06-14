package es.dylanhurtado.bottomnavigatorviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.dylanhurtado.bottomnavigatorviewdemo.fragments.FirstFragment;
import es.dylanhurtado.bottomnavigatorviewdemo.fragments.SecondFragment;
import es.dylanhurtado.bottomnavigatorviewdemo.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    /*
       1. Modificamos el layout de main activity.
           - Yo añado un linearLayout para que me sea mas sencillo organizar los elementos.
           - FrameLayout (donde se veran los fragmentos) (importante el ID)
           - BottomNavigationView (importante el ID)
       2. Creacion de un "Android Resource Directory" en res
           - Resource Type: Menu
           - Nuevo "Menu Resource File"
           - Ahi se colocaran los elementos que veremos en el menu inferior, así como sus iconos.
               - Para agregar iconos propios Android:
                   - drawable -> New -> Vector Asset
           - Importante dar un ID a cada elemento.
       3. De vuelta al layout de main activity, escribimos lo siguiente dentro del BottomNavigationView:
           - app:menu="@menu/menu_layout"  | Siendo eso ultimo el nombre layout propio
               o simplemente buscamos en las opciones "menu", no es necesario meterse en el codigo del elemento
               en el layout.
       4. Programacion de Main Activity
       */

    // Elemento de navegacion
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enlazarComponentes();
        remplazarFragmentos(new SecondFragment());
        menuListeners();
    }

    // Metodo que enlaca los componentes de la vista
    private void enlazarComponentes(){
        bottomNavigationView = findViewById(R.id.botNavView);
    }

    // Metodo que reemplaza los fragmentos en el FrameLayout creado
    private void remplazarFragmentos(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    // Listeners para los elementos del menu
    private void menuListeners(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_1:
                    remplazarFragmentos(new FirstFragment());
                    break;
                case R.id.menu_2:
                    remplazarFragmentos(new SecondFragment());
                    break;
                case R.id.menu_3:
                    remplazarFragmentos(new ThirdFragment());
            }
            return true;
        });
    }
}