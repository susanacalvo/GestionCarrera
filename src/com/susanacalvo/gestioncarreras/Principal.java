package com.susanacalvo.gestioncarreras;

import com.susanacalvo.gestioncarreras.dialogos.DialogoLogin;
import com.susanacalvo.gestioncarreras.mvc.gui.Controlador;
import com.susanacalvo.gestioncarreras.mvc.gui.SplashScreen;
import com.susanacalvo.gestioncarreras.mvc.gui.Vista;
import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import com.susanacalvo.gestioncarreras.util.Util;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
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
        aplicarLookAndFeel();

        Thread hilo = new Thread(new SplashScreen());
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        int fuente=Util.obtenerFuente();
        establecerTamanoFuente(fuente);

        Util.crearSiNoExisteDirectorioDatos();

        int tipoUsuario = DialogoLogin.mostrarDialogoLogin();

        Vista vista=new Vista(tipoUsuario);
        Modelo modelo = new Modelo();

        Controlador controlador = new Controlador(vista, modelo);


    }

    /**
     * Método que aplica un Look And Feel a la aplicacion
     */
    private static void aplicarLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que establece el tamano de fuente de la aplicación
     * @param fuente
     */
    public static void establecerTamanoFuente(int fuente) {
        String[] componentes = {"Label.font", "Button.font", "ToggleButton.font", "RadioButton.font",
                "CheckBox.font", "ComboBox.font", "List.font", "MenuBar.font", "MenuItem.font",
                "RadioButtonMenuItem.font", "CheckBoxMenuItem.font", "Menu.font", "PopupMenu.font",
                "OptionPane.font", "Panel.font", "ProgressBar.font", "ScrollPane.font", "Viewport.font",
                "TabbedPane.font", "Table.font", "TableHeader.font", "TextField.font", "PasswordField.font",
                "TextArea.font", "TextPane.font", "EditorPane.font", "TitledBorder.font", "ToolBar.font",
                "ToolTip.font", "Tree.font"};

        for (String componente : componentes) {
            UIManager.put(componente, new FontUIResource(new Font(null, Font.PLAIN, fuente)));
        }
    }
}
