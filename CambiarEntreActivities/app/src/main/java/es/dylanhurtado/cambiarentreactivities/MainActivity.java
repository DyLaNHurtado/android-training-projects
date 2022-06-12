package es.dylanhurtado.cambiarentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET,ageET;
    private Button buttonAge, buttonForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        buttonAge = findViewById(R.id.sendDataButton);
        buttonForm = findViewById(R.id.buttonBC);
        buttonAge.setOnClickListener(view -> verMenuPersona());
        buttonForm.setOnClickListener(view -> verMenuInfo());

    }
    private void verMenuPersona(){
        if(nameET.getText().toString().trim().length()>0 && ageET.getText().toString().trim().length()>0) {
            Intent intent = new Intent(MainActivity.this,EdadUsuarioActivity.class);
            intent.putExtra("name", nameET.getText().toString());
            intent.putExtra("age", ageET.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(this, "Some Fields are empty !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void verMenuInfo(){
        startActivity(new Intent(MainActivity.this,FormActivity.class));
    }
}