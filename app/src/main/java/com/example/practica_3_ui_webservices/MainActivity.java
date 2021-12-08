package com.example.practica_3_ui_webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica_3_ui_webservices.Interfaz.Api_User;
import com.example.practica_3_ui_webservices.Modelos.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText tvNombre;
    TextView tvInformacion;
    Button btnBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNombre=findViewById(R.id.teNombre);
        tvInformacion=findViewById(R.id.txtInfo);
        btnBuscar=findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarInfo(tvNombre.getText().toString());
            }
        });
    }

    private void MostrarInfo(String name){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://gorest.co.in/").addConverterFactory(GsonConverterFactory.create()).build();

        Api_User api_user=retrofit.create(Api_User.class);
        Call<User> call=api_user.find(name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if(response.isSuccessful()){
                        User u= response.body();
                        tvInformacion.setText("id: " + u.getId().toString() + " Nombre: " + u.getName().toString() +
                                " Genero: " + u.getGender().toString() +" Correo: " + u.getEmail().toString() + " Estado: " + u.getStatus().toString() + "\n");
                    }

                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnEnviar(View view) {
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, act_Validar_Login.class);
        EditText txtNombre = (EditText) findViewById(R.id.txtUsr);
        EditText txtPass = (EditText) findViewById(R.id.txtPass);

        //Creamos la información a pasar entre actividades - Pares Key-Value
        Bundle b = new Bundle();
        b.putString("usr", txtNombre.getText().toString());
        b.putString("pass", txtPass.getText().toString());

        //Añadimos la información al intent
        intent.putExtras(b);

        // Iniciamos la nueva actividad
        startActivity(intent);
    }
}