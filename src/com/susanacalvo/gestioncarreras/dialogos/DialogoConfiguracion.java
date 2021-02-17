package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Clase DialogoConfiguración, permite al usuario configurar su aplicacion a su gusto
 * @author Susana
 * @since JDK 8
 * @version 1.8
 *
 */
public class DialogoConfiguracion extends JDialog {
    /**
     * Atributos de la clase
     */
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton rbEspanol;
    private JRadioButton rbIngles;
    private JComboBox<Integer> cbTamanoFuente;
    private DefaultComboBoxModel<Integer>dcbm;
    private ResourceBundle resourceBundle;

    /**
     * Constructor publico de la clase
     */
    public DialogoConfiguracion() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourceBundle");
        dcbm=new DefaultComboBoxModel<>();
        cbTamanoFuente.setModel(dcbm);
        initDialog();
        cargarConfiguracion();
        cargarComboBox();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que carga en el ComboBox los diferentes tamaños de letra que se pueden aplicar
     */
    private void cargarComboBox() {
        for (int i=8;i<=20;i+=2){
            dcbm.addElement(i);
        }
    }

    /**
     * Método que inicializa los componenente y los manejadores de eventos
     */
    private void initDialog() {
        setContentPane(contentPane);
        setTitle(resourceBundle.getString("vista.menuitem.preferencias"));
        setIconImage(new ImageIcon(getClass().getResource("/corriendo.png")).getImage());
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onOK(); }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onCancel(); }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { onCancel(); }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onCancel(); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Método que reacciona ante el botón Aceptar
     */
    private void onOK() {
        guardarConfiguracion();
        int opt = Util.mensajeConfirmacion(resourceBundle.getString("dialogo.preferencias.mensaje.reinicio"));

        if(opt == JOptionPane.YES_OPTION){
            System.exit(2);
        }
        dispose();
    }

    /**
     * Método que reacciona ante el botón Cancelar
     */
    private void onCancel() { dispose(); }

    /**
     * Método que guarda la configuración
     */
    private void guardarConfiguracion(){
        Properties propiedades = new Properties();
        String idioma;
        String pais;
        if(rbEspanol.isSelected()){
            idioma = "es";
            pais = "ES";
        } else {
            idioma = "en";
            pais = "UK";
        }
        Integer fuente= (Integer) cbTamanoFuente.getSelectedItem();
        propiedades.setProperty("fuente", String.valueOf(fuente));
        propiedades.setProperty("idioma", idioma);
        propiedades.setProperty("pais", pais);


        try {
            propiedades.store(new FileWriter("data/preferencias.conf"), "Fichero de preferencias");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que carga la configuración
     */
    public void cargarConfiguracion() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));

            String pais = properties.getProperty("pais");
            int fuente = Integer.parseInt(properties.getProperty("fuente"));
            dcbm.setSelectedItem(fuente);

            if(pais.equals("ES")){
                rbEspanol.setSelected(true);
            }else {
                rbIngles.setSelected(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
