package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Clase Informes
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class DialogoInformes extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnInforme1;
    private JButton btnInforme2;
    private JPanel panelInforme1;
    private JPanel panelInforme2;
    private Modelo modelo;

    /**
     * Constructor de la clase
     * @param modelo modelo del programa
     */
    public DialogoInformes(Modelo modelo) {
        this.modelo=modelo;
        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Método que inicializa los componentes gráficos y manejadores de eventos de la clase
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

        btnInforme1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JasperReport informe = (JasperReport) JRLoader.loadObject(getClass().getResource("/Informe.jasper"));
                    JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getCompetidores());
                    JasperPrint printer = JasperFillManager.fillReport(informe, null, coleccion);
                    JRViewer visor = new JRViewer(printer);
                    panelInforme1.add(visor);
                    panelInforme1.setVisible(true);
                    panelInforme2.setVisible(false);
                } catch (JRException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnInforme2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JasperReport informe = (JasperReport) JRLoader.loadObject(getClass().getResource("/InformeCarreras.jasper"));
                    JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getCarreras());
                    JasperPrint printer = JasperFillManager.fillReport(informe, null, coleccion);
                    JRViewer visor = new JRViewer(printer);
                    panelInforme2.add(visor);
                    panelInforme2.setVisible(true);
                    panelInforme1.setVisible(false);
                } catch (JRException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Método que reacciona ante el botón OK
     */
    private void onOK() { dispose(); }

    /**
     * Método que reacciona ante el botón CANCEL
     */
    private void onCancel() { dispose(); }
}
