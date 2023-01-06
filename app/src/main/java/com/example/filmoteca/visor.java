package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class visor extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);
        Intent intent = getIntent();
        String pelipulsada = intent.getStringExtra("MESSAGE");
        Toast.makeText(visor.this,"se pasa "+pelipulsada,Toast.LENGTH_SHORT).show();
    }


    //esto es menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se crea el menú que queramos para nuestra activity (inflate)
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
        // return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//se hace el CASE para cada opción del menu
        switch(item.getItemId()){
            case R.id.idioma:
                Toast.makeText(visor.this,"ha pulsado idioma",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.editpeli:
                Toast.makeText(visor.this,"ha pulsado editar",Toast.LENGTH_SHORT).show();
                irActivityeditor();
                return true;

            case R.id.borrarpeli:
                Toast.makeText(visor.this,"ha pulsado borrar",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.buscagoogle:
                Toast.makeText(visor.this,"ha pulsado buscar en google",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.acercade:
                Toast.makeText(visor.this,"ha pulsado acerca de",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
//hasta aqui se crea el menu



    public void irActivityeditor() {//método que lanzara la actividad siguiente con un intent
        Intent intent = new Intent(visor.this,editor.class);
        startActivity(intent);

    }
}