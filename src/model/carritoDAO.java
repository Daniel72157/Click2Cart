/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static controller.productos.CarritoController.productos;
import controller.signIn.SignInFormController;
import static controller.signIn.SignInFormController.Auser;
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
public class carritoDAO {
    public static carrito cab;
    
    public carritoDAO() { cab = null; }
    
    public int llenarCarrito (String nom){
        int existe;
        carrito agregar = cab;
        while(agregar != null){
            if(agregar.Nombre.equals(nom) && agregar.comprador.equals(Auser)){
                JOptionPane.showMessageDialog(null, "Este producto ya se encuentra en el carrito!");
                existe = -1;
                return existe;
            }
            agregar = agregar.sig;
        }
        existe = 0;
        return existe;
    }
    
    public void getCrearnodo(String linkImage, String Nombre, String Descripcion, String User
            , String Clase, String Precio, String comprador){
        carrito p;
        p = new carrito(linkImage, Nombre, Descripcion, User, Clase, Precio, comprador);
        crearLista();
        int existe = llenarCarrito(Nombre);
        Connection connection = null;
        PreparedStatement ps;
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            if(connection != null && existe == 0){
                   
                String sql = "INSERT INTO carrito (linkImage, Nombre, Descripcion, User, Clase, Precio, comprador) VALUES (?,?,?,?,?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, p.linkImage);
                ps.setString(2, p.Nombre);
                ps.setString(3, p.Descripcion);
                ps.setString(4, p.User);
                ps.setString(5, p.Clase);
                ps.setString(6, p.Precio);
                ps.setString(7, p.comprador);
                ps.executeUpdate();
                if(p != null){
                    if(cab == null){
                    cab = p;
                }else{
                    p.sig = cab;
                    cab = p;
                }
            }
                JOptionPane.showMessageDialog(null, "Producto agregado al carrito!");     
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
    public void getCarrito(){    
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        carrito p;
        
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            
            if(connection != null){
                
                Statement datos = connection.createStatement();
                rs = datos.executeQuery("SELECT * FROM carrito");
                
                while(rs.next()){
                    p = new carrito(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                    if(cab == null){
                        cab = p;
                        if(p.comprador.equals(SignInFormController.Auser)){
                            productos.add(p);
                        }
                    }else{
                        p.sig = cab;
                        cab = p;
                        if(p.comprador.equals(SignInFormController.Auser)){
                            productos.add(p);
                        }
                        
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
    public void crearLista(){    
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        carrito p;
        
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            
            if(connection != null){
                
                Statement datos = connection.createStatement();
                rs = datos.executeQuery("SELECT * FROM carrito");
                
                while(rs.next()){
                    p = new carrito(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                    if(cab == null){
                        cab = p;
                    }else{
                        p.sig = cab;
                        cab = p;
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
    
    public carrito buscar(String nom){
        carrito buscar = cab;
        while(buscar != null){
            if(buscar.Nombre.equals(nom)){
                return buscar;
            }else{
                buscar = buscar.sig;
            }
        }
        return null;
    }
    
    public void eliminarcarrito (String nom){
        crearLista();
        carrito eliminar = buscar(nom);
        Connection connection = null;
        PreparedStatement ps;
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            if(connection != null){
                while(cab != null){
                    if(eliminar.Nombre.equals(nom)){                
                        ps = connection.prepareStatement("DELETE FROM carrito WHERE Nombre = '"+ eliminar.Nombre + "';");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Borrado");
                        controller.productos.CarritoController.productos.remove(eliminar);
                        cab = eliminar;
                    }
                    cab = cab.sig;
                }   
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
