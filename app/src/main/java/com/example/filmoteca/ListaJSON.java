package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaJSON extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Pelicula> listaPeliculas;
   PeliculaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_json);

        Intent intent = getIntent();
       // String direccion=intent.getStringExtra("direccion");
        String direccion="http://192.168.1.9:8080";



        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(onItemClickListener);
        listaPeliculas = new ArrayList<Pelicula>();

        adapter = new PeliculaAdapter(this, listaPeliculas);

        listView.setAdapter(adapter);

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
                    if(response.isSuccessful()) {
                        try {
                            String json = response.body().string();
                            JSONObject jsonObject = new JSONObject(json);

                            Pelicula pelicula = new Pelicula(jsonObject.getString("director"), Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("clasificacion"), jsonObject.getString("nombre"));

                            listaPeliculas.add(pelicula);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(ListaJSON.this, "response no hya", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // Toast.makeText(ListaJSON.this, "ha habido un error", Toast.LENGTH_SHORT).show();
                }
            });
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


    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            // Aquí va el código que se ejecutará al hacer clic en un elemento de la lista
            Pelicula peliculaSeleccionada = listaPeliculas.get(position);

            // aqui puedes navegar a otra activity
            Intent intent2 = new Intent(ListaJSON.this, DetallePelicula.class);
            intent2.putExtra("peliculaSeleccionada", peliculaSeleccionada);
            startActivity(intent2);
        }
    };



}
