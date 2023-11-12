/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.productos;

import static controller.productos.tableviewController.mostrar;
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
import model.ProductoDAO;
import model.carrito;
import model.carritoDAO;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class CarritoController implements Initializable {
    
    @FXML
    private TableView<model.carrito> tabla;
    
    @FXML
    private TableColumn<model.carrito, String> User;
    
    @FXML
    private TableColumn<model.carrito, String> Nombre;
    
    @FXML
    private TableColumn<model.carrito, String> Descripcion;
    
    @FXML
    private TableColumn<model.carrito, String> Precio;
    
    public static ObservableList<model.carrito> productos = FXCollections.observableArrayList();
    
    @FXML
    private Button btnRegresar, btnVender, btnInicio, btnHistorial, btnVerproducto;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnRegresar)){
            productos.removeAll(productos);
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnVender)){
            productos.removeAll(productos);
            LoadStage("/main/productos/IngresarProductos.fxml", e);
        }
        if(evt.equals(btnInicio)){
            productos.removeAll(productos);
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnVerproducto)){
            String verprod = tabla.getSelectionModel().getSelectedItem().getNombre();
            carrito ver = carritoDAO.cab;
            productos.removeAll(productos);
            while(ver != null){
                if(ver.getNombre().equals(verprod)){
                    mostrar.setNombre(ver.getNombre());
                    mostrar.setLinkImage(ver.getLinkImage());
                    mostrar.setPrecio(ver.getPrecio());
                    mostrar.setDescripcion(ver.getDescripcion());
                    LoadStage("/main/productos/visualizador.fxml", e);
                }
                ver = ver.sig;
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User.setCellValueFactory(new PropertyValueFactory<model.carrito, String>("User"));
        Nombre.setCellValueFactory(new PropertyValueFactory<model.carrito, String>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<model.carrito, String>("Descripcion"));
        Precio.setCellValueFactory(new PropertyValueFactory<model.carrito, String>("Precio"));
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
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
    
}
