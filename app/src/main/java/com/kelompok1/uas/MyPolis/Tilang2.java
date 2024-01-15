package com.kelompok1.uas.MyPolis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Tilang2 extends AppCompatActivity implements LocationListener {
    DBHelper helper;
    EditText txtglpelanggar, txdenda;
    Spinner txslip, txpenalti, txbukti, txpelanggaran;
    TextView nama1, alamt1, jk1, tmpatlahir1, tgllahir1, pekerjaan1, nopol1, jm1;
    TextView lokasimaps;
    long id;
    Button button;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    ImageView imageView;

    TextView lokasi;
    LocationManager locationManager;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilang2);
        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        Intent intent = getIntent();
        String nama = intent.getStringExtra(Tilang.EXTRA_NAMA);
        String alamt = intent.getStringExtra(Tilang.EXTRA_ALAMAT);
        String jk = intent.getStringExtra(Tilang.EXTRA_JK);
        String tmpatlahir = intent.getStringExtra(Tilang.EXTRA_TEMPATLAHIR);
        String tgllahir = intent.getStringExtra(Tilang.EXTRA_TGLLAHIR);
        String pekerjaan = intent.getStringExtra(Tilang.EXTRA_PEKERJAAN);
        String nopol = intent.getStringExtra(Tilang.EXTRA_NOPOL);
        String jm = intent.getStringExtra(Tilang.EXTRA_JENIS);


        nama1 = (TextView) findViewById(R.id.textnama);
        alamt1 = (TextView) findViewById(R.id.textalamat);
        jk1 = (TextView) findViewById(R.id.textjk);
        tmpatlahir1 = (TextView) findViewById(R.id.texttempatla);
        tgllahir1 = (TextView) findViewById(R.id.texttgllahir);
        pekerjaan1 = (TextView) findViewById(R.id.textpekerjaan);
        nopol1 = (TextView) findViewById(R.id.textnopol);
        jm1 = (TextView) findViewById(R.id.textjm);


        nama1.setText(nama);
        alamt1.setText(alamt);
        jk1.setText(jk);
        tmpatlahir1.setText(tmpatlahir);
        tgllahir1.setText(tgllahir);
        pekerjaan1.setText(pekerjaan);
        nopol1.setText(nopol);
        jm1.setText(jm);


        txtglpelanggar = (EditText) findViewById(R.id.txTglpelanggaran_Add);
        txdenda = (EditText) findViewById(R.id.txDenda_Add);
        txslip = (Spinner) findViewById(R.id.spslip_Add);
        txpenalti = (Spinner) findViewById(R.id.spPenalti_Add);
        txbukti = (Spinner) findViewById(R.id.spbukti_Add);
        txpelanggaran = (Spinner) findViewById(R.id.spPelanggaran_Add);
        imageView = (ImageView) findViewById(R.id.buktifoto_add);
        lokasimaps = (TextView) findViewById(R.id.txlokasip);
        button = (Button) findViewById(R.id.next2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clickme();
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        txtglpelanggar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        //image
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(Tilang2.this);
            }
        });

        getLocation();

        //maps

        if (ActivityCompat.checkSelfPermission(Tilang2.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(Tilang2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    public void maps(View view) {
        startActivity(new Intent(Tilang2.this, Tilang3maps.class));
    }

    //tanggal
    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                txtglpelanggar.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    //create ke db
    public void Clickme() {
        String Nama = nama1.getText().toString().trim();
        String alamat = alamt1.getText().toString().trim();
        String jk = jk1.getText().toString().trim();
        String tempatl = tmpatlahir1.getText().toString().trim();
        String tgll = tgllahir1.getText().toString().trim();
        String pekerjaan = pekerjaan1.getText().toString().trim();
        String nopol = nopol1.getText().toString().trim();
        String jenism = jm1.getText().toString().trim();

        String denda = txdenda.getText().toString().trim();
        String tglpelanggaran = txtglpelanggar.getText().toString().trim();
        String slip = txslip.getSelectedItem().toString().trim();
        String penalti = txpenalti.getSelectedItem().toString().trim();
        String bukti = txbukti.getSelectedItem().toString().trim();
        String pelanggaran = txpelanggaran.getSelectedItem().toString().trim();
        String lokasi = lokasimaps.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DBHelper.row_nama, Nama);
        values.put(DBHelper.row_alamat, alamat);
        values.put(DBHelper.row_jk, jk);
        values.put(DBHelper.row_tempatLahir, tempatl);
        values.put(DBHelper.row_tglLahir, tgll);
        values.put(DBHelper.row_pekerjaan, pekerjaan);
        values.put(DBHelper.row_nopol, nopol);
        values.put(DBHelper.row_jenisM, jenism);

        values.put(DBHelper.row_denda, denda);
        values.put(DBHelper.row_tglpelanggaran, tglpelanggaran);
        values.put(DBHelper.row_slip, slip);
        values.put(DBHelper.row_penalti, penalti);
        values.put(DBHelper.row_bukti, bukti);
        values.put(DBHelper.row_pelanggaran, pelanggaran);
        values.put(DBHelper.row_lokasipelanggaran, lokasi);
        values.put(DBHelper.row_foto, String.valueOf(uri));

        if (denda.equals("")) {
            Toast.makeText(Tilang2.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            helper.insertData(values);
            Toast.makeText(Tilang2.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Tilang2.this, MainActivity.class));
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

    //lokasi
    @SuppressLint("MissingPermission")
    public void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 2, Tilang2.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //get location
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();

        try {
            Geocoder geocoder = new Geocoder(Tilang2.this, Locale.getDefault());
            List<Address> Addreses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String addres = Addreses.get(0).getAddressLine(0);
            lokasimaps.setText(addres);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
