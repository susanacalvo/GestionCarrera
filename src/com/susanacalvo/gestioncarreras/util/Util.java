package com.susanacalvo.gestioncarreras.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Clase Util
 * @author Susana
 * @since JDK8
 * @version 1.8
 */

public class Util {
    /**
     * Atributos de la clase
     */
    public static final int ACEPTAR = JOptionPane.OK_OPTION;
    public static final int CANCELAR = JOptionPane.CANCEL_OPTION;
    public static ResourceBundle resourceBundle =ResourceBundle.getBundle("idiomaResourceBundle");
    /**
     * Método que crea un directorio para guardar todo tipo de configuracio si no existe
     */
    public static void crearSiNoExisteDirectorioDatos(){
        File directorio = new File("data");
        if(!directorio.exists()) {
            directorio.mkdir();
        }

        File user = new File("data/users.dat");
        if(!user.exists()){
            try {
                user.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File preferencias = new File("data/preferencias.conf");
        if(!preferencias.exists()){
            try {
                preferencias.createNewFile();
                Properties propiedades = new Properties();
                String idioma="es";
                String pais="ES";
                int fuente=12;
                propiedades.setProperty("fuente", String.valueOf(fuente));
                propiedades.setProperty("idioma", idioma);
                propiedades.setProperty("pais", pais);
                propiedades.store(new FileWriter("data/preferencias.conf"), "Fichero de preferencias");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que muestra un mensaje de error
     * @param mensaje cadena de texto
     */
    public static void mostrarDialogoError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, resourceBundle.getString("error"), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método que muestra un mensaje de confirmación Sí/No
     * @param mensaje cadena de texto
     * @return 0 si la opción es SI, 1 si la opcion es NO
     */
    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, resourceBundle.getString("confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Método que muestra un cuadro de diálogo de información
     * @param mensaje cadena de texto
     */
    public static void mostrarDialogoInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje, resourceBundle.getString(resourceBundle.getString("informacion")), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que obtiene la localización del Usuario
     * @return locale locaclización del usuario
     */
    public static Locale obtenerLocale() {

        Locale locale = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));

            String idioma = properties.getProperty("idioma");

            if(idioma.equals("en")){
                locale = new Locale("EN");
            }else{
                locale = new Locale("ES");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Si no se ha podido cargar el fichero, idioma spanish
        if(locale == null){
            locale = new Locale("ES");
        }

        return locale;
    }

    /**
     * Método que obtiene la fuente de la configuracion
     */
    public static int obtenerFuente(){
        //Por defecto
        int fuente=12;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));
            fuente=Integer.parseInt(properties.getProperty("fuente"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fuente;
    }

    /**
     * Método para redimensionar las imágenes
     * @param icon Imagen a redimensionar
     * @param alto alto de la imagen
     * @param ancho ancho de la imagen
     * @return icon Imagen redimensionada
     */
    public static Icon escalarImagen(ImageIcon icon, int alto, int ancho){
        Image imagen = icon.getImage();

        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        icon = new ImageIcon(imagenEscalada);
        return icon;
    }
}
