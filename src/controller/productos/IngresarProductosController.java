/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.productos;

import controller.signIn.SignInFormController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    private Button btnIngresar;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnIngresar)){
            String clase = cbTipo.getSelectionModel().getSelectedItem().toString();
            float precio = Float.parseFloat(txtPrecio.getText());
            pila.setPush("Nulo", txtNom.getText(), txtDesc.getText(), SignInFormController.Auser, clase, precio);
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
    
}
