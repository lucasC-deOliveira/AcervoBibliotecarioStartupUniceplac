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
import com.example.acervobibliotecario.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarUsuarioActivity extends AppCompatActivity {
    EditText textEmail, textSenha, textNome, textData, textGenero;
    Button btCriarConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        textEmail = findViewById(R.id.editTextEmail);
        textSenha = findViewById(R.id.editTextSenha);
        textNome = findViewById(R.id.editTextNome);
        textData = findViewById(R.id.editTextDataNascimento);
        textGenero = findViewById(R.id.editTextGenero);
        btCriarConta = findViewById(R.id.buttonCriarConta);

        btCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String senha = textSenha.getText().toString().trim();
                String nome = textNome.getText().toString().trim();
                String data = textData.getText().toString().trim();
                String genero = textGenero.getText().toString().trim();
                Date dataData = parseDate(data);

                MainActivity.usuario.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(CadastrarUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Usuario usuario = new Usuario(MainActivity.usuario.getUid(),nome,email,dataData.getTime(),genero,senha);
                            MainActivity.myRef.child("Usuario").child(usuario.getUID()).setValue(usuario);
                            Toast.makeText(getApplicationContext(),"Cadastro Realizado",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Cadastro Não Realizado",Toast.LENGTH_LONG).show();
                        }
                    }
                });




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