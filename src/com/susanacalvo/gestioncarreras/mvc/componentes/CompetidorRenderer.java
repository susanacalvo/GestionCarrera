package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Competidor;
import javax.swing.*;
import java.awt.*;

public class CompetidorRenderer extends JLabel implements ListCellRenderer<Competidor> {
    private JLabel txtCompe;

    public CompetidorRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Competidor> list, Competidor value, int index, boolean isSelected, boolean cellHasFocus) {

        Competidor competidor = (Competidor) value;
        String nombre = competidor.getNombre();
        String apellidos = competidor.getApellidos();
        Icon icon =competidor.getFoto();
        ImageIcon img = new ImageIcon(getClass().getResource("/img/" + icon + ".png"));

        setIcon(img);
        setText(nombre+" "+apellidos);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
