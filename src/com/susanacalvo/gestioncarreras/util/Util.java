package com.susanacalvo.gestioncarreras.util;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Clase Util
 * @author Susana
 */
public class Util {
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
    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método que muestra un mensaje de confirmación Sí/No
     * @param mensaje
     * @return 0 1
     */
    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
}
