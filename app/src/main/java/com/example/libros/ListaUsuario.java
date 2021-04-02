package com.example.libros;

import java.util.Vector;

public class ListaUsuario {

    private static Vector<Personas> datos = new Vector<Personas>();
    public  static   void agregar(Personas nueva)
    {
        datos.addElement(nueva);
    }
    public  static Vector  consulta()
    {
        return  datos;
    }
}

