/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Apoteker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApotekerDAO {
    public static Connection CON;
    public static final String url = "jdbc:ucanaccess://";
    public static final String path = "./VMeds_Database.mdb";
    
    public void makeConnection(){
        System.out.println("Opening database...");
        
        try{
            CON = DriverManager.getConnection(url+path);
            System.out.println("Success!");
        }catch(Exception e){
            System.out.println("[!] Error opening database...");
            System.out.println(e);
        }
    }
    
    public void closeConnection(){
        System.out.println("Closing database...");
        
        try{
            CON.close();
            System.out.println("Success!");
        }catch(Exception e){
            System.out.println("[!] Error closing database...");
            System.out.println(e);
        }      
    }
    
    public void insertApoteker(Apoteker A){
        String sql = "INSERT INTO Tb_Apoteker (NomorIndukApoteker, NamaApoteker, GenderApoteker, KontakApoteker, Password) VALUES ("+A.getNIK()+", '"+A.getNama()+"', '"+A.getGender()+"', "+A.getKontak()+", '"+A.getPassword()+"')";
                
        System.out.println("Adding Data..");
        try {
            Statement statement = CON.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }catch (Exception e) {
            System.out.println("[!] Error Inserting data...");
            System.out.println(e);
        }
    }
    
    public void updateApoteker(Apoteker A, int nik){             
        String sql = "UPDATE Tb_Apoteker SET NamaApoteker = '"+A.getNama()+"', GenderApoteker= '"+A.getGender()+"', KontakApoteker = "+A.getKontak()+", Password = '"+A.getPassword()+"' WHERE NomorIndukApoteker = "+nik+"";
        System.out.println("Updating Data...");
        
        try{
            Statement statement = CON.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }catch(Exception e){
            System.out.println("[!] Error Updating Apoteker...");
            System.out.println(e);
        } 
    }
    
    public void deleteApoteker(int nik){
        String sql = "DELETE FROM Tb_Apoteker WHERE NomorIndukApoteker = "+nik+"";
        System.out.println("Deleting Apoteker...");
        
        try{
            Statement statement = CON.createStatement();
            int result = statement.executeUpdate(sql);
            statement.close();
        }
        catch(Exception e)
        {
            System.out.println("[!] Error Deleting Apoteker...");
            System.out.println(e);
        }
    }
    
    public int login(int nik, String password){
        String sql = "SELECT NomorIndukApoteker FROM Tb_Apoteker WHERE NomorIndukApoteker = "+nik+" AND Password = '"+password+"'";
        System.out.println("Login...");
        
        int A = 0;
        
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                A = rs.getInt("NomorIndukApoteker");
            }else{
                A = 0;
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("[!] Login error..." + e);
        }  
        return A;
    }
    
    public String getNamaApoteker(int nik){
        String sql = "SELECT NamaApoteker FROM Tb_Apoteker WHERE NomorIndukApoteker = "+nik+"";
        System.out.println("Getting Data...");
        
        String Nama = "";
        
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                Nama = rs.getString("NamaApoteker");
            }else{
                Nama = "";
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("[!] Error getting database..." + e);
        }  
        return Nama;
    }
    
    public String getPasswordApoteker(int nik){
        String sql = "SELECT Password FROM Tb_Apoteker WHERE NomorIndukApoteker = "+nik+"";
        System.out.println("Getting Data...");
        
        String password = "";
        
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                password = rs.getString("Password");
            }else{
                password = "";
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("[!] Error getting database..." + e);
        }  
        return password;
    }
    
    public int totalJumlahApoteker(){
        String sql = "SELECT * FROM Tb_Apoteker";
        System.out.println("Getting Data...");
        
        int totalApoteker = 0;
        
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                totalApoteker ++;
            }
            rs.close();
            statement.close();
        } catch(Exception e){
            System.out.println("[!] Error getting database..." + e);
        }  
        return totalApoteker; 
    }
    
    public List<Apoteker> showAll(){
        String sql = "SELECT * FROM Tb_Apoteker";
        List<Apoteker> list = new ArrayList<>();
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Apoteker a = new Apoteker(Integer.parseInt(rs.getString("NomorIndukApoteker")), rs.getString("NamaApoteker"), rs.getString("GenderApoteker"), rs.getString("KontakApoteker"), rs.getString("Password"));
                    list.add(a);
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
    
    public List<Apoteker> showSearch(String Search){
             
        String sql = "SELECT * FROM Tb_Apoteker WHERE (NamaApoteker LIKE '%"+Search+"%') OR (NomorIndukApoteker LIKE '%"+Search+"%')";
        List<Apoteker> list = new ArrayList<>();
        try{
            Statement statement = CON.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Apoteker a = new Apoteker(Integer.parseInt(rs.getString("NomorIndukApoteker")), rs.getString("NamaApoteker"), rs.getString("GenderApoteker"), rs.getString("KontakApoteker"), rs.getString("Password"));
                    list.add(a);
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
}
