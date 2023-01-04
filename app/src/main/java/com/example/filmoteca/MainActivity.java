package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

            case R.id.editpeli:
                Toast.makeText(MainActivity.this,"ha pulsado editar",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.borrarpeli:
                Toast.makeText(MainActivity.this,"ha pulsado borrar",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.buscagoogle:
                Toast.makeText(MainActivity.this,"ha pulsado buscar en google",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.acercade:
                Toast.makeText(MainActivity.this,"ha pulsado acerca de",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
//hasta aqui se crea el menu
    public void irActivityConcexionbbdd(View view) {//método que lanzara la actividad siguiente con un intent
        Intent intent = new Intent(MainActivity.this,listapelis.class);
        startActivity(intent);

    }
}