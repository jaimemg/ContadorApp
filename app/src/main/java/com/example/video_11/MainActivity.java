package com.example.video_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public int contador;
    TextView textoResultado;

    class EventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView textView, int actionid, KeyEvent keyEvent) {
            if(actionid == EditorInfo.IME_ACTION_DONE) {
                resetear(null);  //resetear contador al pulsar el botón OK del teclado, ejecutando el método resetear con lo que ya tiene (null)
            }
            return false;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado = (TextView)findViewById(R.id.contadorTexto);
        contador = 0;

        textoResultado.setText(""+ contador);

        EventoTeclado teclado = new EventoTeclado();
        EditText n_reseteo = (EditText) findViewById(R.id.initialVal); // Elemento EditText a la escucha
        n_reseteo.setOnEditorActionListener(teclado);
    }

    public void incrementar(View vista){
        contador++;
        textoResultado.setText("" + contador);

    }
    public void decrementar(View vista){
        contador--;
        if(contador < 0){
            CheckBox negativos = (CheckBox)findViewById(R.id.negative);
            if(!negativos.isChecked()){
                contador = 0;
            }
        }
        textoResultado.setText("" + contador);

    }

    /*public void onSaveInstanceState(Bundle state){  //persistencia de datos al girar el dispositivo y al mantener la aplicación en segundo plano

        state.putInt("cuenta", contador);   //creamos la clave y guardamos el entero en el bundle
        super.onSaveInstanceState(state);   //guardamos el estado del bundle

    }

    public void onRestoreInstanceState (Bundle state){

        super.onRestoreInstanceState(state);    //recuperamos el estado del bundle
        contador = state.getInt("cuenta");  //guardamos el valor de la clave en contador

        textoResultado.setText("" +contador);
    }*/

    public void onPause (){

        super.onPause();
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);     //creamos nuestra SharedPreference
        SharedPreferences.Editor miEditor = prefs.edit();   //la hacemos editabe

        miEditor.putInt("cuenta", contador);    //almacenamos el valor que queremos

        miEditor.commit();      //guardamos
    }

    public void onResume (){
        super.onResume();
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);

        contador = prefs.getInt("cuenta", 0);   //recuperamos el valor almacenado

        textoResultado.setText("" + contador);      //actualizamos el contador

    }


    public void resetear(View vista){
        EditText valorInicial = (EditText) findViewById(R.id.initialVal);
        try {
            contador = Integer.parseInt(valorInicial.getText().toString());

        }catch (Exception e){ //Controlar excepción primer reset
            contador = 0;
        }
        textoResultado.setText("" + contador);

        InputMethodManager miTeclado = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE); //Ocultar teclado tras resetear contador
        miTeclado.hideSoftInputFromWindow(valorInicial.getWindowToken(), 0);
    }


}