/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productos;

import controller.signIn.SignInFormController;
import static controller.signIn.SignInFormController.Auser;
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
import javax.swing.JOptionPane;
import model.ProductoDAO;
import model.carritoDAO;

/**
 *
 * @author danie
 */
public class tableviewController implements Initializable {
    
    model.historialDAO historial = new model.historialDAO();
    
    public carritoDAO lista = new carritoDAO();
    public static model.Producto pila = new model.Producto();
    public static model.Producto mostrar = new model.Producto();
    
    @FXML
    private TableView<model.Producto> tabla;
    
    @FXML
    private TableColumn<model.Producto, String> User;
    
    @FXML
    private TableColumn<model.Producto, String> Nombre;
    
    @FXML
    private TableColumn<model.Producto, String> Descripcion;
    
    @FXML
    private TableColumn<model.Producto, String> Precio;
    
    public static ObservableList<model.Producto> productos = FXCollections.observableArrayList();
    
    @FXML
    private Button btnRegresar, btnInicio, btnVender, btnClose, btnVerproducto, btnPcarrito, btnCarrito, btnHistorial;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnRegresar)){
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnInicio)){
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnVender)){
            LoadStage("/main/productos/IngresarProductos.fxml", e);
        }
        if(evt.equals(btnClose)){
            JOptionPane.showMessageDialog(null, "Volviendo a la pagina de Login");
            LoadStage("/main/MainView.fxml", e);
        }
        if(evt.equals(btnVerproducto)){
            String verprod = tabla.getSelectionModel().getSelectedItem().getNombre();
            for(model.Producto ver : ProductoDAO.pila ){
                if(ver.getNombre().equals(verprod)){
                    mostrar = ver;
                    LoadStage("/main/productos/visualizador.fxml", e);
                }
            }    
        }
        if(evt.equals(btnCarrito)){
            String verprod = tabla.getSelectionModel().getSelectedItem().getNombre();
            for(model.Producto ver : ProductoDAO.pila ){
                if(ver.getNombre().equals(verprod)){
                    mostrar = ver;
                }
            }    
            if(!mostrar.getUser().equals(Auser)){
                lista.getCrearnodo(mostrar.getLinkImage(), mostrar.getNombre(), mostrar.getDescripcion(), mostrar.getUser()
                    , mostrar.getClase(), mostrar.getPrecio(), SignInFormController.Auser);
            }else{
                JOptionPane.showMessageDialog(null, "No puedes comprar un producto que publicaste!");
            }
        }
        if(evt.equals(btnPcarrito)){
            lista.getCarrito();
            LoadStage("/main/productos/carrito.fxml", e);
        }
        if(evt.equals(btnHistorial)){
            historial.getHistorial();
            LoadStage("/main/productos/historial.fxml", e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User.setCellValueFactory(new PropertyValueFactory<model.Producto, String>("User"));
        Nombre.setCellValueFactory(new PropertyValueFactory<model.Producto, String>("Nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<model.Producto, String>("Descripcion"));
        Precio.setCellValueFactory(new PropertyValueFactory<model.Producto, String>("Precio"));
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
