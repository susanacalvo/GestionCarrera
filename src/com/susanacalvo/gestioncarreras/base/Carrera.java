package com.susanacalvo.gestioncarreras.base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Clase Carrera
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class Carrera implements Serializable {
    /**
     * Atributos de la clase
     */
    private static final long serialVersionUID = 1L;
    private String denominacion;
    private int metros;
    private LocalDate fecha;
    private String lugar;
    private boolean realizado;
    private Juez juezCarrera;
    private List<Competidor> competidoresCarrera;

    /**
     * Constructor de la clase Carrera
     * @param denominacion nombre de la Carrera
     * @param metros cantidad de metros de la Carrera
     * @param fecha Fecha de la realización de la Carrera
     * @param lugar Lugar de realización de la Carrera
     * @param realizado Si se ha realizado o no
     * @param juezCarrera Objeto Juez
     */
    public Carrera(String denominacion, int metros, LocalDate fecha, String lugar, boolean realizado, Juez juezCarrera) {
        super();
        this.denominacion = denominacion;
        this.metros = metros;
        this.fecha = fecha;
        this.lugar = lugar;
        this.realizado = realizado;
        this.juezCarrera=juezCarrera;
        competidoresCarrera=new ArrayList<>();
    }

    /**
     * Método que devuelve la Denominación de la carrera
     * @return denominacion
     */
    public String getDenominacion() { return denominacion; }

    /**
     * Método que establece una Denominación
     * @param denominacion nombre de la Carrera
     */
    public void setDenominacion(String denominacion) { this.denominacion = denominacion; }

    /**
     * Método que devuelve los metros de la carrera
     * @return metros
     */
    public int getMetros() { return metros; }

    /**
     * Método que establece los metros de la carrera
     * @param metros cantidad de metros de la Carrera
     */
    public void setMetros(int metros) { this.metros = metros; }

    /**
     * Método que devuelve una Fecha
     * @return fecha
     */
    public LocalDate getFecha() { return fecha; }

    /**
     * Método que establece una Fecha
     * @param fecha Fecha de realización de la carrera
     */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    /**
     * Método que devuelve el lugar de la carrera
     * @return lugar
     */
    public String getLugar() { return lugar; }

    /**
     * Método que establece un lugar a la carrera
     * @param lugar
     */
    public void setLugar(String lugar) {this.lugar = lugar;}

    /**
     * Método que devuelve si se ha realizado o no
     * @return realizado
     */
    public boolean isRealizado() { return realizado; }

    /**
     * Método que establece si se ha realizado o no
     * @param realizado
     */
    public void setRealizado(boolean realizado) { this.realizado = realizado; }

    /**
     * Método que devuelve el Juez de la carrera
     * @return juezCarrera
     */
    public Juez getJuezCarrera() { return juezCarrera; }

    /**
     * Método que establece un Juez a la carrera
     * @param juezCarrera
     */
    public void setJuezCarrera(Juez juezCarrera) { this.juezCarrera = juezCarrera; }

    /**
     * Método que devuelve una lista de los competidores de una carrera
     * @return competidoresCarrera
     */
    public List<Competidor>getCompetidoresCarrera(){return competidoresCarrera; }

    /**
     * Método que establece competidores a una Carrera
     * @param competidoresCarrera
     */
    public void setCompetidoresCarrera(List<Competidor>competidoresCarrera){this.competidoresCarrera=competidoresCarrera; }
    /**
     * Método que devuelve una cadena de información de la clase Carrera
     * @return metros + denominacion
     */
    @Override
    public String toString() { return  metros + " - " + denominacion+" - "+lugar; }
}
