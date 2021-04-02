package com.example.libros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        Button agregar =(Button) findViewById(R.id.buttonRegistarUsuario);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Usu,con;
                EditText usuario=(EditText) findViewById(R.id.editTextNombreUsuarioR);
                EditText correo=(EditText) findViewById(R.id.editTextCorreoR);
                EditText contraseña=(EditText) findViewById(R.id.editTextContraseñaR);
                EditText rcontraseña=(EditText) findViewById(R.id.editTextRepetirContraseña);
                Usu=usuario.getText().toString();
                con=contraseña.getText().toString();
                Personas nuevo = new Personas();
                if (usuario.getText().toString().isEmpty()||correo.getText().toString().isEmpty()||contraseña.getText().toString().isEmpty()||rcontraseña.getText().toString().isEmpty())
                {
                    dialogo();
                }
                else
                if (Personas.verificar(Usu)==-1) {
                    nuevo.setUsario(Usu);
                    nuevo.setContraseña(con);
                    ListaUsuario.agregar(nuevo);
                    dialogo2();
                    usuario.setText("");
                    rcontraseña.setText("");
                    correo.setText("");
                    contraseña.setText("");
                }else {
                    dialogo3();
                    usuario.setText("");
                }

            }
        });
    }
    public AlertDialog dialogo()
    {
        AlertDialog.Builder  builder= new AlertDialog.Builder(this);
        builder.setTitle("Falta De Datos");
        builder.setMessage("ingrese datos");
        builder.setNegativeButton("ok",null);
        builder.create();
        return builder.show();
    }

    public  AlertDialog dialogo2()
    {
        AlertDialog.Builder  builder= new AlertDialog.Builder(this);
        builder.setTitle("Usuarios");
        builder.setMessage("Usuario Guardado");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent nuevo = new Intent(RegistrarUsuario.this,MainActivity.class);
                startActivity(nuevo);
            }
        });
        builder.create();
        return builder.show();
    }
    public  AlertDialog dialogo3()
    {
        AlertDialog.Builder  builder= new AlertDialog.Builder(this);
        builder.setTitle("Usuarios");
        builder.setMessage("Usuario Existente Ingrese otro usuario");
        builder.setNegativeButton("ok",null);
        builder.create();
        return builder.show();
    }
}