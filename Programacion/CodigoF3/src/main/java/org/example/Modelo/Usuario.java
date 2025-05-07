 package org.example.Modelo;

public class Usuario {
    private int idUsuario;
    private String nickname;
    private String nombre;
    private String clave;
    private String tipoUsuario;
    public Usuario() {

    }

    public Usuario(int idUsuario, String nickname, String nombre, String clave, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.nombre = nombre;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}