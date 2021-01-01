package com.gzeinnumer.simpletakefotofromgalerymylibdirectory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.gzeinnumer.gzndirectory.helper.FGFile;
import com.gzeinnumer.gzndirectory.helper.imagePicker.FileCompressor;
import com.gzeinnumer.simpletakefotofromgalerymylibdirectory.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //todo 15 initOnClick
        initOnClick();

    }

    //todo 16
    static final int REQUEST_GALLERY_PHOTO = 3;
    File mPhotoFile;
    FileCompressor mCompressor;

    private void initOnClick() {
        //todo 17
        binding.btnGalery.setOnClickListener(v -> {
            dispatchGalleryIntent();
        });
    }

    //todo 18
    private void dispatchGalleryIntent() {
        mCompressor = new FileCompressor(this);
        // int quality = 50;
        // mCompressor = new FileCompressor(this, quality);

        mCompressor.setDestinationDirectoryPath("/Foto");

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);
    }

    //todo 19
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_PHOTO) {
                Uri selectedImage = data.getData();
                try {
                    mPhotoFile = mCompressor.compressToFile(selectedImage);
                    Glide.with(MainActivity.this).load(mPhotoFile).into(binding.img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}