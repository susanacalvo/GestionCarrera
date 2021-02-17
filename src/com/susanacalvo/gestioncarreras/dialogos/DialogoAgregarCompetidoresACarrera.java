package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/**
 * Clase que agrega o elimina competidores de una Carrera
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class DialogoAgregarCompetidoresACarrera extends JDialog {
    /**
     * Atributos de la clase
     */
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblCarrera;
    private JList<Competidor> listCompetidoresExistentes;
    private DefaultListModel<Competidor> dlmCompetidoresExistentes;
    private JList<Competidor> listCompetidoresEnCarrera;
    private DefaultListModel<Competidor> dlmCompetidoresEnCarrera;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private Carrera carrera;
    private List<Competidor> listaTemporalMatriculados;
    private List<Competidor> listaTemporalNoMatriculados;

    /**
     * Constructor de la clase
     * @param carrera Objeto Carrera
     * @param competidores Lista de competidores
     */
    public DialogoAgregarCompetidoresACarrera(Carrera carrera, List<Competidor>competidores) {
        this.carrera=carrera;
        //Creo dos listas temporales. Si finalmente acepto los cambios, se hacen efectivas
        listaTemporalMatriculados = new ArrayList<>(carrera.getCompetidoresCarrera());

        listaTemporalNoMatriculados = new ArrayList<>(competidores);

        //Elimino los alumnos que ya tiene el profesor de la lista de no matriculados
        listaTemporalNoMatriculados.removeAll(listaTemporalMatriculados);


        dlmCompetidoresEnCarrera = new DefaultListModel<>();
        dlmCompetidoresExistentes = new DefaultListModel<>();
        listCompetidoresEnCarrera.setModel(dlmCompetidoresEnCarrera);
        listCompetidoresExistentes.setModel(dlmCompetidoresExistentes);

        listarCompetidoresExistentes();
        listarCompetidoresDeCarrera();
        initUI();
    }

    /**
     * Método que lista los competidores que tiene una carrera
     */
    private void listarCompetidoresDeCarrera() {
        dlmCompetidoresEnCarrera.clear();
        for(Competidor competidor: listaTemporalMatriculados){
            dlmCompetidoresEnCarrera.addElement(competidor);
        }
    }

    /**
     * Método que lista los competidores existentes
     */
    private void listarCompetidoresExistentes() {
        dlmCompetidoresExistentes.clear();
        for(Competidor competidor : listaTemporalNoMatriculados){
            dlmCompetidoresExistentes.addElement(competidor);
        }
    }

    /**
     * Método que reacciona ante el boton Ok
     */
    private void onOK() {
        realizarCambios();
        dispose();
    }

    /**
     * Método que reacciona ante el boton Cancel
     */
    private void onCancel() { dispose(); }

    /**
     * Método que elimina un competidor de la carrera
     */
    private void eliminarCompetidor(){
        List<Competidor> seleccionados = listCompetidoresEnCarrera.getSelectedValuesList();
        listaTemporalMatriculados.removeAll(seleccionados);
        listaTemporalNoMatriculados.addAll(seleccionados);

        listarCompetidoresDeCarrera();
        listarCompetidoresExistentes();
    }

    /**
     * Método que añade un nuevo competidor a la carrera
     */
    private void agregarCompetidor(){
        List<Competidor> seleccionados = listCompetidoresExistentes.getSelectedValuesList();
        listaTemporalMatriculados.addAll(seleccionados);
        listaTemporalNoMatriculados.removeAll(seleccionados);

        listarCompetidoresDeCarrera();
        listarCompetidoresExistentes();

    }

    /**
     * Método que aplica los cambios
     */
    private void realizarCambios(){
        for(Competidor competidor:carrera.getCompetidoresCarrera()){
            competidor.setCarrera(null);
        }

        for(Competidor competidor : listaTemporalMatriculados){
            competidor.setCarrera(carrera);
        }

        carrera.setCompetidoresCarrera(listaTemporalMatriculados);

    }
    /**
     * Método que inicializa los componentes gráficos y manejadores de eventos
     */
    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(carrera.getDenominacion() + " - " + carrera.getLugar() + " - " + carrera.getFecha());

        lblCarrera.setText(carrera.toString());


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
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCompetidor();
            }
        });
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCompetidor();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
