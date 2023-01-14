package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {//override del metodo onCreate autogenerado
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }







   //Creacion del menu de opciones

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se crea el menú que queramos(de las tres variantes posibles) para nuestra activity (inflate)
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//se hace el CASE para cada opción del menu
        switch(item.getItemId()){
            case R.id.idioma: //case idioma con las dos opciones ingles-español y el mensaje de confirmacion
                AlertDialog.Builder locale = new AlertDialog.Builder(this);
                locale.setTitle(R.string.selecciondeidioma);
                locale.setMessage(R.string.textoselecciondeidioma);


                locale.setPositiveButton(R.string.nombredelidioma, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Locale locale = new Locale("es");
                        Locale.setDefault(locale);

                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());

                        // Vuelve a cargar la interfaz de usuario para que se reflejen los cambios de idioma
                        recreate();
                    }
                });

                locale.setNegativeButton(R.string.nombredelidiomaen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Establece el idioma inglés como el idioma por defecto
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);

                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());

                        // Vuelve a cargar la interfaz de usuario para que se reflejen los cambios de idioma
                        recreate();
                    }
                });

                // Muestra el cuadro de diálogo
                AlertDialog dialog = locale.create();
                dialog.show();
                return true;


            case R.id.acercade://case acerca de con el mensaje de acerca de, crea un cuadro de dialogo con un mensaje descriptivo
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.tituloacercade);
                builder.setMessage(R.string.mensajeacercade);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
//hasta aqui la creacion del menu de opciones


    public void irActivityConcexionbbdd(View view) {//método que lanzara la actividad siguiente con un intent
        Intent intent = new Intent(MainActivity.this, conectaaBBDD.class);
        startActivity(intent);

    }
}