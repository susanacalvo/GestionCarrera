package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Juez;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelJuez extends JPanel {
    private JPanel contentPane;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JPanel panelNombre;
    private JPanel panelDatos;
    private JLabel lblCod;

    public PanelJuez(Juez juez){
        mostrarDatosJuez(juez);
        initUI();
    }

    private void mostrarDatosJuez(Juez juez) {
        lblNombre.setText(juez.getNombre());
        lblApellidos.setText(juez.getApellidos());
        lblCod.setText(juez.getNumJuez());
    }

    private void initUI() {
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
