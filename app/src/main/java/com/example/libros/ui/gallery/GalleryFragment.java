package com.example.libros.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.libros.Interfaces.ProductoApi;
import com.example.libros.Modelos.Libro;
import com.example.libros.Modelos.Libro2;
import com.example.libros.Modelos.Libro3;
import com.example.libros.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {
    private TextView nombre1;
    private TextView autor1;
    private TextView descarga1;
    private TextView nombre2;
    private TextView autor2;
    private TextView descarga2;
    private TextView nombre3;
    private TextView autor3;
    private TextView desccarga3;


    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.nombre1);
        nombre1=(TextView)root.findViewById(R.id.nombre1);
        nombre2=(TextView)root.findViewById(R.id.nombre2);
        nombre3=(TextView)root.findViewById(R.id.nombre3);
        autor1=(TextView)root.findViewById(R.id.autor1);
        autor2=(TextView)root.findViewById(R.id.autor2);
        autor3=(TextView)root.findViewById(R.id.autor3);
        descarga1=(TextView)root.findViewById(R.id.descarga1);
        descarga2=(TextView)root.findViewById(R.id.descarga2);
        desccarga3=(TextView)root.findViewById(R.id.descarga3);

        Libros();
        Libros2();
        Libros3();
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private  void Libros()
    {
        Retrofit retrofit= new  Retrofit.Builder()
                .baseUrl("https://www.etnassoft.com/api/v1/get/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoApi productoApi = retrofit.create(ProductoApi.class);
        Call<List<Libro>> call=productoApi.getLibro();
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(!response.isSuccessful())
                {
                    nombre1.setText("Codigo:"+response.code());
                    autor1.setText("Codigo:"+response.code());
                    descarga1.setText("Codigo:"+response.code());
                    return;
                }
                List<Libro> libroList=response.body();
                for( Libro libro: libroList)
                {
                  nombre1.setText(libro.getTitle());
                  autor1.setText(libro.getAuthor());
                  descarga1.setText(libro.getUrl_download());
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                nombre1.setText(t.getMessage());
            }
        });
    }
    private  void Libros2()
    {
        Retrofit retrofit= new  Retrofit.Builder()
                .baseUrl("https://www.etnassoft.com/api/v1/get/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoApi productoApi = retrofit.create(ProductoApi.class);
        Call<List<Libro2>> call=productoApi.getLibro2();
        call.enqueue(new Callback<List<Libro2>>() {
            @Override
            public void onResponse(Call<List<Libro2>> call, Response<List<Libro2>> response) {
                if(!response.isSuccessful())
                {
                    nombre2.setText("Codigo:"+response.code());
                    autor2.setText("Codigo:"+response.code());
                    descarga2.setText("Codigo:"+response.code());
                    return;
                }
                List<Libro2> libroList=response.body();
                for( Libro2 libro: libroList)
                {
                    nombre2.setText(libro.getTitle());
                    autor2.setText(libro.getAuthor());
                    descarga2.setText(libro.getUrl_download());
                }
            }

            @Override
            public void onFailure(Call<List<Libro2>> call, Throwable t) {
                nombre2.setText(t.getMessage());
            }
        });
    }
    private  void Libros3()
    {
        Retrofit retrofit= new  Retrofit.Builder()
                .baseUrl("https://www.etnassoft.com/api/v1/get/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoApi productoApi = retrofit.create(ProductoApi.class);
        Call<List<Libro3>> call=productoApi.getLibro3();
        call.enqueue(new Callback<List<Libro3>>() {
            @Override
            public void onResponse(Call<List<Libro3>> call, Response<List<Libro3>> response) {
                if(!response.isSuccessful())
                {
                    nombre3.setText("Codigo:"+response.code());
                    autor3.setText("Codigo:"+response.code());
                    desccarga3.setText("Codigo:"+response.code());
                    return;
                }
                List<Libro3> libroList=response.body();
                for( Libro3 libro: libroList)
                {
                    nombre3.setText(libro.getTitle());
                    autor3.setText(libro.getAuthor());
                    desccarga3.setText(libro.getUrl_download());
                }
            }

            @Override
            public void onFailure(Call<List<Libro3>> call, Throwable t) {
                nombre3.setText(t.getMessage());
            }
        });
    }
}