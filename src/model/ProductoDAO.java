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
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class ProductoDAO {
    private Stack<Producto> pila;
    
    public ProductoDAO(){
        this.pila = new Stack<>();
    }
    
    public void getProdu(){
        
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        Producto info;
        
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            
            if(connection != null){
                
                Statement datos = connection.createStatement();
                rs = datos.executeQuery("SELECT * FROM productos");
                
                while(rs.next()){
                    info = new Producto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6));
                    pila.push(info);
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
    public void setPush(String linkImage, String Nombre, String Descripcion, String User, String Clase, float Precio){
        Producto p = new Producto(linkImage, Nombre, Descripcion, User, Clase, Precio);
        Connection connection = null;
        PreparedStatement ps;
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            if(connection != null){
                   
                String sql = "INSERT INTO productos (linkImage, Nombre, Descripcion, User, Clase, Precio) VALUES (?,?,?,?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, p.linkImage);
                ps.setString(2, p.Nombre);
                ps.setString(3, p.Descripcion);
                ps.setString(4, p.User);
                ps.setString(5, p.Clase);
                ps.setFloat(6, p.Precio);
                ps.executeUpdate();
                pila.push(p);
                JOptionPane.showMessageDialog(null, "Producto ingresado exitosamente");     
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
    public void eliminarproducto (){
        
    }
}
