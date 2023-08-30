/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import Model.Transaksi;
import Model.TabelTransaksi;
import DAO.TransaksiDAO;

public class TransaksiControl {
    private Transaksi transaksi;
    private TransaksiDAO tDAO = new TransaksiDAO();
    public List <Transaksi> listTransaksi = new ArrayList<Transaksi>();
    
    public TransaksiControl(){}
    
    public TableModel displayTransaksi(){
       tDAO.makeConnection();
       TableModel TabelTransaksi = new TabelTransaksi(tDAO.showAll());
       tDAO.closeConnection();
       return TabelTransaksi;
    }
    
    public TableModel displaySearchTransaksi(String search){
       tDAO.makeConnection();
       TableModel TabelTransaksi = new TabelTransaksi(tDAO.showSearch(search));
       tDAO.closeConnection();
       return TabelTransaksi;
    }
    
    public void insertDataTransaksi(Transaksi T){
        listTransaksi.add(T);
        tDAO.makeConnection();
        tDAO.insertData(T);
        tDAO.closeConnection();
    }
    
    public int totalJumlahTransaksi(){
        int totalTransaksi = 0;
        tDAO.makeConnection();
        totalTransaksi = tDAO.totalJumlahTransaksi();
        tDAO.closeConnection();
        return totalTransaksi;
    }
}
