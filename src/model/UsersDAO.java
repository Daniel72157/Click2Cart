/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class UsersDAO {
    
    Users cab;
    
    public UsersDAO () { cab = null; }
    
    public void getUser(){
        
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        Users info;
        
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            
            if(connection != null){
                
                Statement datos = connection.createStatement();
                rs = datos.executeQuery("SELECT * FROM accounts");
                
                while(rs.next()){
                    info = new Users(rs.getString(1), rs.getString(2));
                    if (cab == null){
                        cab = info;
                    }else{
                        info.sig = cab;
                        cab = info;
                    }
                }
            }
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecucion, posibles errores\n"
                                                + ex.getMessage());
        }finally{
            try{
                if (connection != null){
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
            }catch (SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    
    public int getDatos(String user, String pass){
        
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int state = -1;
        
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            
            if(connection != null){
                
                String sql = "SELECT * FROM accounts WHERE BINARY user=? AND BINARY pass=?";
                
                pst = connection.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, pass);
                
                rs = pst.executeQuery();
                
                if(rs.next()){
                    state = 1;
                }else{
                    state = 0;
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse a la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecucion, posibles errores:\n"
                                                + ex.getMessage());
        }finally{
            try{
                if(connection != null){
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
            }catch(SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
        return state;
    }
    
    public Users getBuscarNom(String user){
        Users p = cab;
        if (cab == null)
            return null;
        else{
            while (p != null){
                if ((p.user).equals(user))
                    return p;
                else
                    p=p.sig;
            }
            return null;
        }
    }
    
    public boolean getVacia (){ return cab == null?true:false; }
    
    public Users getCrearNodo (String user, String pass){
        getUser();
        Users info, p;
        String nom;
        if (user.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(null, "Ambos campos son obligatorios, por favor ingrese su usuario y contraseña");
            return null;
        } else {
            do {
                nom = user;
                p = getBuscarNom(nom);
                if(p != null){
                    JOptionPane.showMessageDialog(null, "Este nombre de usuario ya se encuentra registrado\n"
                        + "Ingrese uno diferente");
                    return null;
                }
            }while (p != null);
            if (p == null){
                info = new Users(nom, pass);
                return info;
            }else{
                return null;
            }
        }
    }
    
    public void setDatos(String user, String pass){
        Users p = getCrearNodo(user, pass);
        Connection connection = null;
        PreparedStatement ps;
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            if(connection != null){
                   
                String sql = "INSERT INTO accounts (user, pass) VALUES (?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, p.user);
                ps.setString(2, p.pass);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se registro con exito, Inicie sesion para continuar");     
            }
            
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecucion, posibles errores:\n"
                                                + ex.getMessage());
        }finally{
            try{
                if(connection != null){
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
            }catch(SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    
}
