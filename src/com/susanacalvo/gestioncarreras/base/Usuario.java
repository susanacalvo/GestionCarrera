package com.susanacalvo.gestioncarreras.base;

import java.io.Serializable;

/**
 * Clase Usuario
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class Usuario implements Serializable {
    /**
     * Atributos  de la clase
     */
    public static final int USUARIO_ADMIN = 0;
    public static final int USUARIO_COMPETIDOR= 1;
    public static final int USUSARIO_JUEZ=2;
    private String login;
    private String password;
    private int rol;

    /**
     * Constructor de la clase Usuario
     * @param login
     * @param password
     * @param rol
     */
    public Usuario(String login, String password, int rol) {
        this.login = login;
        this.password = password;
        this.rol = rol;
    }

    /**
     * Métido que devuelve el nombre del Usuario
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Método que establece el nombre del Usuario
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Método que devuelve la contraseña del Usuario
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método que establece la contrasña del Usuario
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método que devuelve el rol que tiene el Usuario
     * @return rol
     */
    public int getRol() {
        return rol;
    }

    /**
     * Método que establece el rol del Usuario
     * @param rol
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     * Método que devuelve información sobre un Usuario
     * @return login + rol
     */
    @Override
    public String toString() {
        String textoRol;
        if(rol==USUARIO_ADMIN){
            textoRol="Administrador";
        }else if(rol==USUSARIO_JUEZ){
            textoRol="Juez";
        }else{
            textoRol="Competidor";
        }
        return login + ' ' + textoRol;
    }
}

