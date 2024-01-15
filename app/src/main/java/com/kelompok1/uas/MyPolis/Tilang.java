package com.kelompok1.uas.MyPolis;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Tilang extends AppCompatActivity {
    DBHelper helper;
    EditText nama, Tempatlahir, Tgllahir, Alamat, Pekerjaan, nopol, jenism;
    Spinner JK;
    long id;
    Button button;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    //CircularImageView imageView;
    Uri uri;

    public static final String EXTRA_NAMA = "com.zulfirizkiawan.uas.e_tilang.EXTRA_TEXT";
    public static final String EXTRA_JK = "com.zulfirizkiawan.uas.e_tilang.EXTRA_JK";
    public static final String EXTRA_TEMPATLAHIR = "com.zulfirizkiawan.uas.e_tilang.EXTRA_TEMPATLAHIR";
    public static final String EXTRA_TGLLAHIR = "com.zulfirizkiawan.uas.e_tilang.EXTRA_TGLLAHIR ";
    public static final String EXTRA_ALAMAT = "com.zulfirizkiawan.uas.e_tilang.EXTRA_ALAMAT";
    public static final String EXTRA_PEKERJAAN = "com.zulfirizkiawan.uas.e_tilang.EXTRA_PEKERJAAN";
    public static final String EXTRA_NOPOL = "com.zulfirizkiawan.uas.e_tilang.EXTRA_NOPOL";
    public static final String EXTRA_JENIS = "com.zulfirizkiawan.uas.e_tilang.EXTRA_JENIS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilang);
        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);


        nama = (EditText) findViewById(R.id.txNama_Add);
        Tempatlahir = (EditText) findViewById(R.id.txTempatLahir_Add);
        Tgllahir = (EditText) findViewById(R.id.txTglLahir_Add);
        Alamat = (EditText) findViewById(R.id.txAlamat_Add);
        Pekerjaan = (EditText) findViewById(R.id.txpekerjaan_Add);
        nopol = (EditText) findViewById(R.id.txNopol_Add);
        jenism = (EditText) findViewById(R.id.txjenismotor_Add);
        JK = (Spinner) findViewById(R.id.spJK_Add);
        //imageView = (CircularImageView) findViewById(R.id.image_profile);
//        Tgllahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateDialog();
//            }
//        });

        button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clickme();
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//
        Tgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.startPickImageActivity(Tilang.this);
//            }
//        });

    }


    public void Clickme() {
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        nama = (EditText) findViewById(R.id.txNama_Add);
        String Nama1 = nama.getText().toString();

        JK = (Spinner) findViewById(R.id.spJK_Add);
        String JK2 = JK.getSelectedItem().toString();

        Tempatlahir = (EditText) findViewById(R.id.txTempatLahir_Add);
        String Tempatlahir1 = Tempatlahir.getText().toString();

        Tgllahir = (EditText) findViewById(R.id.txTglLahir_Add);
        String Tgllahir1 = Tgllahir.getText().toString();
//        Tgllahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateDialog();
//            }
//        });

        Alamat = (EditText) findViewById(R.id.txAlamat_Add);
        String Alamat1 = Alamat.getText().toString();

        Pekerjaan = (EditText) findViewById(R.id.txpekerjaan_Add);
        String Pekerjaan1 = Pekerjaan.getText().toString();

        nopol = (EditText) findViewById(R.id.txNopol_Add);
        String nopol1 = nopol.getText().toString();

        jenism = (EditText) findViewById(R.id.txjenismotor_Add);
        String Jenism1 = jenism.getText().toString();

        Intent intent = new Intent(Tilang.this, Tilang2.class);

        intent.putExtra(EXTRA_NAMA, Nama1);

        intent.putExtra(EXTRA_JK, JK2);

        intent.putExtra(EXTRA_TEMPATLAHIR, Tempatlahir1);

        intent.putExtra(EXTRA_TGLLAHIR, Tgllahir1);

        intent.putExtra(EXTRA_ALAMAT, Alamat1);

        intent.putExtra(EXTRA_PEKERJAAN, Pekerjaan1);

        intent.putExtra(EXTRA_NOPOL, nopol1);

        intent.putExtra(EXTRA_JENIS, Jenism1);

        startActivity(intent);

        if (nama.equals("") || Alamat.equals("") || Tempatlahir.equals("") || Pekerjaan.equals("") || nopol.equals("") || jenism.equals("")) {
            Toast.makeText(Tilang.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Tilang.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
        }

//        String nama = TxNama.getText().toString().trim();
//        String tempatLahir = TxTempatLahir.getText().toString().trim();
//        String tanggal = TxTanggal.getText().toString().trim();
//        String alamat = TxAlamat.getText().toString().trim();
//        String pekerjaan = TxNohp.getText().toString().trim();
//        String nopol = Txnopol.getText().toString().trim();
//        String jenism = Txjenism.getText().toString().trim();
//        String jk = SpJK.getSelectedItem().toString().trim();
//
//        ContentValues values = new ContentValues();
//
//        values.put(DBHelper.row_nama, nama);
//        values.put(DBHelper.row_tempatLahir, tempatLahir);
//        values.put(DBHelper.row_tglLahir, tanggal);
//        values.put(DBHelper.row_alamat, alamat);
//        values.put(DBHelper.row_pekerjaan, pekerjaan);
//        values.put(DBHelper.row_nopol, nopol);
//        values.put(DBHelper.row_jenisM, jenism);
//        values.put(DBHelper.row_jk, jk);
//        values.put(DBHelper.row_foto, String.valueOf(uri));

//        if (nama.equals("") || nama.equals("") || tempatLahir.equals("") || tanggal.equals("") || alamat.equals("") || pekerjaan.equals("")|| nopol.equals("")|| jenism.equals("")) {
//            Toast.makeText(Tilang.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//        } else {
//            helper.insertData(values);
//            Toast.makeText(Tilang.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(Tilang.this, Tilang2.class));
//        }

    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                Tgllahir.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save_add:
//
//                String nama = TxNama.getText().toString().trim();
//                String tempatLahir = TxTempatLahir.getText().toString().trim();
//                String tanggal = TxTanggal.getText().toString().trim();
//                String alamat = TxAlamat.getText().toString().trim();
//                String nohp = TxNohp.getText().toString().trim();
//                String nopol = Txnopol.getText().toString().trim();
//                String jenism = Txjenism.getText().toString().trim();
//                String jk = SpJK.getSelectedItem().toString().trim();
//
//                ContentValues values = new ContentValues();
//
//                values.put(DBHelper.row_nama, nama);
//                values.put(DBHelper.row_tempatLahir, tempatLahir);
//                values.put(DBHelper.row_tglLahir, tanggal);
//                values.put(DBHelper.row_alamat, alamat);
//                values.put(DBHelper.row_pekerjaan, nohp);
//                values.put(DBHelper.row_nopol, nopol);
//                values.put(DBHelper.row_jenisM, jenism);
//                values.put(DBHelper.row_jk, jk);
//                values.put(DBHelper.row_foto, String.valueOf(uri));
//
//                if (nama.equals("") || nama.equals("") || tempatLahir.equals("") || tanggal.equals("") || alamat.equals("")) {
//                    Toast.makeText(Tilang.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//                } else {
//                    helper.insertData(values);
//                    Toast.makeText(Tilang.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @SuppressLint("MissingSuperCall")
//    @TargetApi(Build.VERSION_CODES.M)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
//                && resultCode == Activity.RESULT_OK) {
//            Uri imageuri = CropImage.getPickImageResultUri(this, data);
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
//                uri = imageuri;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
//                        , 0);
//            } else {
//                startCrop(imageuri);
//            }
//        }
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                imageView.setImageURI(result.getUri());
//                uri = result.getUri();
//            }
//        }
//    }
//
//    private void startCrop(Uri imageuri) {
//        CropImage.activity(imageuri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setAspectRatio(1, 1)
//                .start(this);
//        uri = imageuri;
//    }
}
