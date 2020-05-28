package com.example.appdoencaspi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cadastro_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);
    }

    public void voltarLogin(View view) {
        Intent intent = new Intent(Cadastro_Activity.this,Login_Activity.class);
        startActivity(intent);
    }
}
