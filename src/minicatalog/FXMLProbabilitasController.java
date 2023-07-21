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
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luthf
 */
public class FXMLProbabilitasController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    TextField peluang;
    @FXML
    TextField sizeof;
    @FXML
    TextField nkali;
    @FXML
    Button bernoulibutton;
    @FXML
    private Button Quit;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    void Switchbernouli(ActionEvent event) {
    Probability prob= new Probability();
    double p = Double.parseDouble(peluang.getText());
    double n = Double.parseDouble(nkali.getText());
    double x = Double.parseDouble(sizeof.getText());
    hasil.setText(""+prob.probabilitasBernouli(p,n,x));
    peluang.setText(null);
    nkali.setText(null);
    sizeof.setText(null);
    }
    @FXML
    void ShowBernouli(ActionEvent event) {
    if(label1.isVisible()){
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        hasil.setVisible(false);
        sizeof.setVisible(false);
        nkali.setVisible(false);
        peluang.setVisible(false);
        bernoulibutton.setVisible(false);
    }
    else{
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        hasil.setVisible(true);
        sizeof.setVisible(true);
        nkali.setVisible(true);
        peluang.setVisible(true);
        bernoulibutton.setVisible(true);
    }
    }
    
@FXML
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
    

    @FXML
    private void QuitButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) Quit.getScene().getWindow();
        stage.close();
    }
    }

