/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.JOptionPane;

import Model.*;
import DAO.*;
import Control.*;
import Exception.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends javax.swing.JFrame{
    CardLayout cardLayout;
    private int nikApoteker;
    private static int nik;
    
    Obat obat;
    ObatDAO oDAO = new ObatDAO();
    ObatControl obatControl;
    
    Apoteker apoteker;
    ApotekerDAO aDAO = new ApotekerDAO();
    ApotekerControl apotekerControl;
    
    Transaksi transaksi;
    TransaksiDAO tDAO = new TransaksiDAO();
    TransaksiControl transaksiControl;
    
    public Dashboard(int nikApoteker){
        this.nikApoteker = nikApoteker;
        nik = nikApoteker;
        
        initComponents();
        Component [] components = this.getContentPane().getComponents();
        for(Component component : components){
                if(component instanceof JButton){
                        ((JButton) component).setUI(new BasicButtonUI());
                }
        }
        cardLayout = (CardLayout)(pnlCards.getLayout()); 
        
        obat = new Obat();
        obatControl = new ObatControl();
        ShowTableObat();
        setEnDisMenuObat(false);
        
        apoteker = new Apoteker();
        apotekerControl = new ApotekerControl();
        ShowTableApoteker();
        setEnDisMenuApoteker(false);
        
        transaksi = new Transaksi();
        transaksiControl = new TransaksiControl();
        ShowTableTransaksi();
        ShowTableObatTransaksi();
        setEnDisMenuTransaksi(false);
        
        showTotalJumlah();
        
        jTxtTotalApoteker_Dashboard.setEnabled(true);
        jTxtTotalJenisObat_Dashboard.setEnabled(true);
        jTxtTotalTransaksi_Dashboard.setEnabled(true);
        
        if(nik == 1){
            jButton2.setEnabled(true);
        }else{
            jButton2.setEnabled(false);
        }
    }
    
    public void showTotalJumlah(){
        jTxtTotalApoteker_Dashboard.setText(String.valueOf(apotekerControl.totalJumlahApoteker()));
        jTxtTotalJenisObat_Dashboard.setText(String.valueOf(obatControl.totalJumlahObat()));
        jTxtTotalTransaksi_Dashboard.setText(String.valueOf(transaksiControl.totalJumlahTransaksi()));
    }
    
    public void ShowTableApoteker(){
        jTableApoteker.setModel(apotekerControl.displayApoteker());
        jTableApoteker.getColumnModel().getColumn(0).setMaxWidth(100); 
    }
    
    public void ShowTableApoteker2(){
        jTableApoteker.setModel(apotekerControl.displaySearchApoteker(jTextCariApoteker.getText()));
        jTableApoteker.getColumnModel().getColumn(0).setMaxWidth(100); 
    }
    
    public void ShowTableObat(){
        jTableObat.setModel(obatControl.displayObat());
        jTableObat.getColumnModel().getColumn(0).setMaxWidth(100); 
    }
    
    public void ShowTableObat2(){
        jTableObat.setModel(obatControl.displaySearchObat(jTextCariObat.getText()));
        jTableObat.getColumnModel().getColumn(0).setMaxWidth(100); 
    }
    
    public void ShowTableObatTransaksi(){
        jTableObatTransaksi.setModel(obatControl.displayObat());
        jTableObatTransaksi.getColumnModel().getColumn(0).setMaxWidth(100); 
    }
    
    public void ShowTableObatTransaksi2(){
        jTableObatTransaksi.setModel(obatControl.displaySearchObat(jTextCariObat4.getText()));
        jTableObatTransaksi.getColumnModel().getColumn(0).setMaxWidth(100); 
    }
    
    public void ShowTableTransaksi(){
        jTableTransaksi.setModel(transaksiControl.displayTransaksi());
        jTableTransaksi.getColumnModel().getColumn(0).setMaxWidth(100);
    }
    
    public void ShowTableTransaksi2(){
        jTableTransaksi.setModel(transaksiControl.displaySearchTransaksi(jTextCariTransaksi.getText()));
        jTableTransaksi.getColumnModel().getColumn(0).setMaxWidth(100);
    }
    
    private void selectDataApoteker(){
        int temp = jTableApoteker.getSelectedRow(), index;
        jTextNIKApoteker.setText(jTableApoteker.getValueAt(temp,0).toString());
        jTextNamaApoteker1.setText(jTableApoteker.getValueAt(temp,1).toString());
        if("Laki - Laki".equals(jTableApoteker.getValueAt(temp,2).toString())) 
            index = 0;
        else 
            index = 1;
        jComboBoxGender.setSelectedIndex(index);
        jTextKontakApoteker.setText(jTableApoteker.getValueAt(temp,3).toString());
        jPasswordApoteker.setText(apotekerControl.passwordApoteker(Integer.parseInt(jTableApoteker.getValueAt(temp,0).toString())));
    }
    
    private void selectDataObat() {
       int temp = jTableObat.getSelectedRow(), index;
       jTextidObat.setText(jTableObat.getValueAt(temp,0).toString());
       jTextNamaObat.setText(jTableObat.getValueAt(temp,1).toString());
       if("Tablet/Kapsul/Pil".equals(jTableObat.getValueAt(temp,2).toString())) 
           index = 0;
       else if("Sirup/Suspensi/Emulsi".equals(jTableObat.getValueAt(temp,2).toString())) 
           index = 1;
       else 
           index = 2;
       jComboBoxJenisObat.setSelectedIndex(index);
       jTextStokObat.setText(jTableObat.getValueAt(temp,3).toString());
       jTextHargaSatuanObat.setText(jTableObat.getValueAt(temp,4).toString());
    }
    
    private void selectDataObatTransaksi() {
       int temp = jTableObatTransaksi.getSelectedRow(), index;
       jTextIdObat4.setText(jTableObatTransaksi.getValueAt(temp,0).toString());
       jTextNamaObat4.setText(jTableObatTransaksi.getValueAt(temp,1).toString());
       if("Tablet/Kapsul/Pil".equals(jTableObatTransaksi.getValueAt(temp,2).toString())) 
           index = 0;
       else if("Sirup/Suspensi/Emulsi".equals(jTableObatTransaksi.getValueAt(temp,2).toString())) 
           index = 1;
       else 
           index = 2;
       jComboBoxJenisObat4.setSelectedIndex(index);
       jTextStokObat4.setText(jTableObatTransaksi.getValueAt(temp,3).toString());
       jTextHargaSatuanObat4.setText(jTableObatTransaksi.getValueAt(temp,4).toString());
    }
    
    private void selectDataTransaksi(){
        int temp = jTableTransaksi.getSelectedRow();
        jTextNoTransaksi.setText(jTableApoteker.getValueAt(temp, 0).toString());
        jTextNamaApoteker4.setText(jTableApoteker.getValueAt(temp, 1).toString());
        jTextNamaPembeli.setText(jTableApoteker.getValueAt(temp, 2).toString());
        jTextTanggalPembelian.setText(jTableApoteker.getValueAt(temp, 3).toString());
        jTextIdObat4.setText(jTableApoteker.getValueAt(temp, 4).toString());
        jTextNamaObat4.setText(jTableApoteker.getValueAt(temp, 5).toString());
        jTextJumlahObat.setText(jTableApoteker.getValueAt(temp, 6).toString());
        jTextTotalHarga.setText(jTableApoteker.getValueAt(temp, 7).toString());
    }
    
    public void setEnDisMenuApoteker(boolean val){
        jTextNIKApoteker.setEnabled(val);
        jTextNamaApoteker1.setEnabled(val);
        jComboBoxGender.setEnabled(val);
        jTextKontakApoteker.setEnabled(val);
        jPasswordApoteker.setEnabled(val);
                
        jTextNIKApoteker.setEditable(val); 
        jTextNamaApoteker1.setEditable(val); 
        jComboBoxGender.setEditable(false); 
        jTextKontakApoteker.setEditable(val); 
        jPasswordApoteker.setEditable(val); 
    }
    
    public void ClearTextMenuApoteker(){
        jTextNIKApoteker.setText("");
        jTextNamaApoteker1.setText("");
        jTextKontakApoteker.setText("");
        jPasswordApoteker.setText("");

        jButtonTambah2.setText("TAMBAH");
        jButtonUbah2.setText("UBAH");
       
        jButtonTambah2.setEnabled(true);
        jButtonUbah2.setEnabled(true);
        jButtonHapus2.setEnabled(true);
       
        setEnDisMenuObat(false);
    }
    
      
    public void setEnDisMenuObat(boolean val){
        jTextidObat.setEnabled(val);
        jTextNamaObat.setEnabled(val);
        jComboBoxJenisObat.setEnabled(val);
        jTextStokObat.setEnabled(val);
        jTextHargaSatuanObat.setEnabled(val);
        
        jTextidObat.setEditable(val);
        jTextNamaObat.setEditable(val);
        jComboBoxJenisObat.setEditable(false); 
        jTextStokObat.setEditable(val);
        jTextHargaSatuanObat.setEditable(val); 
   }

    public void ClearTextMenuObat(){
       jTextidObat.setText("");
       jTextNamaObat.setText("");
       jTextStokObat.setText("");
       jTextHargaSatuanObat.setText("");
       
       jButtonTambah3.setText("TAMBAH");
       jButtonUbah3.setText("UBAH");
       
       jButtonTambah3.setEnabled(true);
       jButtonUbah3.setEnabled(true);
       jButtonHapus3.setEnabled(true);
       
       setEnDisMenuTransaksi(false);
    }
    
   public void setEnDisMenuTransaksi(boolean val){
        jTextCariObat4.setEnabled(val);
        jTextNoTransaksi.setEnabled(val);
        jTextNamaApoteker4.setEnabled(val);
        jTextNamaPembeli.setEnabled(val);
        jTextTanggalPembelian.setEnabled(val);
        jTextIdObat4.setEnabled(val);
        jTextNamaObat4.setEnabled(val);
        jComboBoxJenisObat4.setEnabled(val);
        jTextStokObat4.setEnabled(val);
        jTextHargaSatuanObat4.setEnabled(val);
        jTextJumlahObat.setEnabled(val);
        jTextTotalHarga.setEnabled(val);
        jTextJumlahUang.setEnabled(val);
        jTextKembalian.setEnabled(val);
        jTableObatTransaksi.setVisible(val);
        jTableObatTransaksi.setOpaque(val);
        
        jTextCariObat4.setEditable(val);
        jTextNoTransaksi.setEditable(val);
        jTextNamaApoteker4.setEditable(val);
        jTextNamaPembeli.setEditable(val);
        jTextIdObat4.setEditable(val);
        jTextNamaObat4.setEditable(val);
        jComboBoxJenisObat4.setEditable(false);
        jTextStokObat4.setEditable(val);
        jTextHargaSatuanObat4.setEditable(val);
        jTextJumlahObat.setEditable(val);
        jTextTotalHarga.setEditable(val);
        jTextJumlahUang.setEditable(val);
        jTextKembalian.setEditable(val);
    }
    
    public void ClearTextMenuTransaksi(){
        jTextCariTransaksi.setText("");
        jTextCariObat.setText("");
        jTextNoTransaksi.setText("");
        jTextNamaApoteker4.setText("");
        jTextNamaPembeli.setText("");
        jTextTanggalPembelian.setText("");
        jTextIdObat4.setText("");
        jTextNamaObat4.setText("");
        jTextStokObat4.setText("");
        jTextHargaSatuanObat4.setText("");
        jTextJumlahObat.setText("");
        jTextTotalHarga.setText("");
        jTextJumlahUang.setText("");
        jTextKembalian.setText("");
        
        jButtonTambah4.setText("TAMBAH");
        jButtonTambah4.setEnabled(true);
        
        setEnDisMenuTransaksi(false);
    }
    
    private void tambahTransaksi(){
        jTextNoTransaksi.setText(setNomorTransaksi());
        jTextTanggalPembelian.setText(setTanggalTransaksi());
        jTextNamaApoteker4.setText(setNamaApoteker());
        
        jTextNoTransaksi.setEnabled(false);
        jTextNamaApoteker4.setEnabled(false);
        jTextTanggalPembelian.setEnabled(false);
        jTextIdObat4.setEnabled(false);
        jTextNamaObat4.setEnabled(false);
        jComboBoxJenisObat4.setEnabled(false);
        jTextStokObat4.setEnabled(false);
        jTextHargaSatuanObat4.setEnabled(false);
        jTextTotalHarga.setEnabled(false);
        jTextKembalian.setEnabled(false);
        jTextJumlahObat.setEnabled(false);
        jTextJumlahUang.setEnabled(false); 
    }
    
    private String setNomorTransaksi(){
        Date thisDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("ddMMYHHmmss");
        String noTransaksi = dateForm.format(thisDate);
        return noTransaksi;
    }
    
    private String setNamaApoteker(){
        String namaApoteker = apotekerControl.namaApoteker(nik);
        return namaApoteker;
    }
    
    private String setTanggalTransaksi(){
        Date thisDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/Y");
        String tanggalTransaksi = dateForm.format(thisDate);
        return tanggalTransaksi;
    }
    
    private boolean cekKosongApoteker() throws ExceptionApoteker{
       if(jTextNIKApoteker.getText().isEmpty()==true || jTextNamaApoteker1.getText().isEmpty()==true || jTextKontakApoteker.getText().isEmpty()==true || jPasswordApoteker.getText().isEmpty()==true)
            throw new ExceptionApoteker(1);
       return false;
    }
    
    private boolean cekKosongObat() throws ExceptionObat{
       if(jTextidObat.getText().isEmpty()==true || jTextNamaObat.getText().isEmpty()==true || jTextStokObat.getText().isEmpty()==true || jTextHargaSatuanObat.getText().isEmpty()==true)
            throw new ExceptionObat(1);
       return false;
    }
    
    private boolean cekKosong() throws ExceptionTransaksi{
       if(jTextNoTransaksi.getText().isEmpty()==true || jTextNamaApoteker4.getText().isEmpty()==true || jTextNamaPembeli.getText().isEmpty()==true || jTextTanggalPembelian.getText().isEmpty()==true || jTextKembalian.getText().isEmpty()==true || jTextIdObat4.getText().isEmpty()==true || jTextNamaObat4.getText().isEmpty()==true || jTextStokObat4.getText().isEmpty()==true || jTextHargaSatuanObat4.getText().isEmpty()==true || jTextJumlahObat.getText().isEmpty()==true || jTextTotalHarga.getText().isEmpty()==true || jTextJumlahUang.getText().isEmpty()==true)
            throw new ExceptionTransaksi(1);
       return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonLogout = new javax.swing.JButton();
        pnlCards = new javax.swing.JPanel();
        pnlCard1 = new javax.swing.JPanel();
        jTxtTotalTransaksi_Dashboard = new javax.swing.JTextField();
        jTxtTotalApoteker_Dashboard = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTxtTotalJenisObat_Dashboard = new javax.swing.JTextField();
        pnlCard2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextCariApoteker = new javax.swing.JTextField();
        jTextNIKApoteker = new javax.swing.JTextField();
        jTextNamaApoteker1 = new javax.swing.JTextField();
        jTextKontakApoteker = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordApoteker = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableApoteker = new javax.swing.JTable();
        jButtonTambah2 = new javax.swing.JButton();
        jButtonUbah2 = new javax.swing.JButton();
        jButtonHapus2 = new javax.swing.JButton();
        jButtonBatal2 = new javax.swing.JButton();
        jComboBoxGender = new javax.swing.JComboBox<>();
        pnlCard3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextCariObat = new javax.swing.JTextField();
        jTextidObat = new javax.swing.JTextField();
        jTextNamaObat = new javax.swing.JTextField();
        jTextStokObat = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableObat = new javax.swing.JTable();
        jButtonTambah3 = new javax.swing.JButton();
        jButtonUbah3 = new javax.swing.JButton();
        jButtonHapus3 = new javax.swing.JButton();
        jButtonBatal3 = new javax.swing.JButton();
        jComboBoxJenisObat = new javax.swing.JComboBox<>();
        jTextHargaSatuanObat = new javax.swing.JTextField();
        pnlCard4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextCariTransaksi = new javax.swing.JTextField();
        jTextNoTransaksi = new javax.swing.JTextField();
        jTextNamaApoteker4 = new javax.swing.JTextField();
        jTextNamaPembeli = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTransaksi = new javax.swing.JTable();
        jButtonTambah4 = new javax.swing.JButton();
        jButtonBatal4 = new javax.swing.JButton();
        jComboBoxJenisObat4 = new javax.swing.JComboBox<>();
        jTextJumlahUang = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextIdObat4 = new javax.swing.JTextField();
        jTextNamaObat4 = new javax.swing.JTextField();
        jTextJumlahObat = new javax.swing.JTextField();
        jTextTotalHarga = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextTanggalPembelian = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableObatTransaksi = new javax.swing.JTable();
        jTextStokObat4 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextHargaSatuanObat4 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextKembalian = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextCariObat4 = new javax.swing.JTextField();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1005, 650));
        setResizable(false);

        jSplitPane1.setMinimumSize(new java.awt.Dimension(1000, 625));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1000, 625));

        jPanel1.setBackground(new java.awt.Color(0, 113, 188));
        jPanel1.setMaximumSize(new java.awt.Dimension(210, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(210, 185));
        jPanel1.setPreferredSize(new java.awt.Dimension(210, 100));

        jPanel2.setBackground(new java.awt.Color(0, 113, 188));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/VMeds Logo.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Button Menu Dashboard.jpg"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(200, 40));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Dashboard.jpg"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Dashboard.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Button Menu Apoteker.jpg"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPreferredSize(new java.awt.Dimension(200, 40));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Apoteker.jpg"))); // NOI18N
        jButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Apoteker.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Button Menu Stok Obat.jpg"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPreferredSize(new java.awt.Dimension(200, 40));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Stok Obat.jpg"))); // NOI18N
        jButton3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Stok Obat.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Button Menu Transaksi.jpg"))); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(200, 40));
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Transaksi.jpg"))); // NOI18N
        jButton4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Button Menu Transaksi.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        jPanel3.setBackground(new java.awt.Color(0, 113, 188));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 220));
        jPanel3.setRequestFocusEnabled(false);

        jButtonLogout.setBackground(new java.awt.Color(0, 113, 188));
        jButtonLogout.setForeground(new java.awt.Color(0, 113, 188));
        jButtonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Logout Button.png"))); // NOI18N
        jButtonLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLogout.setMaximumSize(new java.awt.Dimension(52, 52));
        jButtonLogout.setPreferredSize(new java.awt.Dimension(52, 52));
        jButtonLogout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Logout Button Clicked.png"))); // NOI18N
        jButtonLogout.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Logout Button Clicked.png"))); // NOI18N
        jButtonLogout.setVerifyInputWhenFocusTarget(false);
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        jSplitPane1.setLeftComponent(jPanel1);

        pnlCards.setMinimumSize(new java.awt.Dimension(297, 623));
        pnlCards.setLayout(new java.awt.CardLayout());

        pnlCard1.setBackground(new java.awt.Color(255, 255, 255));

        jTxtTotalTransaksi_Dashboard.setEditable(false);
        jTxtTotalTransaksi_Dashboard.setBackground(new java.awt.Color(0, 113, 188));
        jTxtTotalTransaksi_Dashboard.setFont(new java.awt.Font("Segoe UI Black", 0, 60)); // NOI18N
        jTxtTotalTransaksi_Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        jTxtTotalTransaksi_Dashboard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtTotalTransaksi_Dashboard.setText("0");
        jTxtTotalTransaksi_Dashboard.setCaretColor(new java.awt.Color(255, 255, 255));
        jTxtTotalTransaksi_Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTxtTotalTransaksi_Dashboard.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jTxtTotalApoteker_Dashboard.setEditable(false);
        jTxtTotalApoteker_Dashboard.setBackground(new java.awt.Color(0, 113, 188));
        jTxtTotalApoteker_Dashboard.setFont(new java.awt.Font("Segoe UI Black", 0, 60)); // NOI18N
        jTxtTotalApoteker_Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        jTxtTotalApoteker_Dashboard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtTotalApoteker_Dashboard.setText("0");
        jTxtTotalApoteker_Dashboard.setCaretColor(new java.awt.Color(255, 255, 255));
        jTxtTotalApoteker_Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTxtTotalApoteker_Dashboard.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Total Transaksi");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Total Apoteker");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Total Jenis Obat");

        jTxtTotalJenisObat_Dashboard.setEditable(false);
        jTxtTotalJenisObat_Dashboard.setBackground(new java.awt.Color(0, 113, 188));
        jTxtTotalJenisObat_Dashboard.setFont(new java.awt.Font("Segoe UI Black", 0, 60)); // NOI18N
        jTxtTotalJenisObat_Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        jTxtTotalJenisObat_Dashboard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtTotalJenisObat_Dashboard.setText("0");
        jTxtTotalJenisObat_Dashboard.setCaretColor(new java.awt.Color(255, 255, 255));
        jTxtTotalJenisObat_Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTxtTotalJenisObat_Dashboard.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlCard1Layout = new javax.swing.GroupLayout(pnlCard1);
        pnlCard1.setLayout(pnlCard1Layout);
        pnlCard1Layout.setHorizontalGroup(
            pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard1Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel31)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard1Layout.createSequentialGroup()
                        .addComponent(jTxtTotalJenisObat_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jTxtTotalTransaksi_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
            .addGroup(pnlCard1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jTxtTotalApoteker_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCard1Layout.setVerticalGroup(
            pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlCard1Layout.createSequentialGroup()
                            .addComponent(jTxtTotalTransaksi_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel31))
                        .addGroup(pnlCard1Layout.createSequentialGroup()
                            .addComponent(jTxtTotalApoteker_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel32)))
                    .addGroup(pnlCard1Layout.createSequentialGroup()
                        .addComponent(jTxtTotalJenisObat_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)))
                .addContainerGap(222, Short.MAX_VALUE))
        );

        pnlCards.add(pnlCard1, "pnlCard1");

        pnlCard2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Cari");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Data Apoteker");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nomor Induk");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nama Apoteker");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Jenis Kelamin");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Kontak");

        jTextCariApoteker.setPreferredSize(new java.awt.Dimension(60, 20));
        jTextCariApoteker.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextCariApotekerKeyReleased(evt);
            }
        });

        jTextNIKApoteker.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Password");

        jTableApoteker.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableApoteker.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableApoteker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableApotekerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableApoteker);

        jButtonTambah2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonTambah2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTambah2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonTambah2.setText("TAMBAH");
        jButtonTambah2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTambah2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTambah2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonTambah2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonTambah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambah2ActionPerformed(evt);
            }
        });

        jButtonUbah2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUbah2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUbah2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonUbah2.setText("UBAH");
        jButtonUbah2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonUbah2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUbah2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonUbah2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonUbah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbah2ActionPerformed(evt);
            }
        });

        jButtonHapus2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonHapus2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonHapus2.setText("HAPUS");
        jButtonHapus2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonHapus2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHapus2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonHapus2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonHapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapus2ActionPerformed(evt);
            }
        });

        jButtonBatal2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBatal2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBatal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonBatal2.setText("BATAL");
        jButtonBatal2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBatal2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBatal2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonBatal2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonBatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBatal2ActionPerformed(evt);
            }
        });

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - Laki", "Perempuan" }));
        jComboBoxGender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxGender.setEnabled(false);

        javax.swing.GroupLayout pnlCard2Layout = new javax.swing.GroupLayout(pnlCard2);
        pnlCard2.setLayout(pnlCard2Layout);
        pnlCard2Layout.setHorizontalGroup(
            pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard2Layout.createSequentialGroup()
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextKontakApoteker)
                                .addComponent(jPasswordApoteker, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                .addComponent(jTextNIKApoteker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextNamaApoteker1))
                            .addComponent(jComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonTambah2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonUbah2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonHapus2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBatal2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addGroup(pnlCard2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextCariApoteker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(402, 402, 402))))
                .addGap(40, 40, 40))
        );
        pnlCard2Layout.setVerticalGroup(
            pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard2Layout.createSequentialGroup()
                .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextCariApoteker, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextNIKApoteker, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextNamaApoteker1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextKontakApoteker, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jPasswordApoteker, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCard2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonTambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUbah2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonHapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pnlCards.add(pnlCard2, "pnlCard2");

        pnlCard3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Cari");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Data Obat");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("ID");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Nama");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Jenis");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Stok");

        jTextCariObat.setPreferredSize(new java.awt.Dimension(60, 20));
        jTextCariObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextCariObatKeyReleased(evt);
            }
        });

        jTextidObat.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Harga Satuan");

        jTableObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableObat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableObatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableObat);

        jButtonTambah3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonTambah3.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTambah3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonTambah3.setText("TAMBAH");
        jButtonTambah3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTambah3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTambah3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonTambah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambah3ActionPerformed(evt);
            }
        });

        jButtonUbah3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUbah3.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUbah3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonUbah3.setText("UBAH");
        jButtonUbah3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonUbah3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUbah3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonUbah3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonUbah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbah3ActionPerformed(evt);
            }
        });

        jButtonHapus3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonHapus3.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHapus3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonHapus3.setText("HAPUS");
        jButtonHapus3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonHapus3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHapus3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonHapus3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonHapus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapus3ActionPerformed(evt);
            }
        });

        jButtonBatal3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBatal3.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBatal3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonBatal3.setText("BATAL");
        jButtonBatal3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBatal3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBatal3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonBatal3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonBatal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBatal3ActionPerformed(evt);
            }
        });

        jComboBoxJenisObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tablet/Kapsul/Pil", "Sirup/Suspensi/Emulsi", "Lainnya" }));
        jComboBoxJenisObat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxJenisObat.setEnabled(false);
        jComboBoxJenisObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJenisObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCard3Layout = new javax.swing.GroupLayout(pnlCard3);
        pnlCard3.setLayout(pnlCard3Layout);
        pnlCard3Layout.setHorizontalGroup(
            pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard3Layout.createSequentialGroup()
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextHargaSatuanObat)
                            .addComponent(jComboBoxJenisObat, 0, 293, Short.MAX_VALUE)
                            .addComponent(jTextStokObat)
                            .addComponent(jTextNamaObat)
                            .addComponent(jTextidObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonTambah3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonUbah3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonHapus3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBatal3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addGroup(pnlCard3Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextCariObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(402, 402, 402))))
                .addGap(40, 40, 40))
        );
        pnlCard3Layout.setVerticalGroup(
            pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard3Layout.createSequentialGroup()
                .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextCariObat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextidObat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNamaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxJenisObat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jTextStokObat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextHargaSatuanObat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCard3Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonTambah3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUbah3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonHapus3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBatal3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pnlCards.add(pnlCard3, "pnlCard3");

        pnlCard4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Cari Obat");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Data Transaksi");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("No. Transaksi");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Nama Apoteker");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Nama Pembeli");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Tanggal Pembelian");

        jTextCariTransaksi.setPreferredSize(new java.awt.Dimension(60, 20));
        jTextCariTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextCariTransaksiKeyReleased(evt);
            }
        });

        jTextNoTransaksi.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Jumlah Uang");

        jTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(jTableTransaksi);

        jButtonTambah4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonTambah4.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTambah4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonTambah4.setText("TAMBAH");
        jButtonTambah4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTambah4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTambah4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonTambah4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonTambah4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambah4ActionPerformed(evt);
            }
        });

        jButtonBatal4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBatal4.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBatal4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Default Button.png"))); // NOI18N
        jButtonBatal4.setText("BATAL");
        jButtonBatal4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBatal4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBatal4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonBatal4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Clicked Default Button.png"))); // NOI18N
        jButtonBatal4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBatal4ActionPerformed(evt);
            }
        });

        jComboBoxJenisObat4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tablet/Kapsul/Pil", "Sirup/Suspensi/Emulsi", "Lainnya" }));
        jComboBoxJenisObat4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTextJumlahUang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextJumlahUang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextJumlahUangKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("ID Obat");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Nama Obat");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Stok Obat");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Jumlah Obat");

        jTextJumlahObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextJumlahObatKeyReleased(evt);
            }
        });

        jTextTotalHarga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Total Harga");

        jTableObatTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableObatTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableObatTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableObatTransaksiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableObatTransaksi);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Jenis Obat");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Harga Satuan");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Kembalian");

        jTextKembalian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setText("Cari Transaksi");

        jTextCariObat4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextCariObat4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCard4Layout = new javax.swing.GroupLayout(pnlCard4);
        pnlCard4.setLayout(pnlCard4Layout);
        pnlCard4Layout.setHorizontalGroup(
            pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlCard4Layout.createSequentialGroup()
                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jTextCariTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(pnlCard4Layout.createSequentialGroup()
                                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlCard4Layout.createSequentialGroup()
                                                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel26)
                                                    .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlCard4Layout.createSequentialGroup()
                                                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(jTextKembalian, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextTotalHarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                                            .addComponent(jTextJumlahUang, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addGap(60, 60, 60))
                                                    .addComponent(jTextTanggalPembelian)))
                                            .addGroup(pnlCard4Layout.createSequentialGroup()
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextNamaPembeli))
                                            .addGroup(pnlCard4Layout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextNamaApoteker4))
                                            .addGroup(pnlCard4Layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(52, 52, 52)
                                                .addComponent(jTextNoTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(4, 4, 4)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonTambah4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextCariObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonBatal4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(31, 31, 31))
                                    .addGroup(pnlCard4Layout.createSequentialGroup()
                                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextIdObat4)
                                    .addComponent(jTextNamaObat4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCard4Layout.createSequentialGroup()
                                        .addComponent(jComboBoxJenisObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextStokObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextHargaSatuanObat4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextJumlahObat, javax.swing.GroupLayout.Alignment.LEADING))))))
                .addGap(29, 29, 29))
        );
        pnlCard4Layout.setVerticalGroup(
            pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCariTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNamaApoteker4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextTanggalPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCard4Layout.createSequentialGroup()
                        .addComponent(jTextTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jTextJumlahUang))
                        .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jTextKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(282, 282, 282))
            .addGroup(pnlCard4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCariObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextIdObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCard4Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextNamaObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCard4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxJenisObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextStokObat4)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextHargaSatuanObat4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextJumlahObat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBatal4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTambah4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(470, 470, 470))
        );

        pnlCards.add(pnlCard4, "pnlCard4");

        jSplitPane1.setRightComponent(pnlCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1016, 659));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cardLayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cardLayout.show(pnlCards, "pnlCard1");
        showTotalJumlah();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cardLayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cardLayout.show(pnlCards, "pnlCard4");
        ShowTableObatTransaksi();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonTambah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambah3ActionPerformed
        if (jButtonTambah3.getText()=="TAMBAH"){
            ClearTextMenuObat();
            setEnDisMenuObat(true);
            jButtonTambah3.setText("SIMPAN");
            jButtonUbah3.setEnabled(false);
            jButtonHapus3.setEnabled(false);
        }else if (jButtonTambah3.getText()=="SIMPAN"){
            try{
                if (cekKosongObat()==false){
                    if(obatControl.cekObatUnik(jTextidObat.getText()) != true){
                        String jenisObat = "";
                        switch(jComboBoxJenisObat.getSelectedIndex()){
                            case 0:
                                jenisObat = "Tablet/Kapsul/Pil";
                                break;
                            case 1:
                                jenisObat = "Sirup/Suspensi/Emulsi";
                                break;
                            default:
                                jenisObat = "Lainnya";
                                break;
                        }
                        obat = new Obat(jTextidObat.getText(),jTextNamaObat.getText(),jenisObat, Integer.parseInt(jTextStokObat.getText()),Double.parseDouble(jTextHargaSatuanObat.getText()));
                        obatControl.insertDataObat(obat);
                        ShowTableObat();
                        ClearTextMenuObat();
                        setEnDisMenuObat(false);
                    }else JOptionPane.showMessageDialog(null, "ID obat sudah terdaftar!");
                    
                }
            }catch(ExceptionObat e){
                JOptionPane.showMessageDialog(null,e.getMessage());        
            }catch(NumberFormatException f){
                JOptionPane.showMessageDialog(null,f.getMessage() + "Inputan hanya bisa berupa angka!"); 
            }
        }   
    }//GEN-LAST:event_jButtonTambah3ActionPerformed

    private void jButtonUbah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbah3ActionPerformed
        try{
            if (jTableObat.getSelectionModel().isSelectionEmpty()== true ){
                JOptionPane.showMessageDialog(null,"Silahkan pilih data yang hendak di ubah!");
            }else if (jButtonUbah3.getText()=="UBAH"){
                setEnDisMenuObat(true);
                jButtonUbah3.setText("SIMPAN");
                jButtonTambah3.setEnabled(false);
                jButtonHapus3.setEnabled(false);
                jTextidObat.setEnabled(false);
            }else if (jButtonUbah3.getText()=="SIMPAN"){
                String IdObat= jTableObat.getValueAt(jTableObat.getSelectedRow(),0).toString();
                if (cekKosongObat()==false){
                    String jenisObat = "";
                    switch(jComboBoxJenisObat.getSelectedIndex()){
                        case 0:
                            jenisObat = "Tablet/Kapsul/Pil";
                            break;
                        case 1:
                            jenisObat = "Sirup/Suspensi/Emulsi";
                            break;
                        default:
                            jenisObat = "Lainnya";
                            break;
                    }
                    int option = JOptionPane.showConfirmDialog(null, "Konfirmasi", "Update Data Obat : " + IdObat, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(option == JOptionPane.OK_OPTION){
                        obat = new Obat(jTextidObat.getText(),jTextNamaObat.getText(),jenisObat,Integer.parseInt(jTextStokObat.getText()),Double.parseDouble(jTextHargaSatuanObat.getText()));
                        obatControl.updateDataObat(obat, IdObat);
                    }
                    else if(option == JOptionPane.CANCEL_OPTION){}
                }
                ShowTableObat();
                ShowTableObatTransaksi();
                ClearTextMenuObat();
                setEnDisMenuObat(false);
            }
         }catch(ExceptionObat e){
             JOptionPane.showMessageDialog(null,e.getMessage());        
        }catch(NumberFormatException f){
                JOptionPane.showMessageDialog(null,f.getMessage() + "Inputan hanya bisa berupa angka!"); 
            }
    }//GEN-LAST:event_jButtonUbah3ActionPerformed

    private void jButtonHapus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapus3ActionPerformed
        if (jTableObat.getSelectionModel().isSelectionEmpty()== true ){
                JOptionPane.showMessageDialog(null,"Silahkan pilih data yang ingin di hapus!");
        }else{
            int option = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Hapus data" + jTextidObat.getText(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.OK_OPTION){
                obatControl.deleteDataObat(jTextidObat.getText());
            }else if(option == JOptionPane.CANCEL_OPTION){}
            ShowTableObat();
            ClearTextMenuObat();
            setEnDisMenuObat(false);
        }
    }//GEN-LAST:event_jButtonHapus3ActionPerformed

    private void jButtonBatal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBatal3ActionPerformed
        ShowTableObat();
        ClearTextMenuObat();
        setEnDisMenuObat(false);
    }//GEN-LAST:event_jButtonBatal3ActionPerformed

    private void jTextCariObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCariObatKeyReleased
        if(jTextCariObat.getText().isEmpty()==true){
            ShowTableObat();
        }else{
            ShowTableObat2();
        }
    }//GEN-LAST:event_jTextCariObatKeyReleased

    private void jTableObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableObatMouseClicked
        selectDataObat();
    }//GEN-LAST:event_jTableObatMouseClicked

    private void jTableApotekerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableApotekerMouseClicked
        selectDataApoteker();
    }//GEN-LAST:event_jTableApotekerMouseClicked

    private void jTextCariApotekerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCariApotekerKeyReleased
        if(jTextCariApoteker.getText().isEmpty()==true){
            ShowTableApoteker();
        }else{
            ShowTableApoteker2();
        }
    }//GEN-LAST:event_jTextCariApotekerKeyReleased

    private void jButtonTambah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambah2ActionPerformed
        if (jButtonTambah2.getText() == "TAMBAH"){
            ClearTextMenuApoteker();
            setEnDisMenuApoteker(true);
            jButtonTambah2.setText("SIMPAN");
            jButtonUbah2.setEnabled(false);
            jButtonHapus2.setEnabled(false);
        }else if (jButtonTambah2.getText() == "SIMPAN"){
            try{
                if (cekKosongApoteker()==false){
                    if(apotekerControl.namaApoteker(Integer.parseInt(jTextNIKApoteker.getText())) == ""){
                        String genderApoteker = "";
                        switch(jComboBoxGender.getSelectedIndex()){
                            case 0:
                                genderApoteker = "Laki - Laki";
                                break;
                            case 1:
                                genderApoteker = "Perempuan";
                                break;
                        }
                        apoteker = new Apoteker(Integer.parseInt(jTextNIKApoteker.getText()), jTextNamaApoteker1.getText(), genderApoteker, jTextKontakApoteker.getText(), jPasswordApoteker.getText());
                        apotekerControl.insertDataApoteker(apoteker);
                        ShowTableApoteker();
                        ClearTextMenuApoteker();
                        setEnDisMenuApoteker(false);
                    }else JOptionPane.showMessageDialog(null, "NIK sudah terdaftar!"); 
                }
            }catch(ExceptionApoteker e){
                JOptionPane.showMessageDialog(null,e.getMessage());        
            }catch(NumberFormatException f){
                JOptionPane.showMessageDialog(null,f.getMessage() + " Inputan hanya bisa berupa angka!"); 
            }
        }   
    }//GEN-LAST:event_jButtonTambah2ActionPerformed

    private void jButtonUbah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbah2ActionPerformed
        try{
            if (jTableApoteker.getSelectionModel().isSelectionEmpty()== true ){
                JOptionPane.showMessageDialog(null,"Silahkan pilih data yang hendak di ubah");
            }else if (jButtonUbah2.getText()=="UBAH"){
                setEnDisMenuApoteker(true);
                jButtonUbah2.setText("SIMPAN");
                jButtonTambah2.setEnabled(false);
                jButtonHapus2.setEnabled(false);
                jTextNIKApoteker.setEnabled(false);
            }else if (jButtonUbah2.getText()=="SIMPAN"){
                String idApoteker = jTableApoteker.getValueAt(jTableApoteker.getSelectedRow(),0).toString();
                if (cekKosongApoteker()==false){
                    
                    String genderApoteker = "";
                    switch(jComboBoxGender.getSelectedIndex()){
                        case 0:
                            genderApoteker = "Laki - Laki";
                            break;
                        case 1:
                            genderApoteker = "Perempuan";
                            break;
                    }
                    int option = JOptionPane.showConfirmDialog(null, "Konfirmasi", "Update Data Apoteker " + jTextNamaApoteker1.getText(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(option == JOptionPane.OK_OPTION){
                        apoteker = new Apoteker(Integer.parseInt(jTextNIKApoteker.getText()), jTextNamaApoteker1.getText(), genderApoteker, jTextKontakApoteker.getText(), jPasswordApoteker.getText());
                        apotekerControl.updateDataApoteker(apoteker, Integer.parseInt(idApoteker));
                    }
                    else if(option == JOptionPane.CANCEL_OPTION){}
                }
                ShowTableApoteker();
                ClearTextMenuApoteker();
                setEnDisMenuApoteker(false);
            }
         }catch(ExceptionApoteker e){
             JOptionPane.showMessageDialog(null,e.getMessage());        
         }catch(NumberFormatException f){
                JOptionPane.showMessageDialog(null,f.getMessage() + "Inputan hanya bisa berupa angka!"); 
            }
    }//GEN-LAST:event_jButtonUbah2ActionPerformed

    private void jButtonHapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapus2ActionPerformed
        if (jTableApoteker.getSelectionModel().isSelectionEmpty()== true ){
                JOptionPane.showMessageDialog(null,"Silahkan pilih data yang ingin di hapus!");
        }else{
            int option = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Hapus data" + jTextNamaApoteker1.getText(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.OK_OPTION){
                apotekerControl.deleteDataApoteker(Integer.parseInt(jTextNIKApoteker.getText()));
            }else if(option == JOptionPane.CANCEL_OPTION){}
            ShowTableApoteker();
            ClearTextMenuApoteker();
            setEnDisMenuApoteker(false);
        }
    }//GEN-LAST:event_jButtonHapus2ActionPerformed

    private void jButtonBatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBatal2ActionPerformed
        ShowTableApoteker();
        ClearTextMenuApoteker();
        setEnDisMenuApoteker(false);
    }//GEN-LAST:event_jButtonBatal2ActionPerformed

    private void jButtonTambah4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambah4ActionPerformed
        if(jButtonTambah4.getText()=="TAMBAH"){
            ClearTextMenuTransaksi();
            setEnDisMenuTransaksi(true);
            tambahTransaksi();
            jButtonTambah4.setText("SIMPAN");
            jButtonTambah4.setEnabled(false);
       }else if(jButtonTambah4.getText()=="SIMPAN"){        
            try{
                if (cekKosong()==false){
                    String jenisObat = "";
                    switch(jComboBoxJenisObat.getSelectedIndex()){
                        case 0:
                            jenisObat = "Tablet/Kapsul/Pil";
                            break;
                        case 1:
                            jenisObat = "Sirup/Suspensi/Emulsi";
                            break;
                        default:
                            jenisObat = "Lainnya";
                            break;
                    }
                    String namaMerkObat = jTextIdObat4.getText() + " - " + jTextNamaObat4.getText();
                    transaksi = new Transaksi(jTextNoTransaksi.getText(), jTextNamaPembeli.getText(), jTextTanggalPembelian.getText(), Integer.parseInt(jTextJumlahObat.getText()), jTextNamaApoteker4.getText(), namaMerkObat, jenisObat, Double.parseDouble(jTextTotalHarga.getText()));
                    transaksiControl.insertDataTransaksi(transaksi);
                    
                    int sisaObat = Integer.parseInt(jTextStokObat4.getText()) - Integer.parseInt(jTextJumlahObat.getText());
                    obat = new Obat(jTextIdObat4.getText(),jTextNamaObat4.getText(),jenisObat, sisaObat,Double.parseDouble(jTextHargaSatuanObat4.getText()));
                    obatControl.updateDataObat(obat, jTextIdObat4.getText());
                                    
                    ShowTableTransaksi();
                    ShowTableObatTransaksi();
                    ClearTextMenuTransaksi();
                    setEnDisMenuTransaksi(false);                  
                }
            }catch(ExceptionTransaksi e){
                JOptionPane.showMessageDialog(null,e.getMessage()); 
            }catch(NumberFormatException f){
                JOptionPane.showMessageDialog(null,f.getMessage() + "Inputan hanya bisa berupa angka!"); 
            }
        }
    }//GEN-LAST:event_jButtonTambah4ActionPerformed

    private void jButtonBatal4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBatal4ActionPerformed
        // TODO add your handling code here:
        ShowTableTransaksi();
        ClearTextMenuTransaksi();
        setEnDisMenuTransaksi(false);
    }//GEN-LAST:event_jButtonBatal4ActionPerformed

    private void jTextCariObat4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCariObat4KeyReleased
        // TODO add your handling code here:
        if(jTextCariObat4.getText().isEmpty()==true){
            ShowTableObatTransaksi();
        }else{
            ShowTableObatTransaksi2();
        }
    }//GEN-LAST:event_jTextCariObat4KeyReleased

    private void jTextCariTransaksiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCariTransaksiKeyReleased
        // TODO add your handling code here:
        if(jTextCariTransaksi.getText().isEmpty()==true){
            ShowTableTransaksi();
        }else{
            ShowTableTransaksi2();
        }
    }//GEN-LAST:event_jTextCariTransaksiKeyReleased

    private void jTextJumlahUangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextJumlahUangKeyReleased
        // TODO add your handling code here:
        try{
            if(jTextTotalHarga.getText().isEmpty() == false){
                double kembalian;
                kembalian = Double.parseDouble(jTextJumlahUang.getText()) - Double.parseDouble(jTextTotalHarga.getText());
                if(kembalian<0){
                    jButtonTambah4.setEnabled(false);
                }
                else if(kembalian>=0){
                    jButtonTambah4.setEnabled(true);
                }
                jTextKembalian.setText(String.valueOf(kembalian));
            }
            else{
                JOptionPane.showMessageDialog(null,"Masukan jumlah obat terlebih dahulu!");
                jTextJumlahUang.setText("");
                jTextKembalian.setText("");
            }
        }catch(NumberFormatException e){
            if(jTextJumlahUang.getText().isEmpty() == false) JOptionPane.showMessageDialog(null,"Inputan hanya bisa berupa angka!");
            jTextJumlahUang.setText("");
            jTextKembalian.setText("");
        }
    }//GEN-LAST:event_jTextJumlahUangKeyReleased

    private void jTextJumlahObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextJumlahObatKeyReleased
        // TODO add your handling code here:
        try{
            if(Integer.parseInt(jTextStokObat4.getText()) - Integer.parseInt(jTextJumlahObat.getText()) < 0){
                JOptionPane.showMessageDialog(null,"Jumlah obat yang di-input melebihi batas stok!");
                jTextJumlahObat.setText("");
                jTextTotalHarga.setText("");
                jTextJumlahUang.setText("");
                jTextKembalian.setText("");
            }else{
                double totalHarga;
                totalHarga = Double.parseDouble(jTextHargaSatuanObat4.getText()) * Integer.parseInt(jTextJumlahObat.getText());
                jTextTotalHarga.setText(String.valueOf(totalHarga));
            }
        }catch(NumberFormatException e){
                if(jTextJumlahObat.getText().isEmpty() == false) JOptionPane.showMessageDialog(null,"Inputan hanya bisa berupa angka!");
                jTextTotalHarga.setText("");
                jTextJumlahObat.setText("");
                jTextJumlahUang.setText("");
                jTextKembalian.setText("");
        }
        
    }//GEN-LAST:event_jTextJumlahObatKeyReleased

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Log Out", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(option == JOptionPane.OK_OPTION){
            new Login().setVisible(true);
            this.setVisible(false);
        }
        else if(option == JOptionPane.CANCEL_OPTION){}
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jComboBoxJenisObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxJenisObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxJenisObatActionPerformed

    private void jTableObatTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableObatTransaksiMouseClicked
        // TODO add your handling code here:
        jTextTotalHarga.setText("");
        jTextJumlahObat.setText("");
        jTextJumlahUang.setText("");
        jTextKembalian.setText("");

        jTextJumlahObat.setEnabled(true);
        jTextJumlahUang.setEnabled(true);
        selectDataObatTransaksi();
    }//GEN-LAST:event_jTableObatTransaksiMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard(nik).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonBatal2;
    private javax.swing.JButton jButtonBatal3;
    private javax.swing.JButton jButtonBatal4;
    private javax.swing.JButton jButtonHapus2;
    private javax.swing.JButton jButtonHapus3;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonTambah2;
    private javax.swing.JButton jButtonTambah3;
    private javax.swing.JButton jButtonTambah4;
    private javax.swing.JButton jButtonUbah2;
    private javax.swing.JButton jButtonUbah3;
    private javax.swing.JComboBox<String> jComboBoxGender;
    private javax.swing.JComboBox<String> jComboBoxJenisObat;
    private javax.swing.JComboBox<String> jComboBoxJenisObat4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordApoteker;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTableApoteker;
    private javax.swing.JTable jTableObat;
    private javax.swing.JTable jTableObatTransaksi;
    private javax.swing.JTable jTableTransaksi;
    private javax.swing.JTextField jTextCariApoteker;
    private javax.swing.JTextField jTextCariObat;
    private javax.swing.JTextField jTextCariObat4;
    private javax.swing.JTextField jTextCariTransaksi;
    private javax.swing.JTextField jTextHargaSatuanObat;
    private javax.swing.JTextField jTextHargaSatuanObat4;
    private javax.swing.JTextField jTextIdObat4;
    private javax.swing.JTextField jTextJumlahObat;
    private javax.swing.JTextField jTextJumlahUang;
    private javax.swing.JTextField jTextKembalian;
    private javax.swing.JTextField jTextKontakApoteker;
    private javax.swing.JTextField jTextNIKApoteker;
    private javax.swing.JTextField jTextNamaApoteker1;
    private javax.swing.JTextField jTextNamaApoteker4;
    private javax.swing.JTextField jTextNamaObat;
    private javax.swing.JTextField jTextNamaObat4;
    private javax.swing.JTextField jTextNamaPembeli;
    private javax.swing.JTextField jTextNoTransaksi;
    private javax.swing.JTextField jTextStokObat;
    private javax.swing.JTextField jTextStokObat4;
    private javax.swing.JTextField jTextTanggalPembelian;
    private javax.swing.JTextField jTextTotalHarga;
    private javax.swing.JTextField jTextidObat;
    private javax.swing.JTextField jTxtTotalApoteker_Dashboard;
    private javax.swing.JTextField jTxtTotalJenisObat_Dashboard;
    private javax.swing.JTextField jTxtTotalTransaksi_Dashboard;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlCard2;
    private javax.swing.JPanel pnlCard3;
    private javax.swing.JPanel pnlCard4;
    private javax.swing.JPanel pnlCards;
    // End of variables declaration//GEN-END:variables
}
