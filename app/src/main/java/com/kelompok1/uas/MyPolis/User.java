package com.kelompok1.uas.MyPolis;

public class User {
    public String id;
    public String userName;
    public String nrp;
    public String pangkat;
    public String kesatuan;
    public String nohp;
    public String alamat;
    public String email;
    public String password;

    public User(String id, String userName, String nrp, String pangkat, String kesatuan, String nohp, String alamat, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.nrp = nrp;
        this.pangkat = pangkat;
        this.kesatuan = kesatuan;
        this.nohp = nohp;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
    }
}
