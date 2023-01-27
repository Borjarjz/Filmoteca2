package com.example.filmoteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

public class DetallePelicula extends AppCompatActivity {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        viewPager = (ViewPager2) findViewById(R.id.viewpager);
        pagerAdapter=new com.example.filmoteca.PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new Info(), "Info");
        pagerAdapter.addFragment(new Videoclubs(), "Videoclubs");
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}