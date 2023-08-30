/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi {
    public String nomor, namaPembeli, tanggal, namaApoteker, idNamaObat, jenisObat;
    public int jumlahObat;
    private double TotalHarga;
    
    public Transaksi (){}
    
    public Transaksi(String no, String namaP, String tgl, int jmlhObat, String namaAptkr, String idNamaObt, String jnsObat, double tlHarga){
        nomor = no;
        namaPembeli = namaP;
        tanggal = tgl;
        jumlahObat = jmlhObat;
        namaApoteker = namaAptkr;
        idNamaObat = idNamaObt;
        jenisObat = jnsObat;
        TotalHarga = tlHarga;
    }
    
    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }
    
    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(int jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getNamaApoteker() {
        return namaApoteker;
    }

    public void setNamaApoteker(String namaApoteker) {
        this.namaApoteker = namaApoteker;
    }

    public String getIdNamaObat() {
        return idNamaObat;
    }

    public void setIdMerkObat(String id_NamaObat) {
        this.idNamaObat = id_NamaObat;
    }

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
    }

    public double getTotalHarga() {
        return TotalHarga;
    }

    public void setTotalHarga(double TotalHarga) {
        this.TotalHarga = TotalHarga;
    }
    
    public String tampilDataTransaksi()
    {
        return ("Nomor Transaksi: "+getNomor()+"\nNama Apoteker: "+getNamaApoteker()+"\nNama Pembeli: "+getNamaPembeli()+
                "\nTanggal Pembelian: "+getTanggal()+"\nNama dan Merk Obat: "+getIdNamaObat()+"\nJenis Obat: "+getJenisObat()+
                "\nJumlah Obat: "+getJumlahObat()+"\nTotal Harga: "+getTotalHarga());
    }
}
