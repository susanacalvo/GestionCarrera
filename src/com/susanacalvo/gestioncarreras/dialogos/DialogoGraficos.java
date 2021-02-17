package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Juez;
import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle;
/**
 * Clase Graficos
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class DialogoGraficos extends JDialog {
    /**
     * Atributos de la clase
     */
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnGrafico1;
    private JButton btnGrafico2;
    private JPanel panelGrafico1;
    private JPanel panelGrafico2;
    private ResourceBundle resourceBundle;
    private Modelo modelo;

    /**
     * Constructor de la clase
     * @param modelo Modelo
     */
    public DialogoGraficos(Modelo modelo) {
        this.modelo=modelo;
        resourceBundle = ResourceBundle.getBundle("idiomaResourceBundle");
        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que inicializa los manejadores de eventos
     */
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

                for(Juez juez : modelo.getJueces()){
                    dataset.setValue(juez.getNombre(),juez.getCarrerasdeJuez().size());
                }

                JFreeChart grafica = ChartFactory.createPieChart(resourceBundle.getString("title.grafico.uno"), dataset, true, true, false);
                ChartPanel panel = new ChartPanel(grafica);
                panelGrafico1.add(panel);
                panelGrafico1.setVisible(true);
                panelGrafico2.setVisible(false);
            }
        });
        btnGrafico2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for(Carrera carrera:modelo.getCarreras()){
                    dataset.addValue(carrera.getCompetidoresCarrera().size(),carrera.getDenominacion(), carrera.getDenominacion());
                }

                JFreeChart jFreeChart = ChartFactory.createBarChart(resourceBundle.getString("title.grafica.dos"), resourceBundle.getString("nombre.carrera"), resourceBundle.getString("nombre.competidor"),dataset);
                ChartPanel panel = new ChartPanel(jFreeChart);
                panelGrafico2.add(panel);
                panelGrafico2.setVisible(true);
                panelGrafico1.setVisible(false);

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

}
