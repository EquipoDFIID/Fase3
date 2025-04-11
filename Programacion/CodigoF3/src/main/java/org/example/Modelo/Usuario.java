 package org.example.Modelo;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String clave;
    private String tipo_usuario;
    public Usuario() {

    }

    public Usuario(int idUsuario, String nombre, String clave, String tipo_usuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.clave = clave;
        this.tipo_usuario = tipo_usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}