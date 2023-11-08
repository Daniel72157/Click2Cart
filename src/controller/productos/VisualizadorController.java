/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.productos;

import static controller.productos.IngresarProductosController.ima;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class VisualizadorController implements Initializable {
    
    private model.Producto p = tableviewController.mostrar;
    
    @FXML
    private Label lbNom, lbDesc, lbValor;
    
    @FXML
    private ImageView image;
    
    @FXML
    private void actionEvent(ActionEvent e){
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNom.setText(p.getNombre());
        lbDesc.setText(p.getDescripcion());
        lbValor.setText(String.valueOf(p.getPrecio()));
        Image show = new Image(p.getLinkImage());
        image.setImage(show);
        // TODO
    }    
    
}
