/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.signUp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import model.Users;
import model.UsersDAO;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class SignUpFormController implements Initializable {
    
    private UsersDAO model = new UsersDAO();
    
    @FXML
    private TextField txtUserSignUp, txtPasswordSignUpMask;
    
    @FXML
    private PasswordField txtPasswordSignUp;
    
    @FXML
    private CheckBox checkViewPassSignUp;
    
    @FXML
    private Button btnSignUp, btnClean;
    
    @FXML
    public void eventKey(KeyEvent e){
        
        String c = e.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            e.consume();
        }     
    }
    public void actionEvent(ActionEvent e){
        
        Object evt = e.getSource();
        
        if(evt.equals(btnSignUp)){
            String user = txtUserSignUp.getText();
            String pass = txtPasswordSignUp.getText();
            model.setDatos(user, pass);
            txtUserSignUp.setText("");
            txtPasswordSignUp.setText("");
        }else if(evt.equals(btnClean)){
            txtUserSignUp.setText("");
            txtPasswordSignUp.setText("");
        }     
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        maskPassword(txtPasswordSignUp, txtPasswordSignUpMask, checkViewPassSignUp);
        
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
