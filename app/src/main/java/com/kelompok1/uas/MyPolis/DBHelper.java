package com.kelompok1.uas.MyPolis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_etilang";
    public static final String table_name = "tabel_biodata";

    public static final String row_id = "_id";

    public static final String row_nama = "Nama";
    public static final String row_jk = "JK";
    public static final String row_tempatLahir = "TempatLahir";
    public static final String row_tglLahir = "Tanggal";
    public static final String row_alamat = "Alamat";
    public static final String row_pekerjaan = "Nohp";
    public static final String row_nopol = "nopol";
    public static final String row_jenisM = "jenismot";
    public static final String row_tglpelanggaran = "Tanggalpelanggaran";
    public static final String row_slip = "slip";
    public static final String row_penalti = "penlati";
    public static final String row_bukti = "bukti";
    public static final String row_pelanggaran = "pelanggaran";
    public static final String row_denda = "dendaa";
    public static final String row_lokasipelanggaran = "lokasipelaggaran";
    public static final String row_foto = "Foto";


    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                + row_nama + " TEXT, "
                + row_jk + " TEXT, "
                + row_tempatLahir + " TEXT, "
                + row_tglLahir + " TEXT, "
                + row_alamat + " TEXT, "
                + row_pekerjaan + " TEXT, "
                + row_nopol + " TEXT, "
                + row_jenisM + " TEXT, "
                + row_tglpelanggaran + " TEXT, "
                + row_slip + " TEXT, "
                + row_penalti + " TEXT, "
                + row_bukti + " TEXT, "
                + row_pelanggaran + " TEXT, "
                + row_denda + " TEXT, "
                + row_lokasipelanggaran + " TEXT, "
                + row_foto + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //Get All SQLite Data
    public Cursor allData() {
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name, null);
        return cur;
    }

    //Get 1 Data By ID
    public Cursor oneData(Long id) {
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + row_id + "=" + id, null);
        return cur;
    }

    //Insert Data to Database
    public void insertData(ContentValues values) {
        db.insert(table_name, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id) {
        db.update(table_name, values, row_id + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id) {
        db.delete(table_name, row_id + "=" + id, null);
    }

    //search data
    public Cursor searchData(String keyword) {
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + row_nama + " like ? " + " ORDER BY " +
                row_id + " DESC ", new String[]{"%" + keyword + "%"}, null);
        return cur;
    }
}
