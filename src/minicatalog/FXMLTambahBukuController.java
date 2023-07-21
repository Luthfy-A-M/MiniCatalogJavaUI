/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Date;


/**
 * FXML Controller class
 *
 * @author luthf
 */
public class FXMLTambahBukuController implements Initializable {

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
    private Button btnTambah;
    @FXML
    private Button Quit;
    @FXML
    private TextField JumlahData;
    @FXML
    private TableView<Catalog> layarhasil;
    @FXML
    private TableColumn<Catalog, String> tabelbuku;
    @FXML
    private TableColumn<Catalog, Integer> tabelhalamanbuku;
    @FXML
    private TableColumn<Catalog, String> tabeldir;
     
    
    CatalogList catlist= new CatalogList();
    boolean save=false;
    String destinasi;
    Date date;
    
    
    @Override //salman
    public void initialize(URL url, ResourceBundle rb) {
    btnTambah.setVisible(false);
    tabelbuku.setCellValueFactory(cellData->cellData.getValue().getnama());
    tabelhalamanbuku.setCellValueFactory(cellData->cellData.getValue().gethal().asObject());
    tabeldir.setCellValueFactory(cellData->cellData.getValue().getdest());
    catlist.load();
    layarhasil.setItems(catlist.getlist());
    }
    
    @FXML//fadil
    private void TambahBuku(ActionEvent event) throws IOException {
        
        FileChooser tambah = new  FileChooser();
        tambah.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDf Files","*.pdf"));
        List <File> daftarTambah = tambah.showOpenMultipleDialog(null);
        daftarTambah.forEach((file) -> {
            destinasi=file.getAbsolutePath();
        });
        btnTambah.setVisible(true);
    }
 
 @FXML//luthfy
    void tambahAction(ActionEvent event) {
        catlist.load();
            catlist.load();
         String namabuku=Buku.getText();
         int halamanbuku=Integer.parseInt(Halamanbuku.getText());
         if((catlist.Searchbyname(namabuku)!=-1)){
         System.out.println("data yang dimasukkan tidak boleh sama");       
         } else if (catlist.Searchindex(destinasi)!=-1) {
        System.out.println("data yang dimasukkan tidak boleh sama");  
         }
        else{
        catlist.addnew(namabuku,halamanbuku,destinasi,"null");
        catlist.save();
        layarhasil.setItems(catlist.getlist());
        Buku.setText(null);
        Halamanbuku.setText(null);
        btnTambah.setVisible(false);
         }
            
    }
@FXML//anwar
    private void Home(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_page_scene);
        home_stage.show();
    }   
    //Home side menu controller fadil
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


