package com.susanacalvo.gestioncarreras.mvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;
import com.susanacalvo.gestioncarreras.base.Juez;
import com.susanacalvo.gestioncarreras.base.Usuario;
import com.susanacalvo.gestioncarreras.mvc.componentes.CompetidorRenderer;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
/**
 * Clase Vista
 * @author Susana
 * @since JDK8
 * @version 1.8
 */

public class Vista {
    JFrame frame;
    JPanel contentPane;
     JTabbedPane tabbedPane1;
     JButton btnNuevoJuez;
     JButton btnEliminarJuez;
     JButton btnModificarJuez;
    JTextField txtCodJuez;
     JTextField txtNombreJuez;
     JTextField txtApellidosJuez;
     JList <Juez>listaJueces;
    DefaultListModel<Juez>dlmJuez;
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
     JList<Competidor> listCompetidores;
    DefaultListModel<Competidor>dlmCompetidor;
     JTextField txtCarrera;
     JTextField txtMetros;
     JTextField txtLugar;
     JCheckBox cbRealizado;
     JComboBox<Juez> cbJuezCarrera;
     DefaultComboBoxModel<Juez>dcbmJuez;
     JButton btnAnadirCompetidorCarrera;
     JList<Competidor> listCompetidorCarrera;
     DefaultListModel<Competidor>dlmCompetidorCarrera;
     JList<Carrera>listCarrera;
    DefaultListModel<Carrera>dlmCarrera;
     JButton btnNuevaCarrera;
     JButton btnEliminarCarrera;
     JButton btnModificarCarrera;
     JLabel lblNombreCarrera;
     JLabel lblMetros;
     JLabel lblLugar;
     JLabel lblFecha;
     JLabel lblRealizado;
     JLabel lblJuezCarrera;
     JLabel lblCompetidoresCarrera;
     JPanel panelJueces;
     JPanel panelCompetidores;
     JPanel panelCarreras;
     DatePicker dpFecha;
     JList<Carrera> listCarrerasDeJuez;
     DefaultListModel<Carrera>dlmCarreraDeJuez;
     JButton btnAgregarCarreraAJuez;
     JPanel panelListaCarreras;
     JButton btnAnadirCarreraACompetidor;
     JList<Carrera> listCarrerasCompetidor;
     DefaultListModel<Carrera>dlmCarrerasCompetidor;
    private ResourceBundle resourceBundle;
    private int tipoUsuario;
    JMenuItem itemSalir;
    JMenuItem itemGuardar;
    JMenuItem itemCargar;
    JMenuItem itemGestionUsuarios;
    JMenuItem itemPreferencias;
    JMenuItem itemRelaciones;
    JMenuItem itemGraficos;
    JMenuItem itemInformes;
    JMenuItem itemManualUser;


    /**
     * Constructor de la clase, recibe el tipo de usuario que se ha logueado
     * @param tipoUsuario Tipo de Usuario que se ha logueado
     */
    public Vista(int tipoUsuario) {
        this.tipoUsuario=tipoUsuario;
        frame = new JFrame("Gestión de Atletismo 2020");
        resourceBundle=ResourceBundle.getBundle("idiomaResourceBundle");
        frame.setIconImage(new ImageIcon(getClass().getResource("/corriendo.png")).getImage());
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearMenu();
        activarControlPorTeclado();
        iniciarModelos();
        controlarTipoUsuario();

        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

    }

    /**
     * Método que dependiendo del tipo usuario que se haya logueado le permite realizar unas acciones u otras
     */
    private void controlarTipoUsuario() {
        if(tipoUsuario== Usuario.USUSARIO_JUEZ){
            itemGestionUsuarios.setEnabled(false);
        }else if(tipoUsuario==Usuario.USUARIO_COMPETIDOR){
            tabbedPane1.setEnabled(false);
            btnNuevoJuez.setEnabled(false);
            btnEliminarJuez.setEnabled(false);
            btnModificarJuez.setEnabled(false);
            btnAgregarCarreraAJuez.setEnabled(false);
            txtNombreJuez.setEnabled(false);
            txtApellidosJuez.setEnabled(false);
            txtCodJuez.setEnabled(false);
            itemGestionUsuarios.setEnabled(false);
            itemGuardar.setEnabled(false);
        }
    }

    /**
     * Método que incializa las listas y los comboBox
     */
    private void iniciarModelos() {
        dlmCarreraDeJuez=new DefaultListModel<>();
        listCarrerasDeJuez.setModel(dlmCarreraDeJuez);

        dlmCompetidorCarrera = new DefaultListModel<>();
        listCompetidorCarrera.setModel(dlmCompetidorCarrera);

        dlmCompetidor = new DefaultListModel<>();
        listCompetidores.setModel(dlmCompetidor);
        listCompetidores.setCellRenderer(new CompetidorRenderer());

        dlmCarrera = new DefaultListModel<>();
        listCarrera.setModel(dlmCarrera);

        dlmJuez = new DefaultListModel<>();
        listaJueces.setModel(dlmJuez);

        dlmCarrerasCompetidor = new DefaultListModel<>();
        listCarrerasCompetidor.setModel(dlmCarrerasCompetidor);

        dcbmJuez = new DefaultComboBoxModel<>();
        cbJuezCarrera.setModel(dcbmJuez);
    }

    /**
     * Método que crea el Menu
     */
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

        //Menu Vista
        JMenu menuVista = new JMenu((resourceBundle.getString("menu.ver")));
        barra.add(menuVista);

        itemRelaciones = new JMenuItem(resourceBundle.getString("ver.relaciones"));
        itemRelaciones.setActionCommand("Relacion");
        itemRelaciones.setIcon(new ImageIcon(getClass().getResource("/relacion.png")));
        itemGraficos = new JMenuItem(resourceBundle.getString("ver.graficos"));
        itemGraficos.setActionCommand("Graficos");
        itemGraficos.setIcon(new ImageIcon(getClass().getResource("/grafico.png")));
        itemInformes = new JMenuItem(resourceBundle.getString("informes"));
        itemInformes.setActionCommand("Informes");
        itemInformes.setIcon(new ImageIcon(getClass().getResource("/reporte.png")));

        menuVista.add(itemRelaciones);
        menuVista.add(itemGraficos);
        menuVista.add(itemInformes);

        //Menu Ayuda
        JMenu menuAyuda = new JMenu(resourceBundle.getString("ayuda"));
        barra.add(menuAyuda);
        itemManualUser = new JMenuItem(resourceBundle.getString("manual.usuario"));
        itemManualUser.setActionCommand("ManualUser");
        itemManualUser.setIcon(new ImageIcon(getClass().getResource("/informacion.png")));

        menuAyuda.add(itemManualUser);


    }

    /**
     * Método que permite hacer varias acciones con combinaciones de teclado
     */
    private void activarControlPorTeclado() {

        //Aceleradores
        //Ctr +
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        itemCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        itemGraficos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        itemRelaciones.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        itemGestionUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        itemPreferencias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        itemInformes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        //Alt +
        btnNuevoJuez.setMnemonic(KeyEvent.VK_1);
        btnEliminarJuez.setMnemonic(KeyEvent.VK_2);
        btnModificarJuez.setMnemonic(KeyEvent.VK_3);
        btnAgregarCarreraAJuez.setMnemonic(KeyEvent.VK_4);
        btnNuevoCompetidor.setMnemonic(KeyEvent.VK_5);
        btnEliminarCompetidor.setMnemonic(KeyEvent.VK_6);
        btnModificarCompetidor.setMnemonic(KeyEvent.VK_7);
        btnNuevaImagen.setMnemonic(KeyEvent.VK_8);
        btnEliminarImagen.setMnemonic(KeyEvent.VK_9);
        btnNuevaCarrera.setMnemonic(KeyEvent.VK_F1);
        btnEliminarCarrera.setMnemonic(KeyEvent.VK_F2);
        btnModificarCarrera.setMnemonic(KeyEvent.VK_F3);
        btnAnadirCompetidorCarrera.setMnemonic(KeyEvent.VK_F4);


        //Boton por defecto de la aplicacion
        btnNuevoJuez.getRootPane().setDefaultButton(btnNuevoJuez);
    }

}
