package es.dylanhurtado.cambiarentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    EditText editTextNombre, editTextApellido;
    Button botonSend, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTextNombre = findViewById(R.id.nameETForm);
        editTextApellido = findViewById(R.id.apellidoET);
        botonSend= findViewById(R.id.sendDatosButton);
        buttonBack= findViewById(R.id.btnBack);
        botonSend.setOnClickListener(view -> mandarInfo());
        buttonBack.setOnClickListener(view -> back());
    }

    private void mandarInfo(){

        if (editTextNombre.getText().toString().trim().length() > 0 && editTextApellido.getText().toString().trim().length() > 0) {
            editTextNombre.setText("");
            editTextApellido.setText("");
            Toast.makeText(this, "Info sending successful!!!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Some field empty!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void back(){
        startActivity(new Intent(this,MainActivity.class));
    }
}