package com.kelompok1.uas.MyPolis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class read extends AppCompatActivity {
    TextView Tv_id, Tv_Nama, Tv_Alamat, Tv_TempatLahir, Tv_Tanggal, Tv_pekerjaan, Tv_bukti, Tv_Penalti, Tv_tglpelanggar;
    TextView Tv_Nopol, Tv_JenisM;
    TextView Tv_pasal, Tv_denda;
    TextView Tv_lokasi;
    TextView kesatuan, nrpa, nama, kesatuanpolisi;
    long id;
    ImageView imageView;
    Uri uri;
    DBHelper helper;
    SqliteHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        helper = new DBHelper(this);

        kesatuan = findViewById(R.id.tv_kesatuan);
        nrpa = findViewById(R.id.tv_nrp);
        nama = findViewById(R.id.tv_namapolisi);
        kesatuanpolisi = findViewById(R.id.tv_kesatuanpolisi);

        mydb = new SqliteHelper(this);
        Cursor res = mydb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() <= 1) {
            while (res.moveToNext()) {
                stringBuffer.append(": " + res.getString(4));
            }
            kesatuan.setText(stringBuffer.toString());
            kesatuanpolisi.setText(stringBuffer.toString());
        }

        Cursor nrp = mydb.getAllData();
        StringBuffer s = new StringBuffer();
        if (nrp != null && nrp.getCount() <= 1) {
            while (nrp.moveToNext()) {
                s.append(": " + nrp.getString(2));

            }
            nrpa.setText(s.toString());
        }

        Cursor nam = mydb.getAllData();
        StringBuffer n = new StringBuffer();
        if (nam != null && nam.getCount() <= 1) {
            while (nam.moveToNext()) {
                n.append(": " + nam.getString(1));

            }
            nama.setText(n.toString());
        }

        id = getIntent().getLongExtra(DBHelper.row_id, 0);


        Tv_id = (TextView) findViewById(R.id.tv_id);
        Tv_Nama = (TextView) findViewById(R.id.tv_Nama);
        Tv_Alamat = (TextView) findViewById(R.id.tv_Alamat);
        Tv_TempatLahir = (TextView) findViewById(R.id.tv_TempatLahir);
        Tv_Tanggal = (TextView) findViewById(R.id.tv_Tanggal);
        Tv_pekerjaan = (TextView) findViewById(R.id.tv_pekerjaan);
        Tv_bukti = (TextView) findViewById(R.id.tv_bukti);
        Tv_Penalti = (TextView) findViewById(R.id.tv_penalti);
        Tv_tglpelanggar = (TextView) findViewById(R.id.tv_tglpelang);

        Tv_Nopol = (TextView) findViewById(R.id.tv_Nopol);
        Tv_JenisM = (TextView) findViewById(R.id.tv_Jenismo);

        Tv_pasal = (TextView) findViewById(R.id.tv_pasal);
        Tv_denda = (TextView) findViewById(R.id.tv_denda);
        Tv_lokasi = (TextView) findViewById(R.id.tv_lokasipelanggaran);

        imageView = (ImageView) findViewById(R.id.buktifoto_read);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(read.this);
            }
        });

        getData();
    }

    private void getData() {
        Cursor cursor = helper.oneData(id);
        if (cursor.moveToFirst()) {

            String id = (": D" + cursor.getString(cursor.getColumnIndex(DBHelper.row_id)));
            String nama = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_nama)));
            String alamat = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_alamat)));
            String tempatLahir = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_tempatLahir)));
            String tanggal = (cursor.getString(cursor.getColumnIndex(DBHelper.row_tglLahir)));
            String pekerjaan = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_pekerjaan)));
            String bukti = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_bukti)));
            String penalti = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_penalti)));
            String tglpelang = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_tglpelanggaran)));

            String nopol = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_nopol)));
            String jenism = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_jenisM)));

            String pasal = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_pelanggaran)));
            String denda = (": Rp. " + cursor.getString(cursor.getColumnIndex(DBHelper.row_denda)));
            String lokasi = (": " + cursor.getString(cursor.getColumnIndex(DBHelper.row_lokasipelanggaran)));
            String foto = cursor.getString(cursor.getColumnIndex(DBHelper.row_foto));

            Tv_id.setText(id);
            Tv_Nama.setText(nama);
            Tv_Alamat.setText(alamat);
            Tv_TempatLahir.setText(tempatLahir);
            Tv_Tanggal.setText(tanggal);
            Tv_pekerjaan.setText(pekerjaan);
            Tv_bukti.setText(bukti);
            Tv_Penalti.setText(penalti);
            Tv_tglpelanggar.setText(tglpelang);

            Tv_Nopol.setText(nopol);
            Tv_JenisM.setText(jenism);

            Tv_pasal.setText(pasal);
            Tv_denda.setText(denda);
            Tv_lokasi.setText(lokasi);

            if (foto.equals("null")) {
                imageView.setImageResource(R.drawable.ic_person_black_24dp);
            } else {
                imageView.setImageURI(Uri.parse(foto));
            }
        }
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
