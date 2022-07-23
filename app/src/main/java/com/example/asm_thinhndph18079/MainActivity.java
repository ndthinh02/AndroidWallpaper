package com.example.asm_thinhndph18079;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asm_thinhndph18079.adapter.PhotoAdapter;
import com.example.asm_thinhndph18079.api.RetrofitInstance;
import com.example.asm_thinhndph18079.model.ParentPhoto;
import com.example.asm_thinhndph18079.model.Photo;
import com.example.asm_thinhndph18079.model.Photo;
import com.example.asm_thinhndph18079.model.Photos;
import com.example.asm_thinhndph18079.onClick.OnClickPhoto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<Photo> mListPhoto;
    private PhotoAdapter photoAdapter;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    int page = 1;
    int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
//        progressBar = findViewById(R.id.prb);
        nestedScrollView = findViewById(R.id.scrollView);
        mListPhoto = new ArrayList<>();
        photoAdapter = new PhotoAdapter(mListPhoto, MainActivity.this, new OnClickPhoto() {
            @Override
            public void onClickShowPhoto(Photo photo) {
                showDetail(photo);
            }
        });
        callApiGetPhoto();
    }

    private void callApiGetPhoto() {
        Call<ParentPhoto> call = RetrofitInstance.getPhoto().callApiPhoto();
        call.enqueue(new Callback<ParentPhoto>() {
            @Override
            public void onResponse(Call<ParentPhoto> call, Response<ParentPhoto> response) {
                ParentPhoto parentPhoto = response.body();
                mListPhoto = parentPhoto.getPhotos().getPhoto();
                photoAdapter.setData(mListPhoto);
                rv.setAdapter(photoAdapter);
            }

            @Override
            public void onFailure(Call<ParentPhoto> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                Log.d("abcc", t + "");
            }
        });
    }


    private void showDetail(Photo photo) {
        Intent intent = new Intent(this, ShowPhotoActivity.class);
        intent.putExtra("show_img", photo);
        startActivity(intent);
    }
}