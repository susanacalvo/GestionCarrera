package com.susanacalvo.gestioncarreras;

import com.susanacalvo.gestioncarreras.dialogos.DialogoLogin;
import com.susanacalvo.gestioncarreras.mvc.gui.Controlador;
import com.susanacalvo.gestioncarreras.mvc.gui.Vista;
import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import com.susanacalvo.gestioncarreras.util.Util;

import java.util.Locale;

/**
 * Clase Principal, encargada de lanzar la aplicación
 * @author Susana
 */
public class Principal {
    /**
     * Método main
     * @param args
     */
    public static void main(String args[]){
        //Obtenemos la localizacion por defecto de donde se encuentre el usuario
        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        Util.crearSiNoExisteDirectorioDatos();
        //Control de tipo de usuario logueado
        int tipoUsuario = DialogoLogin.mostrarDialogoLogin();

        Vista vista=new Vista(tipoUsuario);
        Modelo modelo = new Modelo();

        Controlador controlador = new Controlador(vista, modelo);
    }
}
