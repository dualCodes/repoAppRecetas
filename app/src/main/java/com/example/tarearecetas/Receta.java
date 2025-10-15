
package com.example.tarearecetas;

import java.util.List;

public class Receta {
    private String nombre;
    private String categoria;
    private String dificultad;
    private int tiempo;
    private List<String> ingredientes;

    public Receta(String nombre, String categoria, String dificultad, int tiempo, List<String> ingredientes) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.dificultad = dificultad;
        this.tiempo = tiempo;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
