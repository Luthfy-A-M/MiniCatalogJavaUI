/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.File;
import java.util.Calendar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author luthf
 */
public class FXMLDaftarBukuController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField judulTextField;
    @FXML
    private TextField namaFileTextField;
     @FXML
    private TextField Buku;
    @FXML
    private TextField Halamanbuku;
    @FXML
    private TextField Hasilny;
    @FXML
    private Button Open;
    @FXML
    private Button ShowEdit;
    @FXML
    private Label hall;
    @FXML
    private Button Edit;
    @FXML
    private Button halb;
    @FXML
    private Button Quit;
    @FXML
    private TextField halt;
    @FXML
    private TextField EditData2;
    @FXML
    private TextField EditData1;
    @FXML
    private TextField JumlahData;
    @FXML 
    private TextField bukuname;
    @FXML
    private TableView<Catalog> layarhasil;
    @FXML
    private TableColumn<Catalog, String> tabelbuku;
    @FXML
    private TableColumn<Catalog, Integer> tabelhalamanbuku;
    @FXML
    private TableColumn<Catalog, String> tabeltanggal;
    @FXML
    private TableColumn<Catalog, Integer> halamanterbaca;
    CatalogList catlist= new CatalogList();
    boolean save=false;
    String destinasi,hasildate;
    File file;
    SimpleStringProperty data;
    
    
     Desktop desktop = Desktop.getDesktop();
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    EditData1.setVisible(false);//hide
    EditData2.setVisible(false);
    Edit.setVisible(false);
    halb.setVisible(false);
    halt.setVisible(false);
    hall.setVisible(false);//till 
    halamanterbaca.setCellValueFactory(cellData->cellData.getValue().getJumlahHistory().asObject());
    tabelbuku.setCellValueFactory(cellData->cellData.getValue().getnama());
    tabelhalamanbuku.setCellValueFactory(cellData->cellData.getValue().gethal().asObject());
    tabeltanggal.setCellValueFactory(cellData->cellData.getValue().gettanggal());
    catlist.load();
    layarhasil.setItems(catlist.getlist());
    
    
        }
    //Show The Edit Form
     @FXML
    void ShoweditAction(ActionEvent event) {
    EditData1.setVisible(true);
    EditData2.setVisible(true);
    Edit.setVisible(true);
    
    }
    // Do edit Function
    @FXML
    void editAction(ActionEvent event) {
    catlist.load();
         
             ObservableList<Catalog> pos = layarhasil.getSelectionModel().getSelectedItems();
             Catalog temp = new Catalog(EditData1.getText(),Integer.parseInt(EditData2.getText()),pos.get(0).getdest().getValue(),pos.get(0).gettanggal().getValue());
             SimpleStringProperty[] hist = catlist.list.get(catlist.Searchindex(pos.get(0).getdest().getValue())).gethistorycat();
             catlist.list.set(catlist.Searchindex(pos.get(0).getdest().getValue()),temp);
             catlist.list.get(catlist.Searchindex(pos.get(0).getdest().getValue())).sethistory(hist);
             catlist.save();
             layarhasil.setItems(catlist.getlist());
             EditData1.setVisible(false);
             EditData2.setVisible(false);
             Edit.setVisible(false);
    }
    @FXML //delete
    void deleteAction(ActionEvent event) {
    catlist.load();
             ObservableList<Catalog> pos = layarhasil.getSelectionModel().getSelectedItems();
             data = pos.get(0).getdest();
             catlist.list.remove(catlist.Searchindex(data.getValue()));
             catlist.save();
             layarhasil.setItems(catlist.getlist());
    }
    @FXML //open the boook 
    void openAction(ActionEvent event) {
         try {
             Date date= new Date();//date by adhy
             catlist.load();
             int jumlahhalaman=0;
             ObservableList<Catalog> pos = layarhasil.getSelectionModel().getSelectedItems();
             data = pos.get(0).getdest();
             String dir = data.get();
             System.out.println(catlist.Searchindex(data.getValue()));
             file = new File(dir);
             if(file.exists())desktop.open(file);
             String tahun=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
             hasildate = Integer.toString(date.getDate())+"/"+Integer.toString(date.getMonth()+1)+"/"+tahun;
             catlist.list.get(catlist.Searchindex(data.getValue())).settanggal(hasildate);
             catlist.save();
             halb.setVisible(true);
             halt.setVisible(true);
             hall.setVisible(true);
             layarhasil.setItems(catlist.getlist());
          } catch (IOException ex) {
             Logger.getLogger(FXMLDaftarBukuController.class.getName()).log(Level.SEVERE, null, ex);
         }   
    }
    @FXML //halaman yang dibaca
    void halAction(ActionEvent event){
        catlist.load();
        System.out.println(data.getValue());
        System.out.println(halt.getText());
        System.out.println(hasildate);
        catlist.addhistory(data.getValue(),halt.getText()+"/"+hasildate);
        catlist.save();
        layarhasil.setItems(catlist.getlist());
        halb.setVisible(false);
        halt.setVisible(false);
        hall.setText("Menyimpan Data Berhasil");
        halt.setText(null);
    }
    
@FXML
    private void Home(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_page_scene);
        home_stage.show();
    }  
    @FXML //pindah ke history fxml
    private void ShowHistory(ActionEvent event) throws IOException {
        catlist.load();
        ObservableList<Catalog> pos = layarhasil.getSelectionModel().getSelectedItems();
        data = pos.get(0).getdest();
        int a = catlist.Searchindex(data.getValue());
        System.out.println(data);
        System.out.println(a);
        catlist.setlistH(a);
        catlist.saveH();
        Parent add_HistoryBuku = FXMLLoader.load(getClass().getResource("FXMLHistoryBuku.fxml"));
        Scene add_HistoryBuku_Scene = new Scene(add_HistoryBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_HistoryBuku_Scene);
        app_stage.show();
    }
    @FXML //Search by name
    private void SearchbynameAction(ActionEvent event)throws IOException{
        if(bukuname.getText()==null){
            System.out.println("Isi Nama Buku");
        }
        else{
        catlist.load();
        Date date=new Date();
        String dir =catlist.list.get(catlist.Searchbyname(bukuname.getText())).getdest().getValue(); 
        data= new SimpleStringProperty(dir);
        file = new File(dir);
        if(file.exists())desktop.open(file);
             String tahun=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
             hasildate = Integer.toString(date.getDate())+"/"+Integer.toString(date.getMonth()+1)+"/"+tahun;
             System.out.println(hasildate);
             catlist.list.get(catlist.Searchbyname(bukuname.getText())).settanggal(hasildate); 
             catlist.save();
        bukuname.setText(null);
        halb.setVisible(true);
        halt.setVisible(true);
        hall.setVisible(true);
        }
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
    

    @FXML
    private void QuitButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) Quit.getScene().getWindow();
        stage.close();
    }
    }


