package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;

import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DialogoAgregarCarrerasACompetidor extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblCompetidor;
    private JList<Carrera> listCarrerasEnCompetidores;
    private DefaultListModel<Carrera>dlmCarrerasEnCompetidores;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JList<Carrera> listCarrerasExistentes;
    private DefaultListModel<Carrera>dlmCarrerasExistentes;
    private Competidor competidor;
    private List<Carrera> listaTemporalMatriculados;
    private List<Carrera> listaTemporalNoMatriculados;


    public DialogoAgregarCarrerasACompetidor(Competidor competidor, List<Carrera>carreras) {
        this.competidor=competidor;
        List<Carrera>c = new ArrayList<>();
        for(Carrera carrera : carreras){
            if(carrera.getCompetidoresCarrera().contains(competidor)){
               c.add(carrera);
            }
        }
        listaTemporalMatriculados = new ArrayList<>(c);

        listaTemporalNoMatriculados = new ArrayList<>(carreras);

        //Elimino los alumnos que ya tiene el profesor de la lista de no matriculados
        listaTemporalNoMatriculados.removeAll(listaTemporalMatriculados);

        dlmCarrerasEnCompetidores = new DefaultListModel<>();
        listCarrerasEnCompetidores.setModel(dlmCarrerasEnCompetidores);

        dlmCarrerasExistentes = new DefaultListModel<>();
        listCarrerasExistentes.setModel(dlmCarrerasExistentes);

        listarCarrerasExistentes();
        listarCarrerasEnCompetidor();
        initUI();
    }

    /**
     * Método que lista las Carreras de ese competidor
     */
    private void listarCarrerasEnCompetidor() {
        dlmCarrerasEnCompetidores.clear();
        for(Carrera carrera : listaTemporalMatriculados){
            dlmCarrerasEnCompetidores.add(carrera);
        }
    }

    /**
     * Método que lista las carreras existentes
     */
    private void listarCarrerasExistentes() {
        dlmCarrerasExistentes.clear();
        for (Carrera carrera : listaTemporalNoMatriculados){
            dlmCarrerasExistentes.add(carrera);
        }
    }

    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(competidor.getDni()+" - "+competidor.getNombre());

        lblCompetidor.setText(competidor.getDni()+" - "+competidor.getNombre());
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

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCarreras();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCarreras();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que elimina competidores de una carrera
     */
    private void eliminarCarreras() {
        List<Carrera>seleccionados = listCarrerasEnCompetidores.getSelectedValuesList();
        listaTemporalMatriculados.removeAll(seleccionados);
        listaTemporalNoMatriculados.addAll(seleccionados);

        listarCarrerasEnCompetidor();
        listarCarrerasExistentes();
    }

    /**
     * Método que agrega competidores a una carrera
     */
    private void agregarCarreras() {
        List<Carrera>seleccionados = listCarrerasExistentes.getSelectedValuesList();
        listaTemporalMatriculados.addAll(seleccionados);
        listaTemporalNoMatriculados.removeAll(seleccionados);

        listarCarrerasExistentes();
        listarCarrerasEnCompetidor();
    }

    /**
     * Método OK
     */
    private void onOK() {
        realizarCambios();
        dispose();
    }

    /**
     * Método que aplica los cambios si se pulsa sobre OK
     */
    private void realizarCambios() {
    }

    private void onCancel() { dispose(); }
}
