package com.example.filmoteca;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmoteca.ui.main.SectionsPagerAdapter;
import com.example.filmoteca.databinding.ActivityDetallePelicula2Binding;

public class DetallePelicula extends AppCompatActivity {

    private ActivityDetallePelicula2Binding binding;

    Pelicula pelicula;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       pelicula = (Pelicula) getIntent().getSerializableExtra("peliculaSeleccionada");



        Bundle bundle = new Bundle();
        bundle.putString("nombre", pelicula.getNombre());
        bundle.putString("clasif", pelicula.getClasificacion());
        bundle.putString("dir", pelicula.getDirector());

        Info fragment = new Info();

        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.view_pager, fragment).commit();




        binding = ActivityDetallePelicula2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);





    }


    }
