/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelObat extends AbstractTableModel {
    private List<Obat> list;
    
    public TabelObat(List<Obat> list){
        this.list = list;
    }
    
    public int getRowCount(){
        return list.size();
    }

    public int getColumnCount(){
        return 5;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return list.get(rowIndex).getJenis();
            case 3:
                return list.get(rowIndex).getStok();
            case 4:
                return list.get(rowIndex).getHargaSatuan();
            default:
                return null;
        }
    }

    public String getColumnName(int column){
        switch (column){
            case 0:
                return "ID Obat";
            case 1:
                return "Nama Obat";
            case 2:
                return "Jenis Obat";
            case 3:
                return "Stok Obat";
            case 4:
                return "Harga Satuan Obat"; 
            default:
                return null;
        }
    }
}
