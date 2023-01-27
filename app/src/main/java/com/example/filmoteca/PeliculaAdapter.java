package com.example.filmoteca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PeliculaAdapter extends ArrayAdapter<Pelicula> {
    private Context mContext;
    private List<Pelicula> mPeliculas;

    public PeliculaAdapter(@NonNull Context context, @NonNull List<Pelicula> peliculas) {
        super(context, 0, peliculas);
        mContext = context;
        mPeliculas = peliculas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Pelicula currentPelicula = mPeliculas.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.nombrePelicula);
        name.setText(currentPelicula.getNombre());

        return listItem;
    }
}