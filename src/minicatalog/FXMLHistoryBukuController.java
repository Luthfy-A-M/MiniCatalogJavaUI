/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author luthf
 */
public class FXMLHistoryBukuController implements Initializable {

    @FXML
    Label label;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label hasil;
    @FXML
    Label keinginantext;
    @FXML
    Button libarchart;
    @FXML
    BarChart barchart;
    @FXML
    TextField keinginan;
    @FXML
    Button keinginanbutton;
    @FXML
    LineChart linechart;
    
    CatalogList catlist = new CatalogList();
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    catlist.loadH();
     int temp = catlist.listH.getValue();
     catlist = new CatalogList();
     catlist.load();
     label.setText("Statistik Penggunaan Aplikasi Untuk Membaca Buku "+catlist.list.get(temp).getnama().getValue());
     SimpleStringProperty[] a = new SimpleStringProperty[100];
     //adding sample
     /*catlist.addhistory(catlist.list.get(1).getdest().getValue(),("20/07/06/2019"));
     catlist.addhistory(catlist.list.get(1).getdest().getValue(),("10/08/06/2019"));
     catlist.addhistory(catlist.list.get(1).getdest().getValue(),("11/10/06/2019"));
     catlist.addhistory(catlist.list.get(1).getdest().getValue(),("7/11/06/2019"));
     catlist.addhistory(catlist.list.get(1).getdest().getValue(),("14/19/06/2019"));
     catlist.addhistory(catlist.list.get(1).getdest().getValue(),("5/20/06/2019"));
     catlist.addhistory(catlist.list.get(1).getdest().getValue(),("9/27/06/2019"));
     catlist.save();*/
     String[] x = new String[100];
     x = catlist.list.get(temp).gethistory();
     XYChart.Series<String,Integer>series= new XYChart.Series();
     XYChart.Series<String,Integer>series1= new XYChart.Series();
    for(int i=0;i<x.length;i++){
        if(x[i]!=null){
            series.getData().add(new XYChart.Data<>(x[i].substring(x[i].indexOf('/')+1),Integer.parseInt(x[i].substring(0,x[i].indexOf('/')))));
            series1.getData().add(new XYChart.Data<>(x[i].substring(x[i].indexOf('/')+1),Integer.parseInt(x[i].substring(0,x[i].indexOf('/')))));
        }
        else{
            i=x.length;
        }
        }
    barchart.getData().add(series);
    linechart.getData().add(series1);
    }
    
     @FXML //showlinechart or barchart
    void SwitchLineChart(ActionEvent event) {
    if(barchart.isVisible()){
    hasil.setVisible(false);
    keinginan.setVisible(false);
    keinginanbutton.setVisible(false);
    keinginantext.setVisible(false);
    label1.setVisible(false);
    label2.setVisible(false);
    label3.setVisible(false);
    label4.setVisible(false);
    barchart.setVisible(false);
        linechart.setVisible(true);
        libarchart.setText("Show BarChart");
    }else
    {
        hasil.setVisible(false);
        keinginan.setVisible(false);
    keinginanbutton.setVisible(false);
    keinginantext.setVisible(false);
        label1.setVisible(false);
    label2.setVisible(false);
    label3.setVisible(false);
    label4.setVisible(false);
       linechart.setVisible(false);
       barchart.setVisible(true);
       libarchart.setText("Show LineChart"); 
    }    
    catlist.loadH();
    int temp = catlist.listH.getValue();
    catlist = new CatalogList();
    catlist.load();
     label.setText("Statistik Penggunaan Aplikasi Untuk Buku "+catlist.list.get(temp).getnama().getValue());
     
    }
    @FXML //Show Rekomendasi berdasarkan jumlah halaman yang belum terbaca
    void ShowProbabilitas(ActionEvent event) {
    catlist.loadH();
    int temp = catlist.listH.getValue();
    catlist = new CatalogList();
    catlist.load();
    double ingin = Double.parseDouble(keinginan.getText());
    double jumlahTerbaca=catlist.list.get(temp).getJumlahHistory().getValue();
    int jumlahhalaman=catlist.list.get(temp).gethal().getValue();
    hasil.setText("Anda Kami Sarankan Untuk Membaca Setidaknya "+(jumlahhalaman-jumlahTerbaca)/ingin+" Halaman Setiap Anda Membuka Buku Ini ");
    
    
    }
    @FXML//showstatistik{mean,min,max,med
    void ShowStatistika(ActionEvent event) {
    if(label1.isVisible()){
    label1.setVisible(false);
    label2.setVisible(false);
    label3.setVisible(false);
    label4.setVisible(false);
    hasil.setVisible(false);
    keinginan.setVisible(false);
    keinginanbutton.setVisible(false);
    keinginantext.setVisible(false);
    }
    else{
    barchart.setVisible(false);
    linechart.setVisible(false);
    label1.setVisible(true);
    label2.setVisible(true);
    label3.setVisible(true);
    label4.setVisible(true);
    catlist.loadH();
    int temp = catlist.listH.getValue();
    catlist = new CatalogList();
    catlist.load();
    double mean=catlist.list.get(temp).getRataHistory().getValue();
    double median = catlist.list.get(temp).getMedianHistory();
    System.out.println(catlist.list.get(temp).getMedianHistory());
    double max = catlist.list.get(temp).getmax();
    double min = catlist.list.get(temp).getmin();
    double jumlahH=catlist.list.get(temp).getJumlahHistory().getValue();
    int jumhalbuk=catlist.list.get(temp).gethal().getValue();
    label1.setText("Rata Rata History: "+ mean );
    label2.setText("Median History: "+ median );
    label3.setText("Pembacaan Halaman Terbanyak : "+ max );
    label4.setText("Pembacaan Halaman Terdikit: "+ min );
    hasil.setText("Dengan Rata-Rata : "+mean+", Anda Akan Selesai Pada Aktivitas Ke : "+Math.ceil((jumhalbuk-jumlahH)/mean));
    hasil.setVisible(true);
    keinginan.setVisible(true);
    keinginanbutton.setVisible(true);
    keinginantext.setVisible(true);
    }
    }
    
    @FXML//go back
    private void BackAction(ActionEvent event)throws IOException{
        Parent home_page = FXMLLoader.load(getClass().getResource("FXMLDaftarBuku.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_page_scene);
        home_stage.show();
    }
    
@FXML//go home
    private void Home(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_page_scene);
        home_stage.show();
    }  
    //Home side menu controller
    @FXML
    private void TambahBukuButton(ActionEvent event) throws IOException {
    Parent add_TambahBuku = FXMLLoader.load(getClass().getResource("FXMLTambahBuku.fxml"));
        Scene add_TambahBuku_Scene = new Scene(add_TambahBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_TambahBuku_Scene);
        app_stage.show();
    }
    @FXML
    private void DaftarBukuButton(ActionEvent event)throws IOException {
        Parent add_DaftarBuku = FXMLLoader.load(getClass().getResource("FXMLDaftarBuku.fxml"));
        Scene add_DaftarBuku_Scene = new Scene(add_DaftarBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_DaftarBuku_Scene);
        app_stage.show();
    }

    @FXML
    private void HistoryButton(ActionEvent event) throws IOException {
        Parent add_DaftarBuku = FXMLLoader.load(getClass().getResource("FXMLProbabilitas.fxml"));
        Scene add_DaftarBuku_Scene = new Scene(add_DaftarBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_DaftarBuku_Scene);
        app_stage.show();
    }
  
}
