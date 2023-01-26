package com.example.filmoteca;

import com.google.gson.annotations.SerializedName;

public class Pelicula {

    @SerializedName("director")
    private String director;

    @SerializedName("id")
    private int id;

    @SerializedName("clasificacion")
    private String clasificacion;

    @SerializedName("nombre")
    private String nombre;


    public Pelicula(String director, int id, String clasificacion, String nombre) {
        this.director = director;
        this.id = id;
        this.clasificacion = clasificacion;
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
