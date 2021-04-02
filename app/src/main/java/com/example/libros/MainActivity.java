package com.example.libros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.buttonRegistrar);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent next2 = new Intent(MainActivity.this,RegistrarUsuario.class);
                startActivity(next2);
            }
        });

        Button btn2 = (Button)findViewById(R.id.btnIniciarSesion);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usuari, con;
                EditText usu = (EditText) findViewById(R.id.editTextNombreUsuarioS);
                EditText contra = (EditText) findViewById(R.id.editTextContraseñaUsuarioS);
                usuari = usu.getText().toString();
                con = contra.getText().toString();
                int pos = Personas.datoscorrectos(usuari,con);
                if(pos==-1)
                {
                    dialogo2();
                }
                else {
                    Intent next2 = new Intent(MainActivity.this, Libreria.class);
                    startActivity(next2);
                    usu.setText("");
                    contra.setText("");
                }
            }
        });
    }
    public AlertDialog dialogo2()
    {
        AlertDialog.Builder  builder= new AlertDialog.Builder(this);
        builder.setTitle("Datos eroneos");
        builder.setMessage("Usuario O contraseña incorrecto");
        builder.setNegativeButton("ok",null);
        builder.create();
        return builder.show();
    }
}