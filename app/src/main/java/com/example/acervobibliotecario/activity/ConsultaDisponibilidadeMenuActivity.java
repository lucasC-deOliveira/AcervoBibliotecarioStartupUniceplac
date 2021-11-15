package com.example.acervobibliotecario.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.acervobibliotecario.R;

public class ConsultaDisponibilidadeMenuActivity extends AppCompatActivity {

    private Button buttonTitulo, buttonAutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_disponibilidade_menu);

        buttonAutor = findViewById(R.id.buttonActivityAutor);
        buttonTitulo = findViewById(R.id.buttonActivityTitulo);

        buttonAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaActivityAutor.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaActivityTitulo.class);
                startActivity(intent);
                finish();
            }
        });

    }
}