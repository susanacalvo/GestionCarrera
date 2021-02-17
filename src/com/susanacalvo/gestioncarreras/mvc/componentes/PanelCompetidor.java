package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Competidor;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase PanelCompetidor, muestra informacion sobre un Objeto Competidor
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class PanelCompetidor extends JPanel {
    /**
     * Atributos de la clase
     */
     JLabel lblNombreCompetidor;
     JLabel lblApellidoCompetidor;
     JLabel lblDNI;
     JLabel lblEdad;
     JLabel lblAltura;
     JPanel panelNombre;
     JPanel panelDatos;
     public JPanel contentPane;

    /**
     * Constructor de la clase
     * @param competidor Objeto Competidor
     */
    public PanelCompetidor(Competidor competidor) {
        panelDatos.setVisible(false);
        initGUI();
        mostrarDatosCompetidor(competidor);
    }

    /**
     * Método que muestra los datos de un Competidor
     * @param competidor Objeto Competidor
     */
    private void mostrarDatosCompetidor(Competidor competidor) {
        lblNombreCompetidor.setText(competidor.getNombre());
        lblApellidoCompetidor.setText(competidor.getApellidos());
        lblDNI.setText(competidor.getDni());
        lblEdad.setText(String.valueOf(competidor.getEdad()));
        lblAltura.setText(String.valueOf(competidor.getAltura()));
    }

    /**
     * Método que inicializa los manejadores de eventos de la clase
     */
    public void initGUI() {
        panelNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                panelDatos.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                panelDatos.setVisible(true);
            }
        });
    }

}
