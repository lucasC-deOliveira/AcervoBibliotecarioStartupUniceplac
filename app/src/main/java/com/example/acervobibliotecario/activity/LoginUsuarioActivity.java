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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginUsuarioActivity extends AppCompatActivity {

    EditText textEmail, textSenha;
    Button btLogin, btCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        textEmail = findViewById(R.id.editTextEmailLogin);
        textSenha = findViewById(R.id.editTextSenhaLogin);
        btLogin = findViewById(R.id.buttonLogar);
        btCriarConta = findViewById(R.id.btCriarConta);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String senha =textSenha.getText().toString().trim();

                MainActivity.usuario.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Login Realizado!",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Login Não Realizado, Verifique os campos!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastrarUsuarioActivity.class);
                startActivity(intent);
            }
        });

    }
}