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
import com.example.libros.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {
    private TextView datos;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.datos);
        datos=root.findViewById(R.id.datos);
        Libros();
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
                    datos.setText("Codigo:"+response.code());
                    return;
                }
                List<Libro> libroList=response.body();
                for( Libro libro: libroList)
                {
                    String content="";
                    content+="ID: "+ libro.getID()+"\n";
                    content+="title: "+ libro.getTitle()+"\n";
                    content+="author: "+ libro.getAuthor()+"\n";
                    content+="content: "+ libro.getContent()+"\n";
                    content+="content_short: "+ libro.getContent_short()+"\n";
                    content+="publisher: "+ libro.getPublisher()+"\n";
                    content+="publisher_date: "+ libro.getPublisher_date()+"\n";
                    content+="pages: "+ libro.getPages()+"\n";
                    content+="language: "+ libro.getLanguage()+"\n";
                    content+="url_details: "+ libro.getUrl_details()+"\n";
                    content+="url_download: "+ libro.getUrl_download()+"\n";
                    content+="cover: "+ libro.getCover()+"\n";
                    content+="thumbnail: "+ libro.getThumbnail()+"\n";
                    content+="num_comment: "+ libro.getNum_comments()+"\n\n";
                    datos.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                datos.setText(t.getMessage());
            }
        });
    }
}