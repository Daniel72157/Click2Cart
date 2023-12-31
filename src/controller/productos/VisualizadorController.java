/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class VisualizadorController implements Initializable {
    
    private model.historialDAO historial = new model.historialDAO();
    private model.carritoDAO lista = new model.carritoDAO();
    private model.Producto p = tableviewController.mostrar;
    
    @FXML
    private Label lbNom, lbDesc, lbValor;
    
    @FXML
    private ImageView image;
    
    @FXML
    private Button btnRegresar, btnComprar, btnCarrito, btnPcarrito, btnInicio, btnHistorial;
    
    @FXML
    private void actionEvent(ActionEvent e){
        Object evt = e.getSource();
        if(evt.equals(btnRegresar)){
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnCarrito)){
            if(!p.getUser().equals(Auser)){
                lista.getCrearnodo(p.getLinkImage(), p.getNombre(), p.getDescripcion(), p.getUser()
                    , p.getClase(), p.getPrecio(), SignInFormController.Auser);
            }else{
                JOptionPane.showMessageDialog(null, "No puedes comprar un producto que publicaste!");
            }
        }
        if(evt.equals(btnPcarrito)){
            lista.getCarrito();
            LoadStage("/main/productos/carrito.fxml", e);
        }
        if(evt.equals(btnInicio)){
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnComprar)){
            historial.getCrearnodo(p.getLinkImage(), p.getNombre(), p.getDescripcion()
                    , p.getUser(), p.getClase(), p.getPrecio(), Auser);
            LoadStage("/main/Principal.fxml", e);
        }
        if(evt.equals(btnHistorial)){
            historial.getHistorial();
            LoadStage("/main/productos/historial.fxml", e);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNom.setText(p.getNombre());
        lbDesc.setText(p.getDescripcion());
        lbValor.setText(p.getPrecio());
        Image show = new Image(p.getLinkImage());
        image.setImage(show);
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
            Logger.getLogger(VisualizadorController.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
}
