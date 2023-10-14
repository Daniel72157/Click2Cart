/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author danie
 */
public class Users {
    
    String user, pass;
    Users sig;
    
    public Users(String user, String pass){
        this.user = user;
        this.pass = pass;
        sig = null;      
    }   
}
