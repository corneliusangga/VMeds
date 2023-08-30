/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Apoteker {
    public String nama, gender, kontak, password;
    public int nik;
    
    public Apoteker (){}
    
    public Apoteker(int nik, String nama, String gender, String kontak, String password){
        this.nik = nik;
        this.nama=nama;
        this.gender=gender;
        this.kontak=kontak;
        this.password=password;
    }

    public int getNIK() {
        return nik;
    }

    public void setNIK(int nik) {
        this.nik = nik;
    }    
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String Kontak) {
        this.kontak = kontak;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String tampilDataApoteker()
    {
        return ("Nomor Induk Apoteker: "+getNIK()
                +"\nNama Apoteker: "+getNama()
                +"\nJenis Kelamin Apoteker: "+getGender()
                +"\nUmur Apoteker: "+getKontak()
                +"\nPassword: "+getPassword());
    }
}
