package com.susanacalvo.gestioncarreras.base;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Clase Juez
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class Juez implements Serializable {
    /**
     * Atributos de la clase
     */
    private String numJuez;
    private String nombre;
    private String apellidos;
    private LinkedList<Carrera>carrerasdeJuez;

    /**
     * Constructor de la clase Juez
     * @param numJuez
     * @param nombre
     * @param apellidos
     */
    public Juez(String numJuez, String nombre, String apellidos) {
        this.numJuez = numJuez;
        this.nombre = nombre;
        this.apellidos = apellidos;
        carrerasdeJuez=new LinkedList<>();
    }

    /**
     * Método que devuelve el número del Juez
     * @return numJuez
     */
    public String getNumJuez() {
        return numJuez;
    }

    /**
     * Método que establece el número de un Juez
     * @param numJuez
     */
    public void setNumJuez(String numJuez) {
        this.numJuez = numJuez;
    }

    /**
     * Método que devuelve el nombre del Juez
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establede el nombre del Juez
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que devuelve el apellido del Juez
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método que establece los apellidos de un Juez
     * @param apellidos
     */
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    /**
     * Método que devuelve la lista de las carreras de un Juez
     * @return carrerasdeJuez
     */
    public LinkedList<Carrera> getCarrerasdeJuez() { return carrerasdeJuez; }

    /**
     * Método que establece la lista de carreras de un Juez
     * @param carrerasdeJuez
     */
    public void setCarrerasdeJuez(LinkedList<Carrera> carrerasdeJuez) {
        this.carrerasdeJuez = carrerasdeJuez;
    }

    /**
     * Método que devuelve una cadena de información de la clase Juez
     * @return nombre + apellidos
     */
    @Override
    public String toString() { return nombre + " " + apellidos; }
}
