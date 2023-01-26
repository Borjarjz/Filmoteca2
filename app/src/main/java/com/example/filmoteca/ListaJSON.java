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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaJSON extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Pelicula> listaPeliculas;
    ArrayAdapter<Pelicula> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_json);

        Intent intent = getIntent();
       // String direccion=intent.getStringExtra("direccion");
        String direccion="http://192.168.1.9:8080";
        //Toast.makeText(getApplicationContext(), direccion, Toast.LENGTH_SHORT).show();


        listView = (ListView) findViewById(R.id.listView);
        listaPeliculas = new ArrayList<Pelicula>();
        adapter= new ArrayAdapter<>(ListaJSON.this, android.R.layout.simple_list_item_1, listaPeliculas);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direccion)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI myAPI = retrofit.create(MyAPI.class);




        for (int i=0; i<7.; i++){


            Call<ResponseBody> call = myAPI.getData(i);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(ListaJSON.this, "hola", Toast.LENGTH_SHORT).show();
                    if(response.isSuccessful()) {
                        try {
                            JSONObject json = new JSONObject(response.body().string());

                            Pelicula pelicula = new Pelicula(json.getString("director"), Integer.parseInt(json.getString("id")), json.getString("clasificacion"), json.getString("nombre"));

                            listaPeliculas.add(pelicula);


                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(ListaJSON.this, "response no hya", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // manejar el error
                }
            });

           /* if(listaPeliculas.isEmpty()){
                Toast.makeText(this, "el array esta vacio", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "el array tiene cositas", Toast.LENGTH_SHORT).show();
            }*/



        }





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se crea el menú que queramos para nuestra activity (inflate)
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;


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
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}