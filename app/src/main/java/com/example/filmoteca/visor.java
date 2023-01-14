package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class visor extends AppCompatActivity {

    //variables de bbdd
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference raiz = database.getReference("PELICULAS");
    String pelipulsada=null;

//declaraciuon de variables
    TextView tit;
    TextView gen;
    TextView sin;
    TextView dir;
    TextView vis;

    String titulo;
    String genero;
    String sinopsis;
    String director;
    String vista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);


//se obtiene el intent de la actividad anterior y un string con el titulo de la pelicula pulsada
        Intent intent = getIntent();
        pelipulsada = intent.getStringExtra("MESSAGE");








        Query query =raiz.orderByChild("Titulo").equalTo(pelipulsada);//se hace la query para obtener la pelicula pulsada en la actividad anterior



//se asigna a cada variable textview del layout las variables locales
        tit=(TextView)findViewById(R.id.titulomostrar);
        gen=(TextView)findViewById(R.id.generomostrar);
        sin=(TextView)findViewById(R.id.sinopsismostrar);
        dir=(TextView)findViewById(R.id.directormostrar);
        vis=(TextView)findViewById(R.id.vistamostrar);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {//se recorre el snapshot de la query en busca de la pelicula pulsada


                    //asignamos los datos de la BBDD en las variables locales textview
                    tit.setText(snapshot.child("Titulo").getValue(String.class));
                    titulo=snapshot.child("Titulo").getValue(String.class);

                    gen.setText(snapshot.child("Genero").getValue(String.class));
                    genero=snapshot.child("Genero").getValue(String.class);
                    sin.setText(snapshot.child("Sinopsis").getValue(String.class));
                    sinopsis=snapshot.child("Sinopsis").getValue(String.class);
                    dir.setText(snapshot.child("Director").getValue(String.class));
                    director=snapshot.child("Director").getValue(String.class);
                    vis.setText(snapshot.child("vista").getValue(Boolean.class).toString());
                    vista=snapshot.child("vista").getValue(Boolean.class).toString();

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });




    }





    //Creacion de menu

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


                //se crea el case para el boton de editar, que nos llevará a la activity editar
            case R.id.editpeli:
                Toast.makeText(visor.this,"ha pulsado editar",Toast.LENGTH_SHORT).show();
                irActivityeditor();
                return true;


                //se crea el case para el boton de borrar, que borrará la película que estámos viendo en el visor
            case R.id.borrarpeli:
                borrarlapeli(pelipulsada);
                Toast.makeText(visor.this,"se ha borrado la entrada: "+pelipulsada,Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(visor.this,PelisListView.class);
                startActivity(intent4);
                return true;


                //se crea el case para el boton buscar en google, que creará iun intent y abrira una busqueda en google de la pelicula mostrada
            case R.id.buscagoogle:
                Intent intent3 = new Intent(Intent.ACTION_WEB_SEARCH);
                intent3.putExtra(SearchManager.QUERY, pelipulsada);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
//hasta aqui se crea el menu





    public void onConfigurationChanged(Configuration newConfig) {//se crea el metodo para que se actualice al cambiar la orientacion
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        startActivity(getIntent());
        finish();

    }

    public void irActivityeditor() {//método que lanzara la actividad editor con un intent, se le pasa los string de los datos de la pelicula
        Intent intent2 = new Intent(visor.this,editor.class);
        intent2.putExtra("TITULO", titulo);
        intent2.putExtra("GENERO", genero);
        intent2.putExtra("SINOPSIS", sinopsis);
        intent2.putExtra("DIRECTOR", director);
        intent2.putExtra("VISTA", vista);
        startActivity(intent2);

    }

    public void borrarlapeli(String peliaborrar){//metodo que borra de la BBDD la pelicula que estemos viendo en el visor
        Query query =raiz.orderByChild("Titulo").equalTo(peliaborrar);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    snapshot.getRef().removeValue();



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });



    }
}