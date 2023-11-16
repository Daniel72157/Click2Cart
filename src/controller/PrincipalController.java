/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.productos.tableviewController;
import static controller.productos.tableviewController.productos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import model.carritoDAO;
import model.historialDAO;
/**
 *
 * @author danie
 */
public class PrincipalController implements Initializable {
    
    public carritoDAO lista = new carritoDAO();
    model.ProductoDAO pila = new model.ProductoDAO();
    
    @FXML
    private Button btnClose, btnVender, btnVehiculos, btnJuguetes, btnRopa, btnDeportes, btnElectro, btnTec, btnHistorial, btnPcarrito;
    
    @FXML
    private void actionEvent(ActionEvent e){
        
        historialDAO historial = new historialDAO();
        Object evt = e.getSource();
        
        if(evt.equals(btnClose)){
            JOptionPane.showMessageDialog(null, "Volviendo a la pagina de Login");
            LoadStage("/main/MainView.fxml", e);
        }
        if(evt.equals(btnVender)){
            LoadStage("/main/productos/IngresarProductos.fxml", e);
        }
        if(evt.equals(btnRopa)){
            tableviewController.productos.removeAll(productos);
            pila.getProdu();
            for(model.Producto ropa : model.ProductoDAO.pila){
                if(ropa.getClase().equals("Ropa")){
                    controller.productos.tableviewController.productos.add(ropa);
                }
            }
            LoadStage("/main/productos/tableview.fxml", e);
        }
        if(evt.equals(btnDeportes)){
            tableviewController.productos.removeAll(productos);
            pila.getProdu();
            for(model.Producto deportes : model.ProductoDAO.pila){
                if(deportes.getClase().equals("Deportes")){
                    controller.productos.tableviewController.productos.add(deportes);
                }
            }
            LoadStage("/main/productos/tableview.fxml", e);
        }
        if(evt.equals(btnElectro)){
            tableviewController.productos.removeAll(productos);
            pila.getProdu();
            for(model.Producto electrodomesticos : model.ProductoDAO.pila){
                if(electrodomesticos.getClase().equals("Electrodomesticos")){
                    controller.productos.tableviewController.productos.add(electrodomesticos);
                }
            }
            LoadStage("/main/productos/tableview.fxml", e);
        }
        if(evt.equals(btnJuguetes)){
            tableviewController.productos.removeAll(productos);
            pila.getProdu();
            for(model.Producto juguetes : model.ProductoDAO.pila){
                if(juguetes.getClase().equals("Juguetes")){
                    controller.productos.tableviewController.productos.add(juguetes);
                }
            }
            LoadStage("/main/productos/tableview.fxml", e);
        }
        if(evt.equals(btnTec)){
            tableviewController.productos.removeAll(productos);
            pila.getProdu();
            for(model.Producto tecnologia : model.ProductoDAO.pila){
                if(tecnologia.getClase().equals("Tecnologia")){
                    controller.productos.tableviewController.productos.add(tecnologia);
                }
            }
            LoadStage("/main/productos/tableview.fxml", e);
        }
        if(evt.equals(btnVehiculos)){
            tableviewController.productos.removeAll(productos);
            pila.getProdu();
            for(model.Producto vehiculos : model.ProductoDAO.pila){
                if(vehiculos.getClase().equals("Vehiculos")){
                    controller.productos.tableviewController.productos.add(vehiculos);
                }
            }
            LoadStage("/main/productos/tableview.fxml", e);
        }
        if(evt.equals(btnHistorial)){
            historial.getHistorial();
            LoadStage("/main/productos/historial.fxml", e);
        }
        if(evt.equals(btnPcarrito)){
            lista.getCarrito();
            LoadStage("/main/productos/carrito.fxml", e);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
}
