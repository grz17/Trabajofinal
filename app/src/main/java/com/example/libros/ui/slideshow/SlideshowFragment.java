package com.example.libros.ui.slideshow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.libros.R;

public class SlideshowFragment extends Fragment {
    private Button btnTelefono;
    private TextView tel;
    private final int PHONE_CALL_CODE = 100;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        tel=(TextView)root.findViewById(R.id.textView4);
        tel.setText("5527832023");
        btnTelefono=(Button)root.findViewById(R.id.btnLlamarGabys);
        btnTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = tel.getText().toString();
                if(numero !=null){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE);
                    }else{
                        versionesAnteriores(numero);
                    }
                }
            }
            private void versionesAnteriores (String numero)
            {
                Intent MainActivity = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
                if (verificarPermisos(Manifest.permission.CALL_PHONE)){
                    startActivity(MainActivity);
                }else{
                    Toast.makeText(getActivity(), "Configura los permisos", Toast.LENGTH_SHORT).show();
                }

            }
        });


        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if  (permission.equals(Manifest.permission.CALL_PHONE))
                {
                    if (result== PackageManager.PERMISSION_GRANTED){
                        String NumeroTelefono=tel.getText().toString();
                        Intent llamada=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+NumeroTelefono));
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)return;
                        startActivity(llamada);
                    }
                    else{
                        Toast.makeText(getActivity(), "No aceptaste el permiso", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }


    }

    private boolean verificarPermisos (String permiso)
    {
        int resultado = getActivity().checkCallingOrSelfPermission(permiso);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }



}