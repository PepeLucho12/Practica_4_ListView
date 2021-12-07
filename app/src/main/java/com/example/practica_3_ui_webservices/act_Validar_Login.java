package com.example.practica_3_ui_webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.practica_3_ui_webservices.WebService.Asynchtask;
import com.example.practica_3_ui_webservices.WebService.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class act_Validar_Login extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_validar_login);

        //Localizar los controles
        //TextView txtSaludo = (TextView)findViewById(R.id.lblMensaje);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        //txtSaludo.setText("Hola!, Bienvenido \n " + bundle.getString("usr") + "\t Clave:" + bundle.getString("pass"));

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://uealecpeterson.net/ws/login.php?usr="
                + bundle.getString("Usr") + "&pass=" + bundle.getString("clave"),
                datos, SaludoActivity.this, SaludoActivity.this);
        ws.execute("GET");


    }

    @Override
    public void processFinish(String result) throws JSONException {
        //TextView txtSaludo = (TextView)findViewById(R.id.lblMensaje);
        //txtSaludo.setText("Hola!, Bienvenido \n " + bundle.getString("usr") + "\t Clave:" + bundle.getString("pass"));
    }
}