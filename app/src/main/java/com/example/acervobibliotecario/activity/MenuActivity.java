package com.example.acervobibliotecario.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.acervobibliotecario.R;

public class MenuActivity extends AppCompatActivity {
    private Button buttonCadastrar;
    private Button buttonConsultar;
    private Button buttonAgendar, buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonCadastrar=findViewById(R.id.buttonCadastrarAcervo);
        buttonAgendar=findViewById(R.id.buttonAgendar);
        buttonConsultar= findViewById(R.id.buttonConsultarAcervo);
        buttonLogout = findViewById(R.id.buttonLogout);


        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroAcervoActivity.class);
                startActivity(intent);
            }
        });

        buttonAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AgendarActivity.class);
                startActivity(intent);
            }
        });

        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaActivity.class);
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.usuario.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}