package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }







   //esto es menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se crea el menú que queramos para nuestra activity (inflate)
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
       // return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//se hace el CASE para cada opción del menu
        switch(item.getItemId()){
            case R.id.idioma:
                Toast.makeText(MainActivity.this,"ha pulsado idioma",Toast.LENGTH_SHORT).show();
                return true;


            case R.id.acercade:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Acerca de");
                builder.setMessage("Esta aplicacion ha sido creada por Borja Rodriguez Para la asignatura prog dirigida por eventos");


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
//hasta aqui se crea el menu
    public void irActivityConcexionbbdd(View view) {//método que lanzara la actividad siguiente con un intent
        Intent intent = new Intent(MainActivity.this, conectaaBBDD.class);
        startActivity(intent);

    }
}