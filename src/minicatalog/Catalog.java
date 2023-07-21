/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;

import java.util.Arrays;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author luthf
 */
public class Catalog {
    SimpleStringProperty nama,tanggal,destfile;
    SimpleIntegerProperty jumlahhalaman;
    SimpleStringProperty[] history= new SimpleStringProperty[100]; //format jumlahhalaman/hari/bulan/tahun
    
    public Catalog(String _nama,int _jumlahhalaman,String _destinasi,String _tanggal){
        this.nama=new SimpleStringProperty(_nama);
        this.jumlahhalaman=new SimpleIntegerProperty(_jumlahhalaman);
        this.destfile=new SimpleStringProperty(_destinasi);
        this.tanggal=new SimpleStringProperty(_tanggal);
    }
    public void settanggal(String _tanggal){
        this.tanggal=new SimpleStringProperty(_tanggal);
    }
    public SimpleStringProperty gettanggal(){
        return tanggal;
    }
   
    
    public SimpleStringProperty getnama(){
        return nama;
    } 
    public SimpleStringProperty getdest(){
        return destfile;
    }
    public SimpleIntegerProperty gethal(){
        return jumlahhalaman;
    }
     public String[] gethistory(){
         String[] hasil=new String[100];
         for(int i=0;i<history.length;i++){
             if (history[i]!=null){
             hasil[i]=history[i].getValue();
        }
             else {return hasil;}
    }
         return hasil;
     }
    public void CleanHistory(){
        history=new SimpleStringProperty[100];               
    }
    public void addHistory(String _history){
        int k = _history.length();
                for(int i = 0;i<k;i++){
                    if(history[i]==null){
                        history[i]=new SimpleStringProperty(_history);
                        k=i;
                    }
                }    
    }
     public SimpleIntegerProperty getJumlahHistory(){
      int k = history.length;
        int j= 100;
        String hasil="";
        int hasil1=0;
        for(int l = 0;l<k;l++){
        if(history[l]==null){
                        k=l;
                }
        else{
            for(int i = 0;i<history[l].getValue().length();i++){
                    if((history[l].getValue().charAt(i)!='/'&&k!=l)){
                    hasil=hasil+history[l].getValue().charAt(i);
                    }
                    else{
                    i=history[l].getValue().length();
                    }     
        }
            hasil1+=Integer.parseInt(hasil);
            hasil="";
        }
        }
        return new SimpleIntegerProperty(hasil1);
     } 
 public SimpleDoubleProperty getRataHistory(){
      int k = history.length;
        String hasil="";
        double hasil1=0;
        int pembagi=0;
        for(int l = 0;l<k;l++){
        if(history[l]==null){
                        k=l;
                }
        else{
            for(int i = 0;i<history[l].getValue().length();i++){
                    if((history[l].getValue().charAt(i)!='/'&&k!=l)){
                    hasil=hasil+history[l].getValue().charAt(i);
                    }
                    else{
                    i=history[l].getValue().length();
                    }     
        }
            hasil1+=Double.parseDouble(hasil);
            pembagi+=1;
            hasil="";
        }
        }
        return new SimpleDoubleProperty(hasil1/(pembagi));
     } 
public double getMedianHistory(){
      double k = history.length;
       double sort[] = new double[100];
       String hasil="";
       int pembagi=0;
       for(int l = 0;l<k;l++){
       if(history[l]==null){
                        k=l;
                }
        else{
            for(int i = 0;i<history[l].getValue().length();i++){
                    if((history[l].getValue().charAt(i)!='/'&&k!=l)){
                    hasil=hasil+history[l].getValue().charAt(i);
                    }
                    else{
                    i=history[l].getValue().length();
                    }     
        }
            sort[l]=Double.parseDouble(hasil);
            hasil="";
            pembagi+=1;
        }
}
       Arrays.sort(sort);
       double penampung[]= new double[pembagi];
       
       for(int i=0;i<pembagi;i++){
           penampung[i]=sort[sort.length-i-1];
       }
       Arrays.sort(penampung);
       if (penampung.length % 2 == 0){
       return (penampung[penampung.length/2] + penampung[penampung.length/2 - 1])/2;
       }
       else{
       return penampung[penampung.length/2];
       }
        
       
}

public double getmax(){
    double k = history.length;
       double sort[] = new double[100];
       String hasil="";
       int pembagi=0;
       for(int l = 0;l<k;l++){
       if(history[l]==null){
                        k=l;
                }
        else{
            for(int i = 0;i<history[l].getValue().length();i++){
                    if((history[l].getValue().charAt(i)!='/'&&k!=l)){
                    hasil=hasil+history[l].getValue().charAt(i);
                    }
                    else{
                    i=history[l].getValue().length();
                    }     
        }
            sort[l]=Double.parseDouble(hasil);
            hasil="";
            pembagi+=1;
        }
}
       Arrays.sort(sort);  
       return sort[sort.length-1];
}
public double getmin(){
    double k = history.length;
       double sort[] = new double[100];
       String hasil="";
       int pembagi=0;
       for(int l = 0;l<k;l++){
       if(history[l]==null){
                        k=l;
                }
        else{
            for(int i = 0;i<history[l].getValue().length();i++){
                    if((history[l].getValue().charAt(i)!='/'&&k!=l)){
                    hasil=hasil+history[l].getValue().charAt(i);
                    }
                    else{
                    i=history[l].getValue().length();
                    }     
        }
            sort[l]=Double.parseDouble(hasil);
            hasil="";
            pembagi+=1;
        }
}
       Arrays.sort(sort);  
       return sort[sort.length-pembagi];
}
public void sethistory(SimpleStringProperty[] a){
    int i=0;
    while(i<a.length){
        history[i]=a[i];
        i+=1;
    }
}
public SimpleStringProperty[] gethistorycat(){
    return history;
}
}