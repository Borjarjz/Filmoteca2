package com.example.filmoteca;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.filmoteca.ui.main.SectionsPagerAdapter;
import com.example.filmoteca.databinding.ActivityDetallePelicula2Binding;

public class DetallePelicula extends AppCompatActivity {



    Pelicula pelicula;
ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       pelicula = (Pelicula) getIntent().getSerializableExtra("peliculaSeleccionada");


        setContentView(R.layout.activity_detalle_pelicula2);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
        sectionsPagerAdapter.addFragment(new Info(), "Info");
        sectionsPagerAdapter.addFragment(new Videoclubs(), "Videoclubs");



    }


    }
