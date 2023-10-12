/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.signIn;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class SignInFormController implements Initializable {
    
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
    
}
