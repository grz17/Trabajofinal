package com.example.libros.Interfaces;

import com.example.libros.Modelos.Libro;
import com.example.libros.Modelos.Libro2;
import com.example.libros.Modelos.Libro3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {
    @GET("?id=17068")
    Call<List<Libro>> getLibro();

    @GET("?category=programacion")
    Call<List<Libro2>> getLibro2();

    @GET("?category=programacion")
    Call<List<Libro3>> getLibro3();
}
