package com.example.adivinandopalabras;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
    List<String> palabras;
    int intentos;
    String palabra;
    char[] palabraEnChar;
    ArrayList<Character> caracteresRepetidos;
    Context contexto;
    public Partida() {

    }

    //Inciamos la partida, eligiendo una palabra al azar de la coleccion palabras y la transformamos a un array de chars.
    public String iniciarPartida() {


        palabras = new ArrayList<String>();
        palabras.add("ciruela");
        palabras.add("manzana");
        palabras.add("escudo");

        Random elegida = new Random();
        palabra = palabras.get(elegida.nextInt(3));
        intentos=palabra.length()/2;

        palabraEnChar = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            palabraEnChar[i] = '_';
        }

        return String.valueOf(palabraEnChar);
    }



    public String adivinar(char letraIntroducida) {


        if (intentos >= 1) {
            if (letraHallada(letraIntroducida)) {
                if (comprobarFinal()) {
                   // volverJugar(true);//Ha ganado si comprobar final devuelve true
                }
            } else {
                intentos--;
            }

        } else {
         //   volverJugar(false);//Ha perdido porque se ha quedado sin intentos

        }


        return String.valueOf(palabraEnChar);
    }

    public int getIntentos() {
        return intentos;
    }

    //Si la letra introducida se encuentra en la palabra, cambia la máscara de guiones por la letra en su posicion
    public boolean letraHallada(char letraIntroducida) {
        boolean letrahallada = false;

        for (int i = 0; i < palabraEnChar.length; i++) {
            if (palabra.charAt(i) == letraIntroducida) {
                palabraEnChar[i] = letraIntroducida;
                letrahallada = true;
            }

        }
        return letrahallada;
    }

    //Este metodo comprueba que la palabra ha sido resuelta completamente, devuelve true si es asi
    public boolean comprobarFinal() {
        boolean terminado = true;

        for (int i = 0; i < palabraEnChar.length; i++) {
            if (palabraEnChar[i] == '_') {
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


    public void iniciarContexto(Context contextoaux) {
        contexto = contextoaux;
    }



    public AlertDialog.Builder volverJugarPartidaPerdida(){
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setTitle("Fin de la partida, perdiste.").setMessage("La palabra era: " + palabra).setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("Cerrar aplicacion", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(contexto, "Sigue practicando!", Toast.LENGTH_LONG).show();
                ((Activity) contexto).finish();
            }
        });
        return builder;
    }

    public AlertDialog.Builder volverJugarPartidaGanada(){
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setTitle("Felicidades, has ganado!!").setMessage("La palabra era: " + palabra).setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("Cerrar aplicacion", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(contexto, "Vuelve pronto!", Toast.LENGTH_LONG).show();
                    ((Activity) contexto).finish();
                }
            });
        return builder;
    }



/*
    public void volverJugar(boolean ganar) {


        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        if (ganar) {

            builder.setTitle("Felicidades, has ganado!!").setMessage("La palabra era: " + palabra).setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            }).setNegativeButton("Cerrar aplicacion", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(contexto, "Vuelve pronto!", Toast.LENGTH_LONG).show();
                    ((Activity) contexto).finish();
                }
            });
        } else {
            builder.setTitle("Fin de la partida, perdiste.").setMessage("La palabra era: " + palabra).setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("Cerrar aplicacion", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(contexto, "Sigue practicando!", Toast.LENGTH_LONG).show();
                    ((Activity) contexto).finish();
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }*/







}