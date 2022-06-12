package es.dylanhurtado.cambiarentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EdadUsuarioActivity extends AppCompatActivity {
    private Button backBtn;
    private TextView textViewNombreIntr, textViewEdadIntr, textViewResultado;
    private ImageView imageViewResultado;
    String name="",edad="";
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edad_usuario);
        this. textViewNombreIntr = findViewById(R.id.nameTV);
        this. textViewEdadIntr = findViewById(R.id.ageTV);
        this.textViewResultado = findViewById(R.id.resultTV);
        this.imageViewResultado = findViewById(R.id.imageView);
        this.backBtn = findViewById(R.id.backButton);

        this.name="";
        this.edad="";
        this.extras = getIntent().getExtras();
        if(extras != null){
            this.name = this.extras.getString("name");
            this.edad = this.extras.getString("age");
        }
        textViewNombreIntr.setText("Name : " + this.name);
        textViewEdadIntr.setText("Edad : " + this.edad);

        if(Integer.parseInt(edad)>=18){
            textViewResultado.setText("Result: Es mayor de edad");
            imageViewResultado.setImageResource(R.drawable.ic_launcher_foreground);
        }else{
            textViewResultado.setText("Result: Es menor de edad");
            imageViewResultado.setImageResource(R.drawable.ic_launcher_background);
        }
        backBtn.setOnClickListener(view -> EdadUsuarioActivity.this.back());
    }


    private void back(){
        startActivity(new Intent(this,MainActivity.class));
    }
}