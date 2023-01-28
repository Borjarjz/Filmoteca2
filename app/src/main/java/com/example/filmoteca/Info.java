package com.example.filmoteca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Info extends Fragment {
  //  public Pelicula pelicula;
public String nom;
    public String clas;
    public String dir;



    public Info() {
        // Required empty public constructor
    }

    public static Info newInstance(String nombre, String clasificacion, String director) {
        Info fragment = new Info();
        Bundle args = new Bundle();
        args.putString("nombre", nombre);
        args.putString("clasif", clasificacion);
        args.putString("dir", director);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            //pelicula = (Pelicula) getArguments().getSerializable("peliculaSeleccionada");
            nom=(String) getArguments().getString("nombre");
            clas=(String) getArguments().getString("clasif");
            dir=(String) getArguments().getString("dir");



        }else  {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        TextView textView11 = view.findViewById(R.id.textView11);
        TextView textView12 = view.findViewById(R.id.textView12);
        TextView textView13 = view.findViewById(R.id.textView13);

        textView11.setText(nom);
        textView12.setText(clas);
        textView13.setText(dir);


        return view;
    }
}