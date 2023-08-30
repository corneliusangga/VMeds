/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelTransaksi extends AbstractTableModel{
    private List<Transaksi> list;
    
    public TabelTransaksi( List<Transaksi> list) {
        this.list = list;
    }
    
    public int getRowCount() {
        return list.size();
    }
 
    public int getColumnCount() {
        return 8;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNomor();
            case 1:
                return list.get(rowIndex).getNamaApoteker();
            case 2:
                return list.get(rowIndex).getNamaPembeli();
            case 3:
                return list.get(rowIndex).getTanggal();
            case 4:
                return list.get(rowIndex).getIdNamaObat();
            case 5:
                return list.get(rowIndex).getJenisObat();
            case 6:
                return list.get(rowIndex).getJumlahObat();
            case 7:
                return list.get(rowIndex).getTotalHarga();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Nomor Transaksi";
            case 1:
                return "Nama Apoteker";        
            case 2:
                return "Nama Pembeli";   
            case 3:
                return "Tanggal Pembelian";   
            case 4:
                return "ID dan Nama Obat";
            case 5:
                return "Jenis Obat";   
            case 6:
                return "Jumlah Obat";   
            case 7:
                return "Total Harga";
            default:
                return null;
        }
    }  
}
