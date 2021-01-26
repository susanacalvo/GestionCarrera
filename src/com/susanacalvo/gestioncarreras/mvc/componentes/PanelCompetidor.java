package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Competidor;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCompetidor extends JPanel {
    private JLabel lblNombreCompetidor;
    private JLabel lblApellidoCompetidor;
    private JLabel lblDNI;
    private JLabel lblEdad;
    private JLabel lblAltura;
    private JPanel panelNombre;
    private JPanel panelDatos;

    public PanelCompetidor(Competidor competidor) {
        //setMaximumSize(new Dimension(450, 109));
        initGUI();
        mostrarDatosCompetidor(competidor);
    }

    private void mostrarDatosCompetidor(Competidor competidor) {
        lblNombreCompetidor.setText(competidor.getNombre());
        lblApellidoCompetidor.setText(competidor.getApellidos());
        lblDNI.setText(competidor.getDni());
        lblEdad.setText(String.valueOf(competidor.getEdad()));
        lblAltura.setText(String.valueOf(competidor.getAltura()));
    }

    public void initGUI() {
        panelNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
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
