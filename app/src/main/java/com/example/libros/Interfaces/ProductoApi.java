package com.example.libros.Interfaces;

import com.example.libros.Modelos.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {
    @GET("?category=programacion")
    Call<List<Libro>> getLibro();
}
