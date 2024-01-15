package com.kelompok1.uas.MyPolis;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class daftarpelanggar extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    DBHelper helper;
    LayoutInflater inflater;
    View dialogView;
    TextView Tv_Nama, Tv_Alamat, Tv_TempatLahir, Tv_Tanggal, Tv_pekerjaan, Tv_bukti, Tv_Penalti, Tv_tglpelanggar;
    TextView Tv_Nopol, Tv_JenisM;
    TextView Tv_pasal, Tv_denda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarpelanggar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Tilang);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, Tilang.class));
//            }
//        });

        helper = new DBHelper(this);
        listView = (ListView) findViewById(R.id.list_data);
        listView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        helper = new DBHelper(this);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchdata(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchdata(s);
                return false;
            }
        });

        return true;
    }

    public void searchdata(String keyword) {
        Cursor cursor = helper.searchData(keyword);
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //      return true;
        //  }

        return super.onOptionsItemSelected(item);
    }

    public void setListView() {
        Cursor cursor = helper.allData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView) view.findViewById(R.id.listID);
        final long id = Long.parseLong(getId.getText().toString());
        final Cursor cur = helper.oneData(id);
        cur.moveToFirst();

        final AlertDialog.Builder builder = new AlertDialog.Builder(daftarpelanggar.this);
        builder.setTitle("Pilih Opsi");

        //Add a list
        String[] options = {"Lihat Data", "Edit Data", "Hapus Data"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent iddata = new Intent(daftarpelanggar.this, read.class);
                        iddata.putExtra(DBHelper.row_id, id);
                        startActivity(iddata);
//                        final AlertDialog.Builder viewData = new AlertDialog.Builder(daftarpelanggar.this);
//                        inflater = getLayoutInflater();
//                        dialogView = inflater.inflate(R.layout.view_data, null);
//                        viewData.setView(dialogView);
//
//                        Tv_Nama = (TextView)dialogView.findViewById(R.id.tv_Nama);
//                        Tv_Alamat = (TextView)dialogView.findViewById(R.id.tv_Alamat);
//                        Tv_TempatLahir = (TextView)dialogView.findViewById(R.id.tv_TempatLahir);
//                        Tv_Tanggal = (TextView)dialogView.findViewById(R.id.tv_Tanggal);
//                        Tv_pekerjaan= (TextView)dialogView.findViewById(R.id.tv_pekerjaan);
//                        Tv_bukti = (TextView)dialogView.findViewById(R.id.tv_bukti);
//                        Tv_Penalti = (TextView)dialogView.findViewById(R.id.tv_penalti);
//                        Tv_tglpelanggar = (TextView)dialogView.findViewById(R.id.tv_tglpelang);
//
//                        Tv_Nopol = (TextView)dialogView.findViewById(R.id.tv_Nopol);
//                        Tv_JenisM = (TextView)dialogView.findViewById(R.id.tv_Jenismo);
//
//                        Tv_pasal = (TextView)dialogView.findViewById(R.id.tv_pasal);
//                        Tv_denda = (TextView)dialogView.findViewById(R.id.tv_denda);
//
//
//
//                        Tv_Nama.setText("Nama                   : " + cur.getString(cur.getColumnIndex(DBHelper.row_nama)));
//                        Tv_Alamat.setText("Alamat                 : " + cur.getString(cur.getColumnIndex(DBHelper.row_alamat)));
//                        Tv_TempatLahir.setText("Tempat Lahir       : " + cur.getString(cur.getColumnIndex(DBHelper.row_tempatLahir)));
//                        Tv_Tanggal.setText("Tanggal Lahir      : " + cur.getString(cur.getColumnIndex(DBHelper.row_tglLahir)));
//                        Tv_pekerjaan.setText("pekerjaan           : " + cur.getString(cur.getColumnIndex(DBHelper.row_pekerjaan)));
//                        Tv_bukti.setText("Bukti Pelanggaran   : " + cur.getString(cur.getColumnIndex(DBHelper.row_bukti)));
//                        Tv_Penalti.setText("pekerjaan           : " + cur.getString(cur.getColumnIndex(DBHelper.row_penalti)));
//                        Tv_tglpelanggar.setText("pekerjaan           : " + cur.getString(cur.getColumnIndex(DBHelper.row_tglpelanggaran)));
//
//                        Tv_Nopol.setText("No. Pol Kendaraan  : " + cur.getString(cur.getColumnIndex(DBHelper.row_nopol)));
//                        Tv_JenisM.setText("Jenis                 : " + cur.getString(cur.getColumnIndex(DBHelper.row_jenisM)));
//
//                        Tv_pasal.setText("pasal          : " + cur.getString(cur.getColumnIndex(DBHelper.row_pelanggaran)));
//                        Tv_denda.setText("pekerjaan           : " + cur.getString(cur.getColumnIndex(DBHelper.row_denda)));
//
//                        viewData.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                        viewData.show();
                }
                switch (which) {
                    case 1:
                        Intent iddata = new Intent(daftarpelanggar.this, edit.class);
                        iddata.putExtra(DBHelper.row_id, id);
                        startActivity(iddata);
                }
                switch (which) {
                    case 2:
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(daftarpelanggar.this);
                        builder1.setMessage("Data ini akan dihapus.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                helper.deleteData(id);
                                Toast.makeText(daftarpelanggar.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                                setListView();
                            }
                        });
                        builder1.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }
}
