package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ResourceBundle;

public class DialogoGraficos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnGrafico1;
    private JButton btnGrafico2;
    private JPanel panelGraficos;
    private ResourceBundle resourceBundle;
    private Modelo modelo;


    public DialogoGraficos(Modelo modelo) {
        resourceBundle = ResourceBundle.getBundle("idiomaResourceBundle");
        initUI();

    }

    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
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

        btnGrafico1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultPieDataset dataset = new DefaultPieDataset();
                

                JFreeChart grafica = ChartFactory.createPieChart(resourceBundle.getString("title.grafico.uno"), dataset, true, true, false);
                ChartPanel panel = new ChartPanel(grafica);
                panelGraficos.add(panel);
            }
        });
        btnGrafico2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void onOK() { dispose(); }

    private void onCancel() { dispose(); }

}
