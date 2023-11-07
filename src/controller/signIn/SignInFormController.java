/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.signIn;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import model.UsersDAO;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class SignInFormController implements Initializable {
    
    public static String Auser;
    
    private UsersDAO model = new UsersDAO();
    
    @FXML
    private TextField txtUserSignIn, txtPasswordSignInMask;
    
    @FXML
    private PasswordField txtPasswordSignIn;
    
    @FXML
    private CheckBox checkViewPassSignIn;
    
    @FXML
    private Button btnSignIn, btnClean;
    
    @FXML
    public void eventKey(KeyEvent e){
        
        String c = e.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            e.consume();
        }
        
    }
    @FXML
    private void actionEvent(ActionEvent event){
        Object evt = event.getSource();
        
        if(evt.equals(btnSignIn)){
            if(!txtUserSignIn.getText().isEmpty() && !txtPasswordSignIn.getText().isEmpty()){
                String user = txtUserSignIn.getText();
                String pass = txtPasswordSignIn.getText();
                
                int state = model.getDatos(user, pass);
                
                if(state != -1){
                    if(state == 1){
                        Auser = txtUserSignIn.getText();
                        JOptionPane.showMessageDialog(null, "Datos correctos, puede ingresar a la tienda");
                        loadStage("/main/Principal.fxml", event);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al iniciar sesion, datos de acceso incorrectos");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion, ingrese los datos del usuario");
            }
        }else if(evt.equals(btnClean)){
            txtUserSignIn.setText("");
            txtPasswordSignIn.setText("");
        }
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        maskPassword(txtPasswordSignIn, txtPasswordSignInMask, checkViewPassSignIn);
        
        // TODO
    }    
    
    private void maskPassword(PasswordField pass, TextField text, CheckBox check){
        
        text.setVisible(false);
        text.setManaged(false);
        
        text.managedProperty().bind(check.selectedProperty());
        text.visibleProperty().bind(check.selectedProperty());
        
        text.textProperty().bindBidirectional(pass.textProperty());
        
    }
    private void loadStage(String url, Event event){
        
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
            Logger.getLogger(SignInFormController.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
    
}
