package com.susanacalvo.gestioncarreras.mvc.modelo;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;
import com.susanacalvo.gestioncarreras.base.Juez;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Clase modelo, realiza todas las operaciones de la aplicacion
 * @author Susana
 *
 */
public class Modelo {
    private HashSet<Competidor> listaCompetidores;
    private LinkedList<Carrera> listaCarreras;
    private ArrayList<Juez> listaJueces;

    /**
     * Constructor de la clase Modelo, inicializa  las Colecciones
     */
    public Modelo(){
        listaCompetidores = new HashSet<>();
        listaCarreras = new LinkedList<>();
        listaJueces = new ArrayList<>();
    }

    /**
     * Método que añade un nuevo competidor al sistema
     * @param competidor
     */
    public void nuevoCompetidor(Competidor competidor){ listaCompetidores.add(competidor); }

    /**
     * Método que elimina un competidor del sistema
     * @param competidor
     */
    public void eliminarCompetidor(Competidor competidor){ listaCompetidores.remove(competidor); }

    /**
     * Método que devuelve una lista de los competidores
     * @return listsCompetidores
     */
    public HashSet<Competidor>getCompetidores(){ return listaCompetidores; }

    /**
     * Método que añade un nuevo Juez al sistema
     * @param juez
     */
    public void nuevoJuez(Juez juez){listaJueces.add(juez);}

    /**
     * Método que elimina un Juez del sistema
     * @param juez
     */
    public void eliminarJuez(Juez juez){listaJueces.remove(juez);}

    /**
     * Método que devuelve una lista de los jueces
     * @return listaJueces
     */
    public ArrayList<Juez>getJueces(){return listaJueces;}

    /**
     * Método que añade una nueva carrera al sistema
     * @param carrera
     */
    public void nuevaCarrera(Carrera carrera){listaCarreras.add(carrera);}

    /**
     * Método que elimina una carrera del sistema
     * @param carrera
     */
    public void eliminarCarrera(Carrera carrera){listaCarreras.remove(carrera);}

    /**
     * Método que devuelve una lista de las carreras
     * @return
     */
    public LinkedList<Carrera>getCarreras(){return listaCarreras;}
}



