/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

public class ExceptionApoteker extends Exception{
    int e;
    
    public ExceptionApoteker(int e){
        this.e = e;
    }
    
    public String getMessage(){
        String pesan = "";
        
        if (e == 1){
            pesan = "[!] Silahkan isi data dengan lengkap";
        }
        
        return pesan;
    }
}
