/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.swing.table.TableModel;
import java.util.*;
import Model.Obat;
import Model.TabelObat;
import DAO.ObatDAO;

public class ObatControl {
    private Obat obat;
    private ObatDAO oDAO = new ObatDAO();
    public List <Obat> listObat = new ArrayList<Obat>();
    
    public ObatControl(){}
    
    public TableModel displayObat(){
       oDAO.makeConnection();
       TableModel tabel = new TabelObat(oDAO.showAll());
       oDAO.closeConnection();
       return tabel;
    }
    
     public TableModel displaySearchObat(String Search){
       oDAO.makeConnection();
       TableModel tabel = new TabelObat(oDAO.showSearch(Search));
       oDAO.closeConnection();
       return tabel;
    }
    
    public void insertDataObat(Obat obat){
        listObat.add(obat);
        oDAO.makeConnection();
        oDAO.insertObat(obat);
        oDAO.closeConnection();
    }
    
    public void updateDataObat(Obat obat,String search)
    {
        oDAO.makeConnection();
        oDAO.updateObat(obat,search);
        oDAO.closeConnection();
    }
    
    public void deleteDataObat(String search){
        oDAO.makeConnection();
        oDAO.deleteObat(search);
        oDAO.closeConnection();
    }
    
    public boolean cekObatUnik(String search)
    {
        boolean temp=false;
        oDAO.makeConnection();
        temp= oDAO.cekObatUnik(search);
        oDAO.closeConnection();
        return temp;
    }
    
    public int totalJumlahObat(){
        int totalObat = 0;
        oDAO.makeConnection();
        totalObat = oDAO.totalJumlahObat();
        oDAO.closeConnection();
        return totalObat;
    }
}
