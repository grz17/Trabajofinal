package com.example.libros;

import java.util.Vector;

public class Personas {
    private  String usario;
    private  String contraseña;

    public String getUsario() {
        return usario;
    }

    public void setUsario(String usario) {
        this.usario = usario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public  static Vector mostrar()
    {
        return ListaUsuario.consulta();
    }
    public  static int  verificar(String usuario)
    {
        Vector lista = mostrar();
        Personas  persona;
        for (int i=0;i<lista.size();i++)
        {
            persona=(Personas)lista.elementAt(i);
            if (persona.getUsario().equalsIgnoreCase(usuario))
            {
                return  i;
            }
        }
        return  -1;
    }

    public  static  int datoscorrectos(String usario,String contraseña)
    {
        Vector lista= mostrar();
        Personas personas;
        for (int i=0;i<lista.size();i++)
        {
            personas=(Personas)lista.elementAt(i);
            if (personas.getUsario().equalsIgnoreCase(usario) && personas.getContraseña().equalsIgnoreCase(contraseña))
            {
                return  i;
            }
        }
        return -1;
    }
}
