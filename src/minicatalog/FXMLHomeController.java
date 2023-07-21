/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANWAR
 */
public class FXMLHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //HOMEFXML
    @FXML
    private Button Quit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML//go to tambahbuku
    private void TambahBukuButton(ActionEvent event) throws IOException {
    Parent add_TambahBuku = FXMLLoader.load(getClass().getResource("FXMLTambahBuku.fxml"));
        Scene add_TambahBuku_Scene = new Scene(add_TambahBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_TambahBuku_Scene);
        app_stage.show();
    }
    @FXML//go to daftarbuku
    private void DaftarBukuButton(ActionEvent event)throws IOException {
        Parent add_DaftarBuku = FXMLLoader.load(getClass().getResource("FXMLDaftarBuku.fxml"));
        Scene add_DaftarBuku_Scene = new Scene(add_DaftarBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_DaftarBuku_Scene);
        app_stage.show();
    }

    @FXML //go to Extend Menu Probabilitas Bernouli
    private void HistoryButton(ActionEvent event) throws IOException {
        Parent add_DaftarBuku = FXMLLoader.load(getClass().getResource("FXMLProbabilitas.fxml"));
        Scene add_DaftarBuku_Scene = new Scene(add_DaftarBuku);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_DaftarBuku_Scene);
        app_stage.show();
    }
    

    @FXML //
    private void QuitButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) Quit.getScene().getWindow();
        stage.close();
    }
    
}
