package com.example.appdoencaspi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText editTextDoencaID, editTextNome,editTextSintomas,editTextPrevencao;
    ProgressBar progressBar;
    ListView listView;
    Button buttonAddUpdate;

    List<Doenca> doencaList;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDoencaID = findViewById(R.id.editTextDoencaID);
        editTextNome = findViewById(R.id.editTextNome);
        editTextSintomas = findViewById(R.id.editTextSintomas);
        editTextPrevencao = findViewById(R.id.editTextPrevencao);

        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listViewDoencas);
        buttonAddUpdate = findViewById(R.id.btnAddUpdate);

        doencaList = new ArrayList<>();

        buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUpdating){
                    updateDoenca();
                }else{
                    createDoenca();
                }
            }

            
        });
        
        readDoenca();
    }

    private void readDoenca() {

        
    }

    private void createDoenca() {
    }

    private void updateDoenca() {

        
    }


}
