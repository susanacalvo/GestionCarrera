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
 * @since JDK 8
 * @version 1.8
 */
public class Modelo {
    /**
     * Atributos de la clase
     */
    private List<Competidor> listaCompetidores;
    private LinkedList<Carrera> listaCarreras;
    private ArrayList<Juez> listaJueces;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase Modelo, inicializa  las Colecciones
     */
    public Modelo(){
        resourceBundle=ResourceBundle.getBundle("idiomaResourceBundle");
        listaCompetidores = new ArrayList<>();
        listaCarreras = new LinkedList<>();
        listaJueces = new ArrayList<>();
    }

    /**
     * Método que añade un nuevo competidor al sistema
     * @param competidor Objeto Competidor
     */
    public void nuevoCompetidor(Competidor competidor){
        listaCompetidores.add(competidor);
        if(competidor.getCarrera()!=null){
            competidor.getCarrera().getCompetidoresCarrera().add(competidor);
        }
    }

    /**
     * Método que elimina un competidor del sistema
     * @param competidor Objeto Competidor
     */
    public void eliminarCompetidor(Competidor competidor){
        if(competidor.getCarrera()!=null){
            competidor.getCarrera().getCompetidoresCarrera().remove(competidor);
        }
        listaCompetidores.remove(competidor);
    }

    /**
     * Método que devuelve una lista de los competidores
     * @return listaCompetidores lista con todos los competidores
     */
    public List<Competidor>getCompetidores(){ return listaCompetidores; }

    /**
     * Método que añade un nuevo Juez al sistema
     * @param juez Objeto Juez
     */
    public void nuevoJuez(Juez juez){listaJueces.add(juez);}

    /**
     * Método que elimina un Juez del sistema
     * @param juez Objeto Juez
     */
    public void eliminarJuez(Juez juez){listaJueces.remove(juez);}

    /**
     * Método que devuelve una lista de los jueces
     * @return listaJueces lista con todos los jueces
     */
    public ArrayList<Juez>getJueces(){return listaJueces;}

    /**
     * Método que añade una nueva carrera al sistema
     * @param carrera Objeto Carrera
     */
    public void nuevaCarrera(Carrera carrera){listaCarreras.add(carrera);}

    /**
     * Método que elimina una carrera del sistema
     * @param carrera Objeto Carrera
     */
    public void eliminarCarrera(Carrera carrera){listaCarreras.remove(carrera);}

    /**
     * Método que devuelve una lista de las carreras
     * @return listaCarreras lista con todas las carreras
     */
    public LinkedList<Carrera>getCarreras(){return listaCarreras;}

    /**
     * Método que comprueba que el Competidor ya existe
     * @param dni cadena de texto referente al dni del Competidor
     * @return True si ya existe, False si no existe
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
     * @param codigo cadena de texto referente al código del Juez
     * @return True si ya existe, False si no existe
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
     * Método que comprueba que si la carrera ya existe
     * @param fecha Fecha de realización de la carrera
     * @param nombre Nombre de la carrera
     * @param lugar Lugar de realización de la carrera
     * @return True si ya existe, False si no existe
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
     * @param fichero Fichero de datos
     */
    public void cargarDatos(File fichero) {
        try {
            FileInputStream flujoEntrada = new FileInputStream (fichero);
            ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
            listaCompetidores = (ArrayList<Competidor>) deserializador.readObject();
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
     * @param fichero Fichero de datos
     */
    public void guardarDatos(File fichero) {
        try {
            FileOutputStream flujoSalida = new FileOutputStream (fichero);
            ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
            serializador.writeObject(listaCompetidores);
            serializador.writeObject(listaJueces);
            serializador.writeObject(listaCarreras);
            serializador.close();
            //Util.mostrarDialogoInformacion(resourceBundle.getString("guardado.correctamente"));
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
