/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelApoteker extends AbstractTableModel{
    private List<Apoteker> list;
    
    public TabelApoteker( List<Apoteker> list) {
        this.list = list;
    }
    
    public int getRowCount() {
        return list.size();
    }
 
    public int getColumnCount() {
        return 4;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNIK();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return list.get(rowIndex).getGender();
            case 3:
                return list.get(rowIndex).getKontak();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "No. Induk";
            case 1:
                return "Nama";        
            case 2:
                return "Gender";   
            case 3:
                return "Kontak";   
            default:
                return null;
        }
    }   
}
