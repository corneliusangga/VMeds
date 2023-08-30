/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Obat {
    private String id, nama, jenis;
    private int stok;
    private double hargaSatuan;
    
    public Obat() {}
    
    public Obat(String i, String n, String j, int s, double hS){
        id = i;
        nama = n;
        jenis = j;
        stok = s;
        hargaSatuan = hS;
    }
    
    public String getId(){
        return id;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getJenis(){
        return jenis;
    }
    
    public int getStok(){
        return stok;
    }
    
    public double getHargaSatuan(){
        return hargaSatuan;
    } 
}
