package com.susanacalvo.gestioncarreras.mvc.modelo;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;
import com.susanacalvo.gestioncarreras.base.Juez;
import com.susanacalvo.gestioncarreras.util.Util;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Clase modelo, realiza todas las operaciones de la aplicacion
 * @author Susana
 *
 */
public class Modelo {
    private HashSet<Competidor> listaCompetidores;
    private LinkedList<Carrera> listaCarreras;
    private ArrayList<Juez> listaJueces;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase Modelo, inicializa  las Colecciones
     */
    public Modelo(){
        resourceBundle=ResourceBundle.getBundle("idiomaResourceBundle");
        listaCompetidores = new HashSet<>();
        listaCarreras = new LinkedList<>();
        listaJueces = new ArrayList<>();
    }

    /**
     * Método que añade un nuevo competidor al sistema
     * @param competidor
     */
    public void nuevoCompetidor(Competidor competidor){
        listaCompetidores.add(competidor);
        if(competidor.getCarrera()!=null){
            competidor.getCarrera().getCompetidoresCarrera().add(competidor);
        }
    }

    /**
     * Método que elimina un competidor del sistema
     * @param competidor
     */
    public void eliminarCompetidor(Competidor competidor){
        if(competidor.getCarrera()!=null){
            competidor.getCarrera().getCompetidoresCarrera().remove(competidor);
        }
        listaCompetidores.remove(competidor);
    }

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

    /**
     * Método que comprueba que el Competidor ya existe
     * @param dni
     * @return false/true
     */
    public boolean existeDniCompetidor(String dni){

        for(Competidor c:listaCompetidores){
            if(c.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }

    /**
     * Método que comprueba que el Juez ya existe
     * @param codigo
     * @return false/true
     */
    public boolean existeCodigoJuez(String codigo){
        for(Juez j:listaJueces){
            if (j.getNumJuez().equals(codigo)){
                return true;
            }
        }
        return false;
    }

    /**
     * Método que comprueba que no haya la misma carrera en la misma fecha
     * @return
     */
    public boolean existeCarrera(LocalDate fecha, String nombre, String lugar){
        for (Carrera c:listaCarreras){
            if (c.getFecha().equals(fecha) && c.getDenominacion().toLowerCase().equals(nombre.toLowerCase()) &&
            c.getLugar().toLowerCase().equals(lugar.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Método para leer los datos de un fichero binario
     * @param fichero
     */
    public void cargarDatos(File fichero) {
        try {
            FileInputStream flujoEntrada = new FileInputStream (fichero);
            ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
            listaCompetidores = (HashSet<Competidor>) deserializador.readObject();
            listaJueces=(ArrayList<Juez>)deserializador.readObject();
            listaCarreras = (LinkedList<Carrera>)deserializador.readObject();
            deserializador.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.err.println("Error: El fichero no existe. ");
            Util.mostrarDialogoError(resourceBundle.getString("el.fichero.no.existe"));
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Fallo en la lectura del fichero. ");
            Util.mostrarDialogoError(resourceBundle.getString("error.fallo.en.la.lectura.del.fichero"));
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se pudo acceder a la clase adecuada para revertir la Serializacion al leer del fichero.");
            Util.mostrarDialogoError(resourceBundle.getString("error.no.se.pudo.acceder.a.la.clase"));
        }
    }

    /**
     * Método para guardar los datos en un fichero binario
     * @param fichero
     */
    public void guardarDatos(File fichero) {
        try {
            FileOutputStream flujoSalida = new FileOutputStream (fichero);
            ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
            serializador.writeObject(listaCompetidores);
            serializador.writeObject(listaJueces);
            serializador.writeObject(listaCarreras);
            serializador.close();
            Util.mostrarDialogoInformacion(resourceBundle.getString("guardado.correctamente"));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("El fichero no existe. ");
            Util.mostrarDialogoError("el.fichero.no.existe");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fallo en la escritura en el fichero. ");
            Util.mostrarDialogoError(resourceBundle.getString("error.en.la.escritura.del.fichero"));
        }
    }
}
