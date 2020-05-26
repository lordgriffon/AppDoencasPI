package com.example.appdoencaspi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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

    private void refreshDoencaList(JSONArray doencas) throws JSONException{
        doencaList.clear();

        for(int i = 0; i < doencas.length();i++){
            JSONObject obj = doencas.getJSONObject(i);
            doencaList.add(new Doenca(
                    obj.getInt("id"),
                    obj.getString("nome"),
                    obj.getString("sintomas"),
                    obj.getString("prevencao")
            ));
        }


    }

    private void readDoenca() {


    }

    private void createDoenca() {

        String id = editTextDoencaID.getText().toString();
        String nome = editTextNome.getText().toString();
        String sintomas = editTextSintomas.getText().toString();
        String prevencao = editTextPrevencao.getText().toString();

        if(TextUtils.isEmpty(nome)){
            editTextNome.setError("Por Favor entre com o nome");
            editTextNome.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(sintomas)){
            editTextNome.setError("Por Favor entre com os sintomas");
            editTextNome.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(prevencao)){
            editTextNome.setError("Por Favor entre com as prevenções");
            editTextNome.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("nome", nome);
        params.put("sintomas", sintomas);
        params.put("prevencao", prevencao);


    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshDoencaList(object.getJSONArray("heroes"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            if(requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);

            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);


            return null;
        }
    }

    private void updateDoenca() {

        
    }

    class DoencaAdapter extends ArrayAdapter<Doenca>{
        List<Doenca> doencaList;

        public DoencaAdapter(List<Doenca> doencaList){
            super(MainActivity.this,R.layout.layout_lista_doencas,doencaList);
            this.doencaList = doencaList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }


}
