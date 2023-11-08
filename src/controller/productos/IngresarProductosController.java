/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.productos;

import controller.signIn.SignInFormController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class IngresarProductosController implements Initializable {
    
    model.ProductoDAO pila = new model.ProductoDAO();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<String> cbTipo;
    
    @FXML
    private TextField txtNom, txtPrecio, txtDesc;
    
    @FXML
    private Button btnIngresar, btnRegresar;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnIngresar)){
            String clase = cbTipo.getSelectionModel().getSelectedItem().toString();
            float precio = Float.parseFloat(txtPrecio.getText());
            pila.setPush("Nulo", txtNom.getText(), txtDesc.getText(), SignInFormController.Auser, clase, precio);
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnRegresar)){
            LoadStage("/main/Principal.fxml", e);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items.addAll("Ropa", "Deportes", "Electrodomesticos", "Juguetes", "Tecnologia", "Vehiculos");
        cbTipo.setItems(items);
        // TODO
    }    
    
    private void LoadStage(String url, Event event){
        
        try {
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window;
            stage.hide();
            
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
            
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }        
            });
        }catch ( IOException ex){
            Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
    
}
