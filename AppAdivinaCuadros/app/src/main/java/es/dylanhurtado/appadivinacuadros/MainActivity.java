package es.dylanhurtado.appadivinacuadros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button playBtn,aboutUsBtn,listBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.btnJugar);
        playBtn.setOnClickListener(view -> play());
        aboutUsBtn = findViewById(R.id.btnAcercaDe);
        aboutUsBtn.setOnClickListener(view -> aboutUs());
        listBtn = findViewById(R.id.btnCuadros);
        listBtn.setOnClickListener(view -> list());
    }

    private void play(){
        startActivity(new Intent(MainActivity.this,ActivityJuego.class));
    }

    private void aboutUs(){
        startActivity(new Intent(MainActivity.this,ActivityAcercaDe.class));
    }

    private void list(){
        startActivity(new Intent(MainActivity.this,ActivityLista.class));
    }
}