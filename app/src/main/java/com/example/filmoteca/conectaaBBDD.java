package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class conectaaBBDD extends AppCompatActivity {
    private FirebaseAuth mAuth;//conexion a la BBDD Firebase







    @Override
    protected void onCreate(Bundle savedInstanceState) {//override del metodo onCreate, se hace un getInstance de la conexion a la BBDD

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectarbbdd);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {//override del metodo onStart, se comprueba si el usuario esta logueado o no anonimamente
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {//se comprueba si el usuario esta logueado o no anonimamente
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        TextView estadocon=(TextView) findViewById(R.id.textoconexionbbdd);
                        if (task.isSuccessful()) {//dependiendo del estado de la conexion se muestra un mensaje en verde o rojo


                            FirebaseUser user = mAuth.getCurrentUser();
                            estadocon.setText(R.string.conectadoabbdd);
                            estadocon.setTextColor(Color.GREEN);
                            updateUI(user);
                        } else {
                            estadocon.setText(R.string.noconectadoabbdd);
                            estadocon.setTextColor(Color.RED);
                            updateUI(null);
                        }
                    }
                });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se crea el men?? que queramos para nuestra activity (inflate)
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;


    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//se hace el CASE para cada opci??n del menu
        switch(item.getItemId()){
            case R.id.idioma:
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
                        // Establece el idioma ingl??s como el idioma por defecto
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

                // Muestra el cuadro de di??logo
                AlertDialog dialog = locale.create();
                dialog.show();
                return true;


            case R.id.acercade:
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

    private void updateUI(FirebaseUser user) {

    }



    //este metodo abre la siguiente actividad mediante un intent
    public void irActivityVisor(View view){

    Intent intent = new Intent(conectaaBBDD.this,PelisListView.class);
    startActivity(intent);

    }

    public void irActivityJSON(View view){
        Intent intent4 = new Intent(conectaaBBDD.this,ConJSON.class);
        startActivity(intent4);

    }


}