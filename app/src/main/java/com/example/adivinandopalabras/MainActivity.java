package com.example.adivinandopalabras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> palabras;
    TextView intentos, palabraPorDefecto;
    int intentos2;
    String palabra;
    EditText letra;
    char[] nuevaPalabra;
    ArrayList<Character> caracteresRepetidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarPartida();
    }

    public void iniciarPartida() {
        intentos = (TextView) findViewById(R.id.intentos);
        intentos.setText("5");
        intentos2 = Integer.parseInt(intentos.getText().toString());
        palabras = new ArrayList<String>();
        palabras.add("ciruela");
        palabras.add("manzana");
        palabras.add("escudo");

        Random elegida = new Random();
        palabra = palabras.get(elegida.nextInt(3));

        palabraPorDefecto = (TextView) findViewById(R.id.palabra);
        nuevaPalabra = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            nuevaPalabra[i] = '_';
        }

        palabraPorDefecto.setText(String.valueOf(nuevaPalabra));
    }

    public void jugar(View view) {

        iniciarPartida();


    }

    public void adivinar(View view) {
        try {


            if (palabra == null) {
                Toast.makeText(this, "Debes de comenzar el juego!!", Toast.LENGTH_LONG).show();
            } else {
                letra = (EditText) findViewById(R.id.letra);
                char letraIntroducida = letra.getText().charAt(0);


                if (intentos2 > 1) {
                    if (letraHallada(letraIntroducida)) {

                    } else {
                        intentos2--;
                    }

                    palabraPorDefecto.setText(String.valueOf(nuevaPalabra));
                } else {
                    volverJugar(view, false);

                }

                intentos.setText(String.valueOf(intentos2));
                if (comprobarFinal()) {

                    volverJugar(view, true);
                }
            }
            ((EditText) findViewById(R.id.letra)).setText("");
        } catch (IndexOutOfBoundsException iobe) {
            Toast.makeText(this, "Introduce una letra!", Toast.LENGTH_SHORT).show();
        }
    }

    //Si la letra introducida se encuentra en la palabra, cambia la máscara de guiones por la letra en su posicion
    public boolean letraHallada(char letraIntroducida) {
        boolean letrahallada = false;
        char[] letra1 = palabra.toCharArray();

        for (int i = 0; i < letra1.length; i++) {
            if (palabra.charAt(i) == letraIntroducida) {
                nuevaPalabra[i] = letraIntroducida;
                letrahallada = true;
            }

        }
        return letrahallada;
    }

    //Este metodo comprueba que la palabra ha sido resuelta completamente, devuelve true si es asi
    public boolean comprobarFinal() {
        boolean terminado = true;

        for (int i = 0; i < nuevaPalabra.length; i++) {
            if (nuevaPalabra[i] == '_') {
                terminado = false;
                break;
            }
        }
        return terminado;
    }

    public boolean letrasRepetidas(char caracter) {
        boolean repetida = false;
        if (caracteresRepetidos != null) {
            if (caracteresRepetidos.contains(caracter)) {
                repetida = true;
            }
            caracteresRepetidos.add(caracter);
        }


        return repetida;
    }

    public void volverJugar(View view, boolean ganar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (ganar) {

            builder.setTitle("Felicidades, has ganado!!").
                    setMessage("La palabra era: " + palabra).setPositiveButton("Volver a jugar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    jugar(view);
                                }
                            }).setNegativeButton("Cerrar aplicacion", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Vuelve pronto!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
        } else {
            builder.setTitle("Fin de la partida, perdiste.").
                    setMessage("La palabra era: " + palabra).setPositiveButton("Volver a jugar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    jugar(view);
                                }
                            }).setNegativeButton("Cerrar aplicacion", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Sigue practicando!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}