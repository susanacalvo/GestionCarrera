package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Juez;
import com.susanacalvo.gestioncarreras.mvc.gui.Vista;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
/**
 * Clase que agrega o elimina carreras a un juez
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class DialogoAgregarCarrerasAJuez extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton eliminar;
    private JButton agregar;
    private JList <Carrera>listCarrerasDeJuez;
    private DefaultListModel<Carrera>dlmExistentes;
    private DefaultListModel<Carrera>dlmDeJuez;
    private JList<Carrera> listCarrerasExistentes;
    private JLabel lblJuez;
    private Juez juez;
    private LinkedList<Carrera> listaTemporalMatriculados;
    private LinkedList<Carrera> listaTemporalNoMatriculados;
    private Vista vista;

    /**
     * Controlador de la clase
     * @param juez Objeto Juez
     * @param carreras Lista de carreras
     */
    public DialogoAgregarCarrerasAJuez(Juez juez, LinkedList<Carrera>carreras) {
        this.juez=juez;

        //Creo dos listas temporales. Si finalmente acepto los cambios, se hacen efectivas
        listaTemporalMatriculados = new LinkedList<>(juez.getCarrerasdeJuez());

        listaTemporalNoMatriculados = new LinkedList<>(carreras);

        //Elimino los alumnos que ya tiene el profesor de la lista de no matriculados
        listaTemporalNoMatriculados.removeAll(listaTemporalMatriculados);


        dlmDeJuez = new DefaultListModel<>();
        dlmExistentes = new DefaultListModel<>();
        listCarrerasExistentes.setModel(dlmExistentes);
        listCarrerasDeJuez.setModel(dlmDeJuez);

        listarCarrerasExistentes();
        listarCarrerasDeJuez();

        initUI();
    }

    /**
     * Método onOk
     */
    private void onOK() {
        realizarCambios();
        dispose();
    }

    /**
     * Método onCancel
     */
    private void onCancel() { dispose(); }

    /**
     * Método que lista las carreras que tiene un Juez
     */
    private void listarCarrerasDeJuez() {
        dlmDeJuez.clear();
        for(Carrera carrera : listaTemporalMatriculados){
            dlmDeJuez.addElement(carrera);
        }
    }

    /**
     * Método que lista las Carreras que hay en el sistema
     */
    private void listarCarrerasExistentes() {

        dlmExistentes.clear();
        for(Carrera carrera : listaTemporalNoMatriculados){
            dlmExistentes.addElement(carrera);
        }
    }

    /**
     * Método que agrega una Carrera a un juez
     */
    private void agregarCarreras() {
        //Obtengo todos los alumnos seleccionados de la lista de no matriculados
        List<Carrera> seleccionados = listCarrerasExistentes.getSelectedValuesList();
        //Los elimino de su lista
        listaTemporalNoMatriculados.removeAll(seleccionados);
        //Y los annado a la lista del profesor
        listaTemporalMatriculados.addAll(seleccionados);

        listarCarrerasExistentes();
        listarCarrerasDeJuez();
    }

    /**
     * Método que elimina una Carrera de un Juez
     */
    private void eleminarCarreras() {
        List<Carrera> seleccionados = listCarrerasDeJuez.getSelectedValuesList();
        listaTemporalMatriculados.removeAll(seleccionados);
        listaTemporalNoMatriculados.addAll(seleccionados);

        listarCarrerasExistentes();
        listarCarrerasDeJuez();
    }

    /**
     * Método que aplica los cambios realizados
     */
    private void realizarCambios() {
        for(Carrera carrera : listaTemporalMatriculados){
            carrera.getJuezCarrera().getCarrerasdeJuez().remove(carrera);
            carrera.setJuezCarrera(juez);
        }

        juez.setCarrerasdeJuez(listaTemporalMatriculados);

    }

    /**
     * Método que inicializa los componentes gráficos y manejadores de eventos
     */
    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(juez.getNumJuez() + " - " + juez.getNombre() + " " + juez.getApellidos());

        lblJuez.setText(juez.toString());


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onOK(); }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onCancel(); }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eleminarCarreras();
            }
        });
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCarreras();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
