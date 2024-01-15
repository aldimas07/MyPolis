package com.kelompok1.uas.MyPolis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profil extends AppCompatActivity {
    SqliteHelper mydb;
    TextView nama, keluar, tentang, bantuan;
    CircleImageView imageView;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = findViewById(R.id.txt_nama);

        mydb = new SqliteHelper(this);
        Cursor res = mydb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() <= 1) {
            while (res.moveToNext()) {
                stringBuffer.append("Nama       :  " + res.getString(1) + "\n\n");
                stringBuffer.append("NRP          :  " + res.getString(2) + "\n\n");
                stringBuffer.append("Pangkat   :  " + res.getString(3) + "\n\n");
                stringBuffer.append("Kesatuan :  " + res.getString(4) + "\n\n");
                stringBuffer.append("No. HP     :  " + res.getString(5) + "\n\n");
                stringBuffer.append("Alamat     :  " + res.getString(6) + "\n\n");
                stringBuffer.append("Email         :  " + res.getString(7) + "\n\n");

            }
            nama.setText(stringBuffer.toString());

        }

        imageView = (CircleImageView) findViewById(R.id.myPict);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(Profil.this);
            }
        });

        keluar = findViewById(R.id.txt_keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        tentang = findViewById(R.id.txt_tentang);
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profil.this, TentangActivity.class));
            }
        });

        bantuan = findViewById(R.id.txt_bantuan);
        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profil.this, BantuanActivity.class));
            }
        });

    }

    public void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Profil.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Profil.this, login.class));
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @SuppressLint("MissingSuperCall")
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                uri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        , 0);
            } else {
                startCrop(imageuri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(result.getUri());
                uri = result.getUri();
            }
        }
    }

    private void startCrop(Uri imageuri) {
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .start(this);
        uri = imageuri;
    }
}
