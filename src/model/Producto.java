/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author danie
 */
public class Producto {
    
    String linkImage, Nombre, Descripcion, User, Clase;
    float Precio;
    
    public Producto(){
        linkImage = "";
        Nombre = "";
        Descripcion = "";
        User = "";
        Clase = "";
        Precio = -1;
    }
    public Producto(String linkImage, String Nombre, String Descripcion, String User, String Clase, float Precio){
        this.linkImage = linkImage;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.User = User;
        this.Clase = Clase;
        this.Precio = Precio;
    }

    public String getClase() {
        return Clase;
    }

    public void setClase(String Clase) {
        this.Clase = Clase;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }
    
}
