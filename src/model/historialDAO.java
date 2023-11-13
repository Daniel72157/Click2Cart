/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static controller.productos.HistorialController.productos;
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
public class historialDAO {
    historial inicio;
    
    private model.carritoDAO cab = new model.carritoDAO();
    private model.ProductoDAO pila = new model.ProductoDAO();
    public historialDAO(){ inicio = null; }
    
    public void getCrearnodo(String linkImage, String Nombre, String Descripcion, String User
            , String Clase, String Precio, String comprador){
        historial p;
        p = new historial(linkImage, Nombre, Descripcion, User, Clase, Precio, comprador);
        Connection connection = null;
        PreparedStatement ps;
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            if(connection != null){
                   
                String sql = "INSERT INTO historial (linkImage, Nombre, Descripcion, User, Clase, Precio, comprador) VALUES (?,?,?,?,?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, p.linkImage);
                ps.setString(2, p.Nombre);
                ps.setString(3, p.Descripcion);
                ps.setString(4, p.User);
                ps.setString(5, p.Clase);
                ps.setString(6, p.Precio);
                ps.setString(7, p.comprador);
                ps.executeUpdate();
                if(inicio==null){
                    inicio=p;
                    inicio.sig=inicio.ant=inicio;
                }else{
                    p.ant=inicio.ant;
                    p.sig=inicio;
                    inicio.ant.sig=p;
                    inicio.ant=p;
                }
                JOptionPane.showMessageDialog(null, "Producto comprado con exito!");   
                pila.eliminarproducto(p.Nombre);
                cab.eliminarcarrito(p.Nombre);
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
    public void getHistorial(){    
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        historial p;
        
        try{
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            
            if(connection != null){
                
                Statement datos = connection.createStatement();
                rs = datos.executeQuery("SELECT * FROM historial");
                
                while(rs.next()){
                    p = new historial(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                    if(p.comprador.equals(Auser)){
                        productos.add(p);
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
}
