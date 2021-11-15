package com.example.acervobibliotecario.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.acervobibliotecario.R;
import com.example.acervobibliotecario.adapter.Adapter;
import com.example.acervobibliotecario.model.Acervo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivityTitulo extends AppCompatActivity {
    private EditText textTitulo;
    private Button buttonConsultar;
    private List<Acervo> acervos = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter = new Adapter(acervos);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_titulo);
        textTitulo = findViewById(R.id.editTextTituloConsulta);
        buttonConsultar = findViewById(R.id.buttonConsultar);
        recyclerView = findViewById(R.id.recyclerViewTitulo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = textTitulo.getText().toString();
                Acervo acervoArgumento = new Acervo();
                acervoArgumento.setTitulo(titulo);
                pesquisarAcervo(acervoArgumento);

            }
        });


    }


    private void pesquisarAcervo(Acervo acervo) {
        Query query;
        query = MainActivity.myRef.child("Acervo").orderByChild("titulo").startAt(acervo.getTitulo()).endAt(acervo.getTitulo() + "\uf8ff");




        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean result = snapshot.hasChildren();
                Acervo acervoPesquisa;
                if (!result) {
                    acervos.removeAll(acervos);
                    acervoPesquisa = new Acervo();
                    acervoPesquisa.setTitulo("Acervo Não disponivel");
                    acervos.add(acervoPesquisa);
                    adapter.notifyDataSetChanged();
                } else {
                    acervos.removeAll(acervos);
                    for (DataSnapshot obj : snapshot.getChildren()) {
                        acervoPesquisa = obj.getValue(Acervo.class);
                        Log.i("Resultado", acervoPesquisa.toString());
                        acervos.add(acervoPesquisa);
                        adapter.notifyDataSetChanged();
                    }
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