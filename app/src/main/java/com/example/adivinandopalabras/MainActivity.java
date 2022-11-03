package com.example.adivinandopalabras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView intentos, palabraAadivinar;
    EditText letra;
    ArrayList<Character> caracteresRepetidos;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        intentos = findViewById(R.id.intentos);
        palabraAadivinar = findViewById(R.id.palabra);
        partida = new Partida();
        partida.iniciarContexto(this);
        palabraAadivinar.setText(String.valueOf(partida.iniciarPartida()));

        intentos.setText(partida.getIntentos() + "");
    }

    public void jugar(View view) {
        reiniciar();
    }

    public void reiniciar() {
        palabraAadivinar.setText(partida.iniciarPartida());
        intentos.setText("" + partida.getIntentos());
    }

    public void adivinar(View vista) {
        letra = findViewById(R.id.letra);
        if (partida.getIntentos() >= 1) {

            try {
                palabraAadivinar.setText(partida.adivinar(String.valueOf(letra.getText()).charAt(0)));
                intentos.setText(partida.getIntentos() + "");
                letra.setText("");
                if (partida.comprobarFinal()) {
                    partida.volverJugarPartidaGanada().show();
                    reiniciar();
                }
            } catch (IndexOutOfBoundsException ioe) {
                Toast.makeText(this, "Introduce una letra!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (!partida.comprobarFinal()) {
                partida.volverJugarPartidaPerdida().show();
                reiniciar();
            }
        }
    }

}