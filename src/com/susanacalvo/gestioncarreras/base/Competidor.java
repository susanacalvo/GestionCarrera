package com.susanacalvo.gestioncarreras.base;

import javax.swing.*;
import java.io.Serializable;
import java.util.LinkedList;


/**
 * Clase Competidor
 * @author Susana
 *
 */
public class Competidor implements Serializable {
    /**
     * Atributos de la clase
     */
    private String dni;
    private String nombre;
    private String apellidos;
    private int edad;
    private double altura;
    private Icon foto;

    /**
     * Constructor de la clase Competidor
     * @param dni
     * @param nombre
     * @param apellidos
     * @param edad
     * @param altura
     * @param foto
     */
    public Competidor(String dni, String nombre, String apellidos, int edad, double altura, Icon foto) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.altura = altura;
        this.foto=foto;
    }

    /**
     * Método que devuelve el Dni
     * @return dni
     */
    public String getDni() {return dni; }

    /**
     * Método que establece un dni
     * @param dni
     */
    public void setDni(String dni) { this.dni = dni; }

    /**
     * Método que devuelve el Nombre
     * @return
     */
    public String getNombre() { return nombre; }

    /**
     * Método que establece un Nombre
     * @param nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Método que devuelve el Apellido
     * @return apellidos
     */
    public String getApellidos() { return apellidos; }

    /**
     * Método que establece un Apellido
     * @param apellidos
     */
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    /**
     * Método que devuelve la Edad
     * @return edad
     */
    public int getEdad() { return edad; }

    /**
     * Método que establece una Edad
     * @param edad
     */
    public void setEdad(int edad) { this.edad = edad; }

    /**
     * Método que devuelve la Altura
     * @return altura
     */
    public double getAltura() { return altura; }

    /**
     * Método que establece una Altura
     * @param altura
     */
    public void setAltura(double altura) { this.altura = altura; }

    /**
     * Método que devuelve una foto
     * @return foto
     */
    public Icon getFoto() {
        return foto;
    }

    /**
     * Método que establece una foto
     * @param foto
     */
    public void setFoto(Icon foto) { this.foto = foto; }

    /**
     * Método que devuelve una cadena de información de la clase Competidor
     * @return nombre + apellidos + altura
     */
    @Override
    public String toString() { return  nombre + " " + apellidos + " " + altura; }
}
