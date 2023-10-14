/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author danie
 */
public class MainViewController implements Initializable {
    
    @FXML
    private Button btnSignIn, btnSignUp;
    
    @FXML
    private StackPane containerForm;
    
    private VBox signInForm, signUpForm;
    
    @FXML
    private void actionEvent(ActionEvent e){
        
        Object evt = e.getSource();
        
        if(evt.equals(btnSignIn)){
            signInForm.setVisible(true);
            signUpForm.setVisible(false);
        }else if(evt.equals(btnSignUp)){
            signInForm.setVisible(false);
            signUpForm.setVisible(true);
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            signInForm = LoadForm("/main/signIn/SignInForm.fxml");
            signUpForm = LoadForm("/main/signUp/SignUpForm.fxml");
            containerForm.getChildren().addAll(signInForm,signUpForm);
            
            signInForm.setVisible(true);
            signUpForm.setVisible(false);
            
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private VBox LoadForm(String url) throws IOException{
        return (VBox)FXMLLoader.load(getClass().getResource(url));
    } 
    
}
