package com.example.filmoteca;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.filmoteca.ui.main.SectionsPagerAdapter;
import com.example.filmoteca.databinding.ActivityDetallePelicula2Binding;

public class DetallePelicula extends AppCompatActivity {



    Pelicula pelicula;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle_pelicula2);
        pelicula = (Pelicula) getIntent().getSerializableExtra("peliculaSeleccionada");

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("Info"));
        tabs.addTab(tabs.newTab().setText("Videoclubs"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.view_pager);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(tabs.getContext(), getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);


        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        sectionsPagerAdapter.addFragment(Info.newInstance(pelicula.getNombre(), pelicula.getClasificacion(), pelicula.getDirector()), "Info");
        sectionsPagerAdapter.addFragment(new Videoclubs(), "Videoclubs");





    }

    public void imdb(View view) {
        String query = pelicula.getNombre();
        String url = "https://www.imdb.com/find?q=" + query + "&s=all";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    }
