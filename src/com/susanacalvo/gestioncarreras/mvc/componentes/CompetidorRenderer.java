package com.susanacalvo.gestioncarreras.mvc.componentes;

import com.susanacalvo.gestioncarreras.base.Competidor;
import javax.swing.*;
import java.awt.*;

/**
 * Clase CompetidorRenderer
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class CompetidorRenderer extends JLabel implements ListCellRenderer<Competidor> {
    /**
     * Constructor de la clase
     */
    public CompetidorRenderer(){

    }

    /**
     * Método implementado por la interfaz ListCellRenderer
     * @param list lista de competidores
     * @param value objeto Competidor
     * @param index indice
     * @param selected boolean que indica si está seleccionado o no
     * @param cellHasFocus si la celda tiene el foco
     * @return esta misma clase una JLabel
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Competidor> list, Competidor value, int index, boolean selected, boolean cellHasFocus) {

        Competidor competidor = value;
        String nombre= competidor.getNombre();
        String apellido=competidor.getApellidos();
        Icon imageIcon = competidor.getFoto();

        setIcon(imageIcon);
        setText(nombre+" "+apellido);

        //Indico los colores de fondo de los elementos seleccionados y no seleccionados
        if(selected) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        }else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        return this;
    }
}

