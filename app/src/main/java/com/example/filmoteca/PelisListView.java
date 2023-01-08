package com.example.filmoteca;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PelisListView extends AppCompatActivity {
    private ListView listview;

    private String pelipulsada=null;

    //database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference raiz = database.getReference("PELICULAS");
    final ArrayList<String> names = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelis_list_view);

    Query query =raiz.orderByChild("Titulo");





        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = snapshot.getValue(String.class);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String tit = snapshot.child("Titulo").getValue(String.class);
                    names.add(tit);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PelisListView.this, android.R.layout.simple_list_item_1, names);
                listview = (ListView) findViewById(R.id.listviewdepelis);
                listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {//se crea el click listener de la listview
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // Obtener el elemento pulsado
                                String item = (String) parent.getItemAtPosition(position);
                                Toast.makeText(PelisListView.this,"ha pulsado "+item,Toast.LENGTH_SHORT).show();
                                pelipulsada=item;
                                //se crea el intent con la nueva actividad y se le pasa la peliplsada para rescatar esos datos de la BBDD
                                Intent intent = new Intent(PelisListView.this, visor.class);
                                intent.putExtra("MESSAGE", pelipulsada);
                                startActivity(intent);


                            }
                        });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });

/*
             ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
                listview = (ListView) findViewById(R.id.listviewdepelis);
                listview.setAdapter(adapter);
*/




    }



    //esto es menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se crea el menú que queramos para nuestra activity (inflate)
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
        // return super.onCreateOptionsMenu(menu);

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//se hace el CASE para cada opción del menu
        switch(item.getItemId()){
            case R.id.idioma:
                Toast.makeText(PelisListView.this,"ha pulsado idioma",Toast.LENGTH_SHORT).show();
                return true;


            case R.id.acercade:
                Toast.makeText(PelisListView.this,"ha pulsado acerca de",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.anyadirpeli:
                Intent intent3 = new Intent(PelisListView.this,IntroducePeli.class);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }




//hasta aqui se crea el menu
}