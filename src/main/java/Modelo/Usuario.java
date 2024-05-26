/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Usuario {
    private String id;
    private String nombre;
    private String contrasena;
    private Double saldo;
    private Servicio servicio;
    
    public static final String[] NOMBRES_USUARIO ={"ID","Nombre", "Contraseña", "Saldo"};

    public Usuario(String id, String nombre, String contrasena, Double saldo, Servicio servicio) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.saldo = saldo;
        this.servicio = servicio;
    }

    public Usuario(String id, String nombre, String contrasena, Double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", contrasena=" + contrasena + ", saldo=" + saldo + '}';
    }
    
    
    
}
