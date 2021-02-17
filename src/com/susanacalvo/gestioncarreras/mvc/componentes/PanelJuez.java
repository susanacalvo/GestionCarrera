package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Juez;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Clase PanelJuez, encargada de mostrar información sobre el Juez
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class PanelJuez extends JPanel {
    /**
     *Atributos de la clase
     */
    public JPanel contentPane;
    JLabel lblNombre;
    JLabel lblApellidos;
    JPanel panelNombre;
    JPanel panelDatos;
    JLabel lblCod;

    /**
     * Constructor de la clase
     * @param juez Objeto Juez
     */
    public PanelJuez(Juez juez){
        panelDatos.setVisible(false);
        mostrarDatosJuez(juez);
        initUI();
    }

    /**
     * Método que muestra información sobre el Juez
     * @param juez Objeto Juez
     */
    private void mostrarDatosJuez(Juez juez) {
        lblNombre.setText(juez.getNombre());
        lblApellidos.setText(juez.getApellidos());
        lblCod.setText(juez.getNumJuez());
    }

    /**
     * Método que inicializa los manejadore de evento de la clase
     */
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
