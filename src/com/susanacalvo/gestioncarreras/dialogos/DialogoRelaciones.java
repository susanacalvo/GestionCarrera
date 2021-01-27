package com.susanacalvo.gestioncarreras.dialogos;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
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

public class DialogoRelaciones extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Carrera> listCarreras;
    private DefaultListModel<Carrera>dlm;
    private JPanel panelJueces;
    private JPanel panelCompetidores;
    private Modelo modelo;

    public DialogoRelaciones(Modelo modelo) {
        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        this.modelo=modelo;
        dlm=new DefaultListModel<>();
        listCarreras.setModel(dlm);
        listarCarreras();
        System.out.println("SI");
    }

    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


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

    private void onOK() { dispose(); }

    private void onCancel() { dispose(); }

    private void mostrarJueces(Carrera carrera){
        panelJueces.removeAll();
        for(Juez juez: modelo.getJueces()){
            for (Carrera c : juez.getCarrerasdeJuez()){
                if (c==carrera){
                    panelJueces.add(new PanelJuez(juez).contentPane);
                }
                panelJueces.revalidate();
                panelJueces.repaint();
            }
        }
    }

    private void mostrarCompetidores(Carrera carrera){
        panelCompetidores.removeAll();
        for(Competidor competidor : modelo.getCompetidores()){
            if(competidor.getCarrera()==carrera){
                panelCompetidores.add(new PanelCompetidor(competidor).contentPane);
            }
            panelCompetidores.revalidate();
            panelCompetidores.repaint();
        }
    }

    private void listarCarreras(){
        dlm.clear();

        for(Carrera carrera : modelo.getCarreras()){
            dlm.addElement(carrera);
            System.out.println("Entra");
        }
    }

}
