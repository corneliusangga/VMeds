/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Obat;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ObatDAO {
    public static Connection CON;
    public static final String url = "jdbc:ucanaccess://";
    public static final String path = "./VMeds_Database.mdb";
    
    public void makeConnection(){
        System.out.println("Opening database...");
        try{
            CON = DriverManager.getConnection(url+path);
            System.out.println("Success!");
        }
        catch(Exception e){
            System.out.println("[!] Error opening database...");
            System.out.println(e);
        }
    }
    
    public void closeConnection(){
        System.out.println("Closing database...");
        try{
            CON.close();
            System.out.println("Success!");
        }
        catch(Exception e){
            System.out.println("[!] Error closing database...");
            System.out.println(e);
        }
    }
    
    public void insertObat(Obat o){
        String sql="INSERT INTO Tb_stokObat(IdObat,NamaObat,JenisObat,StokObat,HargaPerUnit) VALUES('"+o.getId()+"','"+o.getNama()+"','"+o.getJenis()+"',"+o.getStok()+","+o.getHargaSatuan()+")";
        System.out.println("Inserting Data...");
        try{
            Statement statement = CON.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }
        catch(Exception e){
            System.out.println("[!] Error Inserting data...");
            System.out.println(e);
        }
    }
    
    public void updateObat(Obat o, String search){
        String sql="UPDATE Tb_stokObat SET NamaObat ='"+ o.getNama()+"',JenisObat ='"+ o.getJenis()+"',StokObat="+ o.getStok()+",HargaPerUnit ="+o.getHargaSatuan()+" WHERE IdObat ='" +search+"'";
        System.out.println("Updating Data...");
        try{
            Statement statement = CON.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }
        catch(Exception e){
            System.out.println("[!] Error Updating Apoteker...");
            System.out.println(e);
        }
    }
    
     public void deleteObat(String search){
        String sql="DELETE FROM Tb_stokObat WHERE IdObat = '" +search+"'";
        try{
            Statement statement = CON.createStatement();
            int result=statement.executeUpdate(sql);
            statement.close();
        }
        catch(Exception e){
            System.out.println("[!] Error Deleting Apoteker...");
            System.out.println(e);
        }
    }
     
     public boolean cekObatUnik(String search){
         boolean temp=false;
         String sql="SELECT * FROM Tb_stokObat where IdObat='"+search+"'";
          try{
            Statement statement=CON.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                   temp=true;
                }
            }
            rs.close();
            statement.close();

        }
        catch(Exception Ex){
            System.out.println(Ex);
        }
         
         return temp;
     }
     
    public int totalJumlahObat(){
        String sql = "SELECT * FROM Tb_stokObat";
        System.out.println("Getting Data...");
        
        int totalObat = 0;
        
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                totalObat ++;
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("[!] Error getting database..." + e);
        }  
        return totalObat; 
    }
     
     
    public List <Obat> showAll(){
        String sql="SELECT * FROM Tb_stokObat";
        List <Obat> list=new ArrayList<Obat>();
        try{
            Statement statement = CON.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Obat o = new Obat(rs.getString("IdObat"),rs.getString("NamaObat"),rs.getString("JenisObat"),Integer.parseInt(rs.getString("StokObat")),Double.parseDouble(rs.getString("HargaPerUnit")));
                    list.add(o);
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
    
    public List <Obat> showSearch(String Search){
        String sql="SELECT * FROM Tb_stokObat  WHERE (IdObat Like '%"+Search+"%') OR (NamaObat Like '%"+ Search +"%') OR (JenisObat Like '%"+ Search +"%')";
        List <Obat> list = new ArrayList<Obat>();
        try{
            Statement statement = CON.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Obat o = new Obat(rs.getString("IdObat"),rs.getString("NamaObat"),rs.getString("JenisObat"),Integer.parseInt(rs.getString("StokObat")),Double.parseDouble(rs.getString("HargaPerUnit")));
                    list.add(o);
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("[!] Error reading database...");
            System.out.println(e);
        }
         return list;
    }
}
