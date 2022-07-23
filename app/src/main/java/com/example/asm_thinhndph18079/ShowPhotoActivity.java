package com.example.asm_thinhndph18079;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asm_thinhndph18079.model.Photo;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ShowPhotoActivity extends AppCompatActivity {
    private ImageView img;
    private EditText editText;
    private LinearLayout linearLayout;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        img = findViewById(R.id.img);
        linearLayout = findViewById(R.id.cardView);
        showImage();
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog();
                return false;
            }
        });

    }

    private void showImage() {
        Photo photo = (Photo) getIntent().getSerializableExtra("show_img");
        Glide.with(this).load(photo.getUrlC()).placeholder(R.drawable.loading).into(img);
    }

    private void showDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_dialog);
        Button btnSave = bottomSheetDialog.findViewById(R.id.btnSave);
        Button btnReport = bottomSheetDialog.findViewById(R.id.btnReport);
        btnSave.setOnClickListener(view -> {
            downloadImage();
            bottomSheetDialog.dismiss();
        });
        btnReport.setOnClickListener(view -> {
            Toast.makeText(ShowPhotoActivity.this, "Reported !", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }

    private void downloadImage() {
        Photo photo = (Photo) getIntent().getSerializableExtra("show_img");
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(photo.getUrlC());
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Download");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, String.valueOf(System.currentTimeMillis()));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
            Toast.makeText(ShowPhotoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}