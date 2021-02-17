package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;
import com.susanacalvo.gestioncarreras.base.Juez;
import com.susanacalvo.gestioncarreras.mvc.componentes.PanelCompetidor;
import com.susanacalvo.gestioncarreras.mvc.componentes.PanelJuez;
import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
/**
 * Clase Relaciones
 *  @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class DialogoRelaciones extends JDialog {
    /**
     * Atributos de la clase
     */
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Carrera> listCarreras;
    private DefaultListModel<Carrera>dlm;
    private JPanel panelJueces;
    private JPanel panelCompetidores;
    private Modelo modelo;

    /**
     * Constructor de la clase
     * @param modelo Modelo
     */
    public DialogoRelaciones(Modelo modelo) {
        this.modelo=modelo;
        dlm=new DefaultListModel<>();
        listCarreras.setModel(dlm);
        listarCarreras();
        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que inicializa los manejadores de evento de la clase
     */
    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        panelCompetidores.setLayout(new BoxLayout(panelCompetidores,BoxLayout.Y_AXIS));


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

        listCarreras.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getSource()==listCarreras){
                    Carrera carrera = listCarreras.getSelectedValue();
                    if(carrera!=null){
                        mostrarCompetidores(carrera);
                        mostrarJueces(carrera);
                    }
                }
            }
        });
    }

    /**
     * Método que reacciona ante el botón OK
     */
    private void onOK() { dispose(); }

    /**
     * Método que reacciona ante el botón Cancel
     */
    private void onCancel() { dispose(); }

    /**
     * Método que muestra un panel de un Juez de una carrera
     * @param carrera Objeto Carrera
     */
    private void mostrarJueces(Carrera carrera){
        panelJueces.removeAll();
        for(Juez juez: modelo.getJueces()){
            for (Carrera c : juez.getCarrerasdeJuez()){
                if (c.equals(carrera)){
                    panelJueces.add(new PanelJuez(juez).contentPane);
                }
                panelJueces.revalidate();
                panelJueces.repaint();
            }
        }
    }

    /**
     * Método que muestra un panel de Competidores de una Carrera
     * @param carrera Objeto Carrera
     */
    private void mostrarCompetidores(Carrera carrera){
        panelCompetidores.removeAll();
        for(Competidor competidor : modelo.getCompetidores()){
            if(competidor.getCarrera().equals(carrera)){
                panelCompetidores.add(new PanelCompetidor(competidor).contentPane);
            }
            panelCompetidores.revalidate();
            panelCompetidores.repaint();
        }
    }

    /**
     * Método que lista las carreras existentes
     */
    private void listarCarreras(){
        dlm.clear();
        for(Carrera carrera : modelo.getCarreras()){
            dlm.addElement(carrera);
        }
    }
}
