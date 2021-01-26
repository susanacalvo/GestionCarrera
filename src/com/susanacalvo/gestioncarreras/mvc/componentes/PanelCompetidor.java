package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Competidor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCompetidor extends JPanel {

     JLabel lblNombreCompetidor;
     JLabel lblApellidoCompetidor;
     JLabel lblDNI;
     JLabel lblEdad;
     JLabel lblAltura;
     JPanel panelNombre;
     JPanel panelDatos;
     public JPanel contentPane;

    public PanelCompetidor(Competidor competidor) {
        //setContentPane(contentPane);

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
