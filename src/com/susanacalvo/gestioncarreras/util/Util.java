package com.susanacalvo.gestioncarreras.util;

import com.sun.deploy.nativesandbox.NativeSandboxBroker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Clase Util
 * @author Susana
 */
public class Util {
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
    }

    /**
     * Método que muestra un mensaje de error
     * @param mensaje
     */
    public static void mostrarDialogoError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, resourceBundle.getString("error"), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método que muestra un mensaje de confirmación Sí/No
     * @param mensaje
     * @return 0 1
     */
    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, resourceBundle.getString("confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Método que muestra un cuadro de diálogo de información
     * @param mensaje
     */
    public static void mostrarDialogoInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje, resourceBundle.getString(resourceBundle.getString("informacion")), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que obtiene la localización del Usuario
     * @return locale
     */
    public static Locale obtenerLocale() {

        Locale locale = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));
            String pais = properties.getProperty("pais");
            String idioma = properties.getProperty("idioma");

            if(pais.equals("UK")){
                locale = new Locale("en", "UK");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Si no se ha podido cargar el fichero, idioma spanish
        if(locale == null){
            locale = new Locale("es", "ES");
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
     * @param icon
     * @param alto
     * @param ancho
     * @return icon
     */
    public static Icon escalarImagen(ImageIcon icon, int alto, int ancho){
        Image imagen = icon.getImage();

        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        icon = new ImageIcon(imagenEscalada);
        return icon;
    }
}
