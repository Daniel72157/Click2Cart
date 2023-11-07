/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ProductoDAO;

/**
 *
 * @author danie
 */
public class tableviewController implements Initializable {
    
    public static ProductoDAO pila = new ProductoDAO();
    
    @FXML
    private TableView<ProductoDAO> tabla;
    
    @FXML
    private TableColumn<ProductoDAO, String> linkImage;
    
    @FXML
    private TableColumn<ProductoDAO, String> Nombre;
    
    @FXML
    private TableColumn<ProductoDAO, String> Descripcion;
    
    @FXML
    private TableColumn<ProductoDAO, Float> Precio;
    
    public static ObservableList<ProductoDAO> productos = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linkImage.setCellValueFactory(new PropertyValueFactory<ProductoDAO, String>("linkImage"));
        Nombre.setCellValueFactory(new PropertyValueFactory<ProductoDAO, String>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<ProductoDAO, String>("Descripcion"));
        Precio.setCellValueFactory(new PropertyValueFactory<ProductoDAO, Float>("Precio"));
        tabla.setItems(productos);
        // TODO
    }    
}
