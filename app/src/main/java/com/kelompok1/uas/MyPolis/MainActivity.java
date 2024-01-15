package com.kelompok1.uas.MyPolis;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //protected Cursor cursor;
    SqliteHelper mydb;
    TextView TVnama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new SqliteHelper(this);

        TVnama = (TextView) findViewById(R.id.tvnama);
        Cursor res = mydb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() <= 1) {
            while (res.moveToNext()) {
                stringBuffer.append(res.getString(1));
            }
            TVnama.setText(stringBuffer.toString());
        }

    }

    public void denda(View view) {
        startActivity(new Intent(MainActivity.this, Daftardenda.class));
    }

    public void surat(View view) {
        startActivity(new Intent(MainActivity.this, Surattugas.class));
    }

    public void profil(View view) {
        startActivity(new Intent(MainActivity.this, Profil.class));
    }

    public void Tilang(View view) {
        startActivity(new Intent(MainActivity.this, Tilang.class));
    }

    public void pelanggar(View view) {
        startActivity(new Intent(MainActivity.this, daftarpelanggar.class));
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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
}
