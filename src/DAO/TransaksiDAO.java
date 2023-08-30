/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Transaksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    public static Connection CON;
    public static final String url = "jdbc:ucanaccess://";
    public static final String path = "./VMeds_Database.mdb";
    
    public void makeConnection(){
        System.out.println("Opening database...");
        try{
            CON = DriverManager.getConnection(url+path);
            System.out.println("Success!");
        }catch(Exception e){
            System.out.println("Error opening database...");
            System.out.println(e);
        }
    }
    
    public void closeConnection(){
        System.out.println("Closing database...");
        try{
            CON.close();
            System.out.println("Success!");
        }catch(Exception e){
            System.out.println("Error closing database...");
            System.out.println(e);
        }      
    }
    
    public void insertData(Transaksi T){
        String sql = "INSERT INTO Tb_Transaksi (NomorTransaksi,NamaApoteker,NamaPembeli,TanggalPembelian,IdNamaObat,JenisObat,JumlahObat,TotalHarga) values ('"+T.getNomor()+ "','" +T.getNamaApoteker()+"','" +T.getNamaPembeli()+"','"+T.getTanggal()+"','"+T.getIdNamaObat()+"','" +T.getJenisObat()+"'," +T.getJumlahObat()+","+T.getTotalHarga()+")";
        System.out.println("Adding Data..");
        try{
            Statement statement=CON.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }
        catch(Exception Ex){
            System.out.println(Ex);
        }
    }
    
    public int totalJumlahTransaksi(){
        String sql = "SELECT * FROM Tb_Transaksi";
        System.out.println("Getting Data...");
        
        int totalTransaksi = 0;
        
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                totalTransaksi++;
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("[!] Error getting database..." + e);
        }  
        return totalTransaksi; 
    }
    
    public List<Transaksi> showAll(){
        String sql = "SELECT NomorTransaksi, NamaApoteker, NamaPembeli, TanggalPembelian, IdNamaObat, JenisObat, JumlahObat, TotalHarga FROM Tb_Transaksi";
        List<Transaksi> list = new ArrayList<>();      
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Transaksi T = new Transaksi(rs.getString("NomorTransaksi"),rs.getString("NamaPembeli"),rs.getString("TanggalPembelian"), Integer.parseInt(rs.getString("JumlahObat")), rs.getString("NamaApoteker"),rs.getString("IdNamaObat"), rs.getString("JenisObat"),Double.parseDouble(rs.getString("TotalHarga")));
                    list.add(T);   
                }
            }
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("[!] Error reading database...");
            System.out.println(e);
        }
        return list;
    }
    
    public List <Transaksi> showSearch(String search){
        String sql = "SELECT NomorTransaksi, NamaApoteker, NamaPembeli, TanggalPembelian, IdNamaObat, JenisObat, JumlahObat, TotalHarga FROM Tb_Transaksi  WHERE (NomorTransaksi Like '%"+search+"%') "+ "OR (NamaApoteker Like '%"+ search +"%') OR (NamaPembeli Like '%"+ search +"%') OR (IdNamaObat Like '%"+ search +"%')";
        List <Transaksi> list=new ArrayList<Transaksi>();
        try{
            Statement statement=CON.createStatement();
            ResultSet rs=statement.executeQuery(sql);  
            if(rs!=null){
                while(rs.next()){
                    Transaksi T = new Transaksi(rs.getString("NomorTransaksi"),rs.getString("NamaPembeli"),rs.getString("TanggalPembelian"), Integer.parseInt(rs.getString("JumlahObat")), rs.getString("NamaApoteker"),rs.getString("IdNamaObat"), rs.getString("JenisObat"), Double.parseDouble(rs.getString("TotalHarga")));
                    list.add(T);
                }
            }
            rs.close();
            statement.close();
        }catch(Exception Ex){
            System.out.println("Error reading database information...\n");
            System.out.println(Ex);
        }
        return list;
    }
}
