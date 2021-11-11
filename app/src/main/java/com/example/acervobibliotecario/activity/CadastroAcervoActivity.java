package com.example.acervobibliotecario.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acervobibliotecario.R;
import com.example.acervobibliotecario.model.Acervo;

public class CadastroAcervoActivity extends AppCompatActivity {
private EditText textTitulo;
private EditText textISBN;
private EditText textAutor;
private EditText textEdicao;
private Button buttonCadastrar;
private TextView erroCampos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_acervo);
        textTitulo = findViewById(R.id.editTextTitulo);
        textISBN = findViewById(R.id.editTextISBN);
        textAutor=findViewById(R.id.editTextAutor);
        textEdicao=findViewById(R.id.editTextEdicao);
        buttonCadastrar=findViewById(R.id.buttonCadastrarAcervo);
        erroCampos=findViewById(R.id.textViewCamposErro);




        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textTitulo.getText().toString().equals("") || textISBN.getText().toString().equals("")||
                        textAutor.getText().toString().equals("")|| textEdicao.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Todos os campos devem estar preenchidos", Toast.LENGTH_SHORT).show();
                    erroCampos.setText("Todos os campos devem estar preenchidos");
                }
                else{

                    erroCampos.setText("");
                    String titulo = textTitulo.getText().toString().trim();
                    String isbn = textISBN.getText().toString().trim();
                    String autor = textAutor.getText().toString().trim();
                    String edicao= textEdicao.getText().toString().trim();
                    String UID = java.util.UUID.randomUUID().toString();
                    Acervo acervo = new Acervo(UID,titulo,isbn,autor,edicao);
                    MainActivity.myRef.child("Acervo").child(UID).setValue(acervo);
                    Toast.makeText(getApplicationContext(),"Sucesso ao cadastrar", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


               }

            }
        });




    }
}