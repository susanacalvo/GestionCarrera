package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Juez;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelJuez extends JPanel {
    public JPanel contentPane;
    JLabel lblNombre;
    JLabel lblApellidos;
    JPanel panelNombre;
    JPanel panelDatos;
    JLabel lblCod;

    public PanelJuez(Juez juez){
        panelDatos.setVisible(false);
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
