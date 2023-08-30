/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.swing.table.TableModel;
import java.util.*;
import Model.Apoteker;
import Model.TabelApoteker;
import DAO.ApotekerDAO;

public class ApotekerControl {
    private Apoteker a;
    private ApotekerDAO aDAO = new ApotekerDAO();
    public List <Apoteker> listApoteker = new ArrayList<Apoteker>();

    public ApotekerControl(){}
    
    public TableModel displayApoteker(){
       aDAO.makeConnection();
       TableModel tabel = new TabelApoteker(aDAO.showAll());
       aDAO.closeConnection();
       return tabel;
    }
    
    public TableModel displaySearchApoteker(String Search){
       aDAO.makeConnection();
       TableModel tabel = new TabelApoteker(aDAO.showSearch(Search));
       aDAO.closeConnection();
       return tabel;
    }  
    
    public void insertDataApoteker(Apoteker a){
        aDAO.makeConnection();
        aDAO.insertApoteker(a);
        aDAO.closeConnection();
    }
    
    public void updateDataApoteker(Apoteker a, int nik){
        aDAO.makeConnection();
        aDAO.updateApoteker(a, nik);
        aDAO.closeConnection();
    }

    public void deleteDataApoteker(int nik){
        aDAO.makeConnection();
        aDAO.deleteApoteker(nik);
        aDAO.closeConnection();
    }
    
    public int login(int nik, String password){
        int apoteker = 0;
        aDAO.makeConnection();
        apoteker = aDAO.login(nik, password);
        aDAO.closeConnection();
        return apoteker;
    } 
    
    public String namaApoteker(int nik){
        String nama = "";
        aDAO.makeConnection();
        nama = aDAO.getNamaApoteker(nik);
        aDAO.closeConnection();
        return nama;
    }
    
    public String passwordApoteker(int nik){
        String password = "";
        aDAO.makeConnection();
        password = aDAO.getPasswordApoteker(nik);
        aDAO.closeConnection();
        return password;
    }
    
    public int totalJumlahApoteker(){
        int totalApoteker = 0;
        aDAO.makeConnection();
        totalApoteker = aDAO.totalJumlahApoteker();
        aDAO.closeConnection();
        return totalApoteker;
    }
}
