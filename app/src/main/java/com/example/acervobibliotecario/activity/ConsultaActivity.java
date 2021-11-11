package com.example.acervobibliotecario.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.acervobibliotecario.R;
import com.example.acervobibliotecario.model.Acervo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    private EditText textTitulo, textAutor;
    private TextView textResultado;
    private Button buttonConsultar;
    public static List<Acervo> acervos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        textTitulo = findViewById(R.id.editTextTituloConsulta);
        textAutor= findViewById(R.id.editTextAutorConsulta);
        buttonConsultar= findViewById(R.id.buttonConsultar);
        textResultado= findViewById(R.id.textResultadoConsulta);


        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = textTitulo.getText().toString();
                String autor = textAutor.getText().toString();
                Acervo acervoArgumento = new Acervo();
                acervoArgumento.setTitulo(titulo);
                acervoArgumento.setAutor(autor);
                pesquisarAcervo(acervoArgumento);

              





            }
        });










    }


    private void pesquisarAcervo(Acervo acervo){
        Query query;
        query= MainActivity.myRef.child("Acervo");
        if(!acervo.getAutor().equals("")){
            query = query.orderByChild("autor").equalTo(acervo.getAutor());
        }
        else if(!acervo.getTitulo().equals("")){
            query = query.orderByChild("titulo").equalTo(acervo.getTitulo());
        }



        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             boolean result= snapshot.hasChildren();
             if(result){
                 textResultado.setText("Acervo Disponivel");
                 for(DataSnapshot obj: snapshot.getChildren()){
                     Acervo acervoPesquisa = obj.getValue(Acervo.class);
                     acervos.add(acervoPesquisa);
                 }
             }
             else{
                 textResultado.setText("Título ou Autor não encontrado");


             }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}