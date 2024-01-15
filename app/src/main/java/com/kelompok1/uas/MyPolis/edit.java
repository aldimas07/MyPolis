package com.kelompok1.uas.MyPolis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class edit extends AppCompatActivity {
    DBHelper helper;
    EditText TxNama, TxTempatLahir, TxTanggal, TxAlamat, Txpekerjaan, TxNopol, TxJenismo;
    EditText Txtglpelang, TxDenda;
    Spinner SpJK, Spslip, Sppenalti, Spbukti, Sppelanggaran;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    ImageView imageView;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);


        TxNama = (EditText) findViewById(R.id.txNama_Edit);
        TxTempatLahir = (EditText) findViewById(R.id.txTempatLahir_Edit);
        TxTanggal = (EditText) findViewById(R.id.txTglLahir_Edit);
        TxAlamat = (EditText) findViewById(R.id.txAlamat_Edit);
        Txpekerjaan = (EditText) findViewById(R.id.txpekerjaan_Edit);
        TxNopol = (EditText) findViewById(R.id.txNopol_Edit);
        TxJenismo = (EditText) findViewById(R.id.txJenism_Edit);
        Txtglpelang = (EditText) findViewById(R.id.txTglpelanggaran_Edit);
        TxDenda = (EditText) findViewById(R.id.txDenda_Edit);

        SpJK = (Spinner) findViewById(R.id.spJK_Edit);
        Spslip = (Spinner) findViewById(R.id.spslip_Edit);
        Sppenalti = (Spinner) findViewById(R.id.spPenalti_Edit);
        Spbukti = (Spinner) findViewById(R.id.spbukti_Edit);
        Sppelanggaran = (Spinner) findViewById(R.id.spPelanggaran_Edit);


        imageView = (ImageView) findViewById(R.id.buktifoto_Edit);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TxTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        Txtglpelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(edit.this);
            }
        });

        getData();
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TxTanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void getData() {
        Cursor cursor = helper.oneData(id);
        if (cursor.moveToFirst()) {

            String nama = cursor.getString(cursor.getColumnIndex(DBHelper.row_nama));
            String tempatLahir = cursor.getString(cursor.getColumnIndex(DBHelper.row_tempatLahir));
            String jk = cursor.getString(cursor.getColumnIndex(DBHelper.row_jk));
            String tanggal = cursor.getString(cursor.getColumnIndex(DBHelper.row_tglLahir));
            String alamat = cursor.getString(cursor.getColumnIndex(DBHelper.row_alamat));
            String pekerjaan = cursor.getString(cursor.getColumnIndex(DBHelper.row_pekerjaan));
            String nopol = cursor.getString(cursor.getColumnIndex(DBHelper.row_nopol));
            String jenism = cursor.getString(cursor.getColumnIndex(DBHelper.row_jenisM));
            String tglpelang = cursor.getString(cursor.getColumnIndex(DBHelper.row_tglpelanggaran));
            String slip = cursor.getString(cursor.getColumnIndex(DBHelper.row_slip));
            String penalti = cursor.getString(cursor.getColumnIndex(DBHelper.row_penalti));
            String bukti = cursor.getString(cursor.getColumnIndex(DBHelper.row_bukti));
            String pelaggaran = cursor.getString(cursor.getColumnIndex(DBHelper.row_pelanggaran));
            String denda = cursor.getString(cursor.getColumnIndex(DBHelper.row_denda));

            String foto = cursor.getString(cursor.getColumnIndex(DBHelper.row_foto));


            TxNama.setText(nama);

            if (jk.equals("Laki-Laki")) {
                SpJK.setSelection(0);
            } else if (jk.equals("Perempuan")) {
                SpJK.setSelection(1);
            }

            TxTempatLahir.setText(tempatLahir);
            TxTanggal.setText(tanggal);
            TxAlamat.setText(alamat);
            Txpekerjaan.setText(pekerjaan);
            TxNopol.setText(nopol);
            TxJenismo.setText(jenism);
            Txtglpelang.setText(tglpelang);
            TxDenda.setText(denda);

            if (slip.equals("Biru")) {
                Spslip.setSelection(0);
            } else if (slip.equals("Merah")) {
                Spslip.setSelection(1);
            }

            if (penalti.equals("Ringan")) {
                Sppenalti.setSelection(0);
            } else if (penalti.equals("Sedang")) {
                Sppenalti.setSelection(1);
            } else if (penalti.equals("Berat")) {
                Sppenalti.setSelection(2);
            }

            if (bukti.equals("STNK")) {
                Spbukti.setSelection(0);
            } else if (bukti.equals("SIM")) {
                Spbukti.setSelection(1);
            } else if (bukti.equals("KTP")) {
                Spbukti.setSelection(2);
            }

            if (pelaggaran.equals("Pasal 281 : Tidak Punya SIM")) {
                Sppelanggaran.setSelection(0);
            } else if (pelaggaran.equals("Pasal 288 Ayat 2  : Tidak Bisa Menunjukan SIM")) {
                Sppelanggaran.setSelection(1);
            } else if (pelaggaran.equals("Pasal 280 : Tidak Ada Plat Pada Kendaraan")) {
                Sppelanggaran.setSelection(2);
            } else if (pelaggaran.equals("Pasal 285 Ayat 1 : Standart Motor Tidak Lengkap")) {
                Sppelanggaran.setSelection(3);
            } else if (pelaggaran.equals("Pasal 285 Ayat 2 : Standart Mobil Tidak Lengkap")) {
                Sppelanggaran.setSelection(4);
            } else if (pelaggaran.equals("Pasal 287 Ayat 1 : Melanggar Rambu Lali Lintas Jalan")) {
                Sppelanggaran.setSelection(5);
            } else if (pelaggaran.equals("Pasal 287 Ayat 5 : Melanggar Batas Kecepatan")) {
                Sppelanggaran.setSelection(6);
            } else if (pelaggaran.equals("asal 288 Ayat 1 : Tidak ada STNK")) {
                Sppelanggaran.setSelection(7);
            } else if (pelaggaran.equals("Pasal 289 : Tidak Pakai Seatbelt")) {
                Sppelanggaran.setSelection(8);
            } else if (pelaggaran.equals("Pasal 291 Ayat 1 : Tidak Pakai Helm")) {
                Sppelanggaran.setSelection(9);
            } else if (pelaggaran.equals("Pasal 293 Ayat 1 : Tidak Menyalakan Lampu Utama Saat Malam")) {
                Sppelanggaran.setSelection(10);
            } else if (pelaggaran.equals("Pasal 293 Ayat 2 : Lampu Utama Sepeda Motor Tidak Hidup Saat Siang Hari")) {
                Sppelanggaran.setSelection(11);
            } else if (pelaggaran.equals("Pasal 294 : Tidak Memberi Lampi Isyarat Saat belok")) {
                Sppelanggaran.setSelection(12);
            }

            if (foto.equals("null")) {
                imageView.setImageResource(R.drawable.ic_person_black_24dp);
            } else {
                imageView.setImageURI(Uri.parse(foto));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_edit:

                String nama = TxNama.getText().toString().trim();
                String tempatLahir = TxTempatLahir.getText().toString().trim();
                String tanggal = TxTanggal.getText().toString().trim();
                String alamat = TxAlamat.getText().toString().trim();
                String pekerjaan = Txpekerjaan.getText().toString().trim();
                String nopol = TxNopol.getText().toString().trim();
                String jenis = TxJenismo.getText().toString().trim();
                String jk = SpJK.getSelectedItem().toString().trim();
                String tglpelang = Txtglpelang.getText().toString().trim();
                String slip = Spslip.getSelectedItem().toString().trim();
                String penalti = Sppenalti.getSelectedItem().toString().trim();
                String bukti = Spbukti.getSelectedItem().toString().trim();
                String pelaggaran = Sppelanggaran.getSelectedItem().toString().trim();
                String denda = TxDenda.getText().toString().trim();

                ContentValues values = new ContentValues();

                values.put(DBHelper.row_nama, nama);
                values.put(DBHelper.row_tempatLahir, tempatLahir);
                values.put(DBHelper.row_tglLahir, tanggal);
                values.put(DBHelper.row_alamat, alamat);
                values.put(DBHelper.row_pekerjaan, pekerjaan);
                values.put(DBHelper.row_nopol, nopol);
                values.put(DBHelper.row_jenisM, jenis);
                values.put(DBHelper.row_jk, jk);
                values.put(DBHelper.row_tglpelanggaran, tglpelang);
                values.put(DBHelper.row_slip, slip);
                values.put(DBHelper.row_penalti, penalti);
                values.put(DBHelper.row_bukti, bukti);
                values.put(DBHelper.row_pelanggaran, pelaggaran);
                values.put(DBHelper.row_denda, denda);

                values.put(DBHelper.row_foto, String.valueOf(uri));

                if (nama.equals("") || pekerjaan.equals("") || tempatLahir.equals("") || tanggal.equals("") || alamat.equals("") ||
                        nopol.equals("") || jenis.equals("") || tglpelang.equals("") || denda.equals("")) {
                    Toast.makeText(edit.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    helper.updateData(values, id);
                    Toast.makeText(edit.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        switch (item.getItemId()) {
            case R.id.delete_edit:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(edit.this);
                builder.setMessage("Data ini akan dihapus.");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(edit.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                android.app.AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
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
