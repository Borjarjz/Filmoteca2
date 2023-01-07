package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class editor extends AppCompatActivity {

    String titulo;
    EditText tit;
    EditText gen;
    EditText sin;
    EditText dir;
    CheckBox vis;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference raiz = database.getReference("PELICULAS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Intent intent = getIntent();



        tit=(EditText)findViewById(R.id.entradatitulo);
        tit.setText(intent.getStringExtra("TITULO"));
        titulo=intent.getStringExtra("TITULO");

        gen=(EditText)findViewById(R.id.entradagenero);
        gen.setText(intent.getStringExtra("GENERO"));

        sin=(EditText)findViewById(R.id.entradasinopsis);
        sin.setText(intent.getStringExtra("SINOPSIS"));

        dir=(EditText)findViewById(R.id.entradadirector);
        dir.setText(intent.getStringExtra("DIRECTOR"));


        vis=findViewById(R.id.entradavista);
        if(intent.getStringExtra("VISTA")=="true"){
            vis.setChecked(true);
        }else{
            vis.setChecked(false);
        }


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
                Toast.makeText(editor.this,"ha pulsado idioma",Toast.LENGTH_SHORT).show();
                return true;


            case R.id.acercade:
                Toast.makeText(editor.this,"ha pulsado acerca de",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
//hasta aqui se crea el menu
public void guardaCambios(){

    Query query =raiz.orderByChild("Titulo").equalTo(titulo);
    //raiz.orderByChild("Titulo").equalTo(pelipulsada)
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            //String value = snapshot.getValue(String.class);



            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                snapshot.child("Titulo").getRef().setValue(tit.getText());
                snapshot.child("Genero").getRef().setValue(gen.getText());
                snapshot.child("Sinopsis").getRef().setValue(sin.getText());
                snapshot.child("Director").getRef().setValue(dir.getText());
                if(vis.isChecked()){
                    snapshot.child("vista").getRef().setValue(true);
                }else{
                    snapshot.child("vista").getRef().setValue(false);
                }
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Failed to read value

        }
    });
}


}