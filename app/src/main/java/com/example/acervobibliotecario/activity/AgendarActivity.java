package com.example.acervobibliotecario.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acervobibliotecario.R;
import com.example.acervobibliotecario.model.Acervo;
import com.example.acervobibliotecario.model.RetirarAcervo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AgendarActivity extends AppCompatActivity {
        private EditText textTitulo, textAutor, textRetirada;
        private Button buttorAgendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        textTitulo = findViewById(R.id.editTextTituloAgendar);
        textAutor = findViewById(R.id.editTextAutorAgendar);
        textRetirada = findViewById(R.id.editTextDiaRetirada);
        buttorAgendar = findViewById(R.id.buttonAgendarRetirada);

        buttorAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = textTitulo.getText().toString();
                String autor = textAutor.getText().toString();
                String data= textRetirada.getText().toString();
                Acervo acervoArgumento = new Acervo();
                acervoArgumento.setTitulo(titulo);
                acervoArgumento.setAutor(autor);
               agendarAcervo(acervoArgumento, data);
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);


            }
        });


    }




    private void agendarAcervo(Acervo acervo, String data){
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
                    for(DataSnapshot obj: snapshot.getChildren()){
                    Acervo acervoPesquisa = obj.getValue(Acervo.class);
                    String UID = java.util.UUID.randomUUID().toString();

                        Date dataData = parseDate(data);


                        RetirarAcervo retirar = new RetirarAcervo(UID,acervoPesquisa.getUID(), dataData.getTime());
                    MainActivity.myRef.child("AgendaRetirada").child(UID).setValue(retirar);
                        Toast.makeText(getApplicationContext(), "Agenda Realizada com sucesso", Toast.LENGTH_LONG).show();


                }
                }

                else{
                    Toast.makeText(getApplicationContext(), "Não foi possivel agendar a retirada pois o titulo não foi encontrado", Toast.LENGTH_LONG).show();
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


    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}