package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.net.HttpCookie;
import java.util.ArrayList;

public class PelisListView extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> names;

    //database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference raiz = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelis_list_view);

        raiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = snapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });


                //EMPIEZA LISTVIEW
                listview = (ListView) findViewById(R.id.listviewdepelis);


                names = new ArrayList<String>();
                names.add(raiz.child("peli1").child("Titulo").toString());
                names.add(String.valueOf(raiz.child("peli2").child("Titulo")));
                names.add(raiz.child("peli3").child("Titulo").get().toString());
                names.add(raiz.child("peli4").get().toString());
                names.add(raiz.child("peli5").toString());
                names.add(raiz.child("peli6").child("Titulo").getKey());


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
                listview.setAdapter(adapter);

                //fin listview
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
                Toast.makeText(PelisListView.this,"ha pulsado idioma",Toast.LENGTH_SHORT).show();
                return true;


            case R.id.acercade:
                Toast.makeText(PelisListView.this,"ha pulsado acerca de",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
//hasta aqui se crea el menu
}