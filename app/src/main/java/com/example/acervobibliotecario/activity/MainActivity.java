package com.example.acervobibliotecario.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.acervobibliotecario.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    public static  FirebaseDatabase dataBase;
    public static DatabaseReference myRef;
    static FirebaseAuth usuario= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarFirebase();
        //usuario.signOut();


        if(usuario.getCurrentUser() == null){
            Intent intent = new Intent(getApplicationContext(), LoginUsuarioActivity.class);
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
        }



    }


    private void iniciarFirebase(){
        dataBase = FirebaseDatabase.getInstance();
        myRef = dataBase.getReference();
    }
}