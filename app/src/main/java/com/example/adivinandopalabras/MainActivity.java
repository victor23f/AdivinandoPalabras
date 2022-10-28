package com.example.adivinandopalabras;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> palabras;
    TextView intentos,palabraPorDefecto;
    int intentos2;
    String palabra;
    EditText letra;
    boolean[] finPartida;
    char[] nuevaPalabra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jugar(View view){
        intentos= (TextView)findViewById(R.id.intentos);
    intentos.setText("5");
    intentos2 = Integer.parseInt(intentos.getText().toString());
    palabras = new ArrayList<String>();
    palabras.add("manzana");
    palabras.add("ciruela");
    palabras.add("escudo");
    palabra = palabras.get((int)Math.random()*2);

        palabraPorDefecto = (TextView)findViewById(R.id.palabra);
        nuevaPalabra =new char[palabra.length()];
        finPartida= new boolean[palabra.length()];
        for (int i=0;i<palabra.length();i++){
            nuevaPalabra[i]='_';
        }

            palabraPorDefecto.setText(String.valueOf(nuevaPalabra));



    }
    public void adivinar(View view){


        letra = (EditText)findViewById(R.id.letra);
        char[] letra1 = palabra.toCharArray();
        char letraIntroducida = letra.getText().charAt(0);


        intentos2--;
        intentos.setText(String.valueOf(intentos));

                    for (int i=0;i<letra1.length;i++){
                        if (letra1[i] == letraIntroducida){
                        nuevaPalabra[i]=letraIntroducida;
                        finPartida[i]=true;
                        }

                    }
                palabraPorDefecto.setText(String.valueOf(nuevaPalabra));




}}