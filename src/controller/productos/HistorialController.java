/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.productos;

import static controller.productos.CarritoController.productos;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class HistorialController implements Initializable {
    
    model.carritoDAO lista = new model.carritoDAO();
    
    @FXML
    private TableView<model.historial> tabla;
    
    @FXML
    private TableColumn<model.historial, String> User;
    
    @FXML
    private TableColumn<model.historial, String> Nombre;
    
    @FXML
    private TableColumn<model.historial, String> Descripcion;
    
    @FXML
    private TableColumn<model.historial, String> Precio;
    
    public static ObservableList<model.historial> productos = FXCollections.observableArrayList();
    
    @FXML
    private Button btnRegresar, btnInicio, btnCarrito, btnVender;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnRegresar)){
            LoadStage("/main/Principal.fxml", e);
            productos.removeAll(productos);
        }
        if(evt.equals(btnCarrito)){
            lista.getCarrito();
            LoadStage("/main/productos/carrito.fxml", e);
            productos.removeAll(productos);
        }
        if(evt.equals(btnVender)){
            LoadStage("/main/productos/IngresarProductos.fxml", e);
            productos.removeAll(productos);
        }
        if(evt.equals(btnInicio)){
            LoadStage("/main/Principal.fxml", e);
            productos.removeAll(productos);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User.setCellValueFactory(new PropertyValueFactory<model.historial, String>("User"));
        Nombre.setCellValueFactory(new PropertyValueFactory<model.historial, String>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<model.historial, String>("Descripcion"));
        Precio.setCellValueFactory(new PropertyValueFactory<model.historial, String>("Precio"));
        tabla.setItems(productos);
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
