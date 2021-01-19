package com.susanacalvo.gestioncarreras.mvc.gui;

import javax.swing.*;
import java.util.ResourceBundle;

public class Vista {
    JFrame frame;
    JPanel contentPane;
     JTabbedPane tabbedPane1;
     JButton btnNuevoJuez;
     JButton btnEliminarJuez;
     JButton btnModificarJuez;
     JLabel lblCopyRight;
     JTextField txtCodJuez;
     JTextField txtNombreJuez;
     JTextField txtApellidosJuez;
     JList listaJueces;
     JLabel lblCodJuez;
     JLabel lblNombreJuez;
     JLabel lblApeJuez;
     JButton btnNuevoCompetidor;
     JButton btnEliminarCompetidor;
     JButton btnModificarCompetidor;
     JTextField txtDni;
     JTextField txtNombreCompetidor;
     JTextField txtEdad;
     JTextField txtAltura;
     JLabel lblDni;
     JLabel lblNombreCompetidor;
     JLabel lblApeCompetidor;
     JTextField txtApeCompetidor;
     JLabel lblEdad;
     JLabel lblAltura;
     JButton btnEliminarImagen;
     JButton btnNuevaImagen;
     JLabel lblFoto;
     JLabel lblImagen;
     JList listCompetidores;
    ResourceBundle resourceBundle;
    JMenuItem itemSalir;
    JMenuItem itemGuardar;
    JMenuItem itemCargar;
    JMenuItem itemGestionUsuarios;
    JMenuItem itemPreferencias;

    /**
     * Constructor de la clase, recibe el tipo de usuario que se ha logueado
     *
     */
    public Vista() {
        frame = new JFrame("Gestión de Atletismo 2020");
        resourceBundle=ResourceBundle.getBundle("idiomaResourceBundle");
        frame.setIconImage(new ImageIcon(getClass().getResource("/corriendo.png")).getImage());
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        crearMenu();
    }

    private void crearMenu(){

        //Barra de menu
        JMenuBar barra = new JMenuBar();
        frame.setJMenuBar(barra);

        //Menu Archivo
        JMenu menuArchivo = new JMenu(resourceBundle.getString("menu.archivo"));
        barra.add(menuArchivo);

        itemSalir = new JMenuItem(resourceBundle.getString("menu.salir"));
        itemSalir.setActionCommand("Salir");
        itemSalir.setIcon(new ImageIcon(getClass().getResource("/turn-off.png")));

        itemGuardar = new JMenuItem(resourceBundle.getString("menu.guardar"));
        itemGuardar.setActionCommand("Guardar");
        itemGuardar.setIcon(new ImageIcon(getClass().getResource("/disquete.png")));

        itemCargar = new JMenuItem(resourceBundle.getString("vista.menuitem.abrir"));
        itemCargar.setActionCommand("Abrir");
        itemCargar.setIcon(new ImageIcon(getClass().getResource("/cargando.png")));

        menuArchivo.add(itemCargar);
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemSalir);

        //Menu Editar
        JMenu menuEditar = new JMenu(resourceBundle.getString("vista.menu.editar"));
        barra.add(menuEditar);

        itemGestionUsuarios = new JMenuItem(resourceBundle.getString("vista.menuitem.gestion.usuarios"));
        itemGestionUsuarios.setActionCommand("Usuarios");
        itemGestionUsuarios.setIcon(new ImageIcon(getClass().getResource("/usuario.png")));

        itemPreferencias = new JMenuItem(resourceBundle.getString("vista.menuitem.preferencias"));
        itemPreferencias.setActionCommand("Configuracion");
        itemPreferencias.setIcon(new ImageIcon(getClass().getResource("/configuraciones.png")));

        menuEditar.add(itemGestionUsuarios);
        menuEditar.add(itemPreferencias);

    }
}
