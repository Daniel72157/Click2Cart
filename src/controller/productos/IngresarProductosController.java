/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.productos;

import controller.signIn.SignInFormController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import model.carritoDAO;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class IngresarProductosController implements Initializable {
    
    model.historialDAO historial = new model.historialDAO();
    
    public static String ima;
    public carritoDAO lista = new carritoDAO();
    model.ProductoDAO pila = new model.ProductoDAO();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    List<String> lstFile;
    
    @FXML
    private ComboBox<String> cbTipo;
    
    @FXML
    private TextField txtNom, txtPrecio;
    
    @FXML
    private TextArea txtDesc;
    
    @FXML
    private Button btnIngresar, btnRegresar, btnFoto, btnPcarrito, btnInicio, btnHistorial;
    
    @FXML
    private ImageView image;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnFoto)){
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", lstFile));
            File f = fc.showOpenDialog(null);
            
            if(f != null){
                File imagen = new File(f.getAbsolutePath());
                ima = imagen.toURI().toString();
                Image show = new Image(ima);
                image.setImage(show);
            }
        }
        if(evt.equals(btnIngresar)){
            String clase = cbTipo.getSelectionModel().getSelectedItem();
            String precio = txtPrecio.getText();
            if(ima != null){
                pila.setPush(ima, txtNom.getText(), txtDesc.getText(), SignInFormController.Auser, clase, precio);
                LoadStage("/main/Principal.fxml", e);
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese una imagen para el producto!");
            }
        }
        if(evt.equals(btnRegresar)){
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnPcarrito)){
            lista.getCarrito();
            LoadStage("/main/productos/carrito.fxml", e);
        }
        if(evt.equals(btnInicio)){
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnHistorial)){
            historial.getHistorial();
            LoadStage("/main/productos/historial.fxml", e);
        }
    }
    @FXML
    private void keyEvent(KeyEvent e){
        Object evt = e.getSource();
        if(evt.equals(txtPrecio)){
            if (!Character.isDigit(e.getCharacter().charAt(0))){
                e.consume();
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items.addAll("Ropa", "Deportes", "Electrodomesticos", "Juguetes", "Tecnologia", "Vehiculos");
        cbTipo.setItems(items);
        lstFile = new ArrayList<>();
        lstFile.add("*.jpg");
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
