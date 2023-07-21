/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author luthf
 */
public class CatalogList {
 public ObservableList <Catalog> list;
 public SimpleIntegerProperty listH;
 public String extFile;
 public Iterator <Catalog> pointer;
 public CatalogList(){
     list = FXCollections.observableArrayList();
     extFile = "Catalog.xml";
 } 
 public SimpleIntegerProperty getlistH(){
     return listH;
 }
 public ObservableList<Catalog> getlist(){
     return list;
 }
 public void addhistory(String _destinasi,String History){
    list.get(Searchindex(_destinasi)).addHistory(History);
}
public void cleanhistory(String _destinasi){
    list.get(Searchindex(_destinasi)).CleanHistory();
}
public void jumlahhistory(String _destinasi){
    list.get(Searchindex(_destinasi)).getJumlahHistory();
}
 
 public int Searchindex(String parameter){
     int hasil = 0;
     for(int i=0;i<list.size();i++){
         if(!list.get(i).getdest().getValue().equals(parameter)){
         hasil+=1;
         }
         else if(list.get(i).getdest().getValue().equals(parameter)){
         return hasil;
         }
     }
     if(list.get(0).getdest().getValue().equals(parameter)){
     return 0;
     }
     else{
     return -1;
             }
 }
 public int Searchbyname(String Namabuku){
   int hasil = 0;
     for(int i=0;i<list.size();i++){
         if(!list.get(i).getnama().getValue().equals(Namabuku)){
         hasil+=1;    
         }
         else if(list.get(i).getnama().getValue().equals(Namabuku)){
         return hasil;
         }
     }
     if(list.get(0).getnama().getValue().equals(Namabuku)){
     return 0;
     }
     else{
     return -1;
             }
 }
 public void addnew(String _nama,int hal,String dir,String tanggal){
     list.add(new Catalog(_nama,hal,dir,tanggal));
 }
 public void setDummy(){
     list.add(new Catalog ("",0,"",""));
 }

 public void setlistH(int i){
     listH= new SimpleIntegerProperty(i);
 }
 public void setDummyListH(){
     listH = new SimpleIntegerProperty(0);
 }
 public void saveH(){
     XStream xs = new XStream(new StaxDriver());
     String xml = xs.toXML(listH);
     try{
         FileOutputStream out = new FileOutputStream("History.xml");
         byte[] bytes = xml.getBytes("UTF-8");
         out.write(bytes);
         out.close();
     }
     catch(Exception e){
         e.printStackTrace();
     }
 }
 public void loadH(){
     XStream xs = new XStream(new StaxDriver());
     try{
         FileInputStream in = new FileInputStream("History.xml");
         String s="";
         int c= in.read();
         while(c!=-1){
             s+=(char)c;
             c=in.read();
         }
         listH = (SimpleIntegerProperty) xs.fromXML(s);
     }
     catch (Exception e){
 }}
 
 public void save(){
     XStream xs = new XStream(new StaxDriver());
     String xml = xs.toXML(list);
     try{
         FileOutputStream out = new FileOutputStream(extFile);
         byte[] bytes = xml.getBytes("UTF-8");
         out.write(bytes);
         out.close();
     }
     catch(Exception e){
         e.printStackTrace();
     }
 }
 public void load(){
     XStream xs = new XStream(new StaxDriver());
     try{
         FileInputStream in = new FileInputStream(extFile);
         String s="";
         int c= in.read();
         while(c!=-1){
             s+=(char)c;
             c=in.read();
         }
         list = (ObservableList <Catalog>) xs.fromXML(s);
     }
     catch (Exception e){
 }}
 
     
}
