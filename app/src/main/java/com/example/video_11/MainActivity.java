package com.example.video_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int contador;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = 0;
    }

    public void incrementar(View vista){
        contador++;
        mostrar();
    }
    public void decrementar(View vista){
        contador--;
        mostrar();
    }
    public void resetear(View vista){
        contador = 0;
        mostrar();
    }

    public void mostrar(){
        TextView resultado = (TextView)findViewById(R.id.contadorPulsaciones);
        resultado.setText("Count: " + contador);
    }
}