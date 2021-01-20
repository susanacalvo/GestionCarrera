package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Usuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Clase DialogoGestionUsuarios, encargada de crear nuevos usuarios y/o eliminarlos
 */
public class DialogoGestionUsuarios extends JDialog {
    /**
     * Atributos de la clase
     */
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtUser;
    private JPasswordField txtpass;
    private JCheckBox mostrarContraseñaCheckBox;
    private JComboBox cbTipoUser;
    private JButton anadirUsuarioButton;
    private JButton eliminarUsuarioButton;
    private JList<Usuario> listUsuarios;
    private ResourceBundle resourceBundle;
    private ArrayList<Usuario> usuarios;
    private DefaultListModel<Usuario> dlm;

    /**
     * Constructor publico de la clase DialogoGestionUsuarios
     */
    public DialogoGestionUsuarios() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");
        dlm = new DefaultListModel<>();
        listUsuarios.setModel(dlm);
        initDialog();
        cargarUsuarios();
        listarUsuarios();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    /**
     * Método que muestra los datos del usuario al seleccionarlo en la lista
     */
    private void mostrarDatos() {
        Usuario usuarioSeleccionado = listUsuarios.getSelectedValue();
        if(usuarioSeleccionado != null){
            txtUser.setText(usuarioSeleccionado.getLogin());
            txtpass.setText(usuarioSeleccionado.getPassword());
            cbTipoUser.setSelectedIndex(usuarioSeleccionado.getRol());
        } else {
            txtpass.setText("");
            txtUser.setText("");
            cbTipoUser.setSelectedIndex(Usuario.USUARIO_ADMIN);
        }
    }

    /**
     * Método que muestra la contraseña
     */
    private void mostrarContrasena() {
        if(mostrarContraseñaCheckBox.isSelected()){
            txtpass.setEchoChar((char)0);
        } else {
            txtpass.setEchoChar('*');
        }
    }

    /**
     * Método que lista los Usuarios
     */
    private void listarUsuarios() {
        dlm.clear();
        for(Usuario usuario : usuarios){
            dlm.addElement(usuario);
        }
    }

    /**
     * Método que inicializa los componentes y los manejadores de eventos del cuadro de diálogo
     */
    private void initDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle(resourceBundle.getString("creacion.de.nuevos.usuarios"));
        setIconImage(new ImageIcon(getClass().getResource("/corriendo.png")).getImage());
        getRootPane().setDefaultButton(buttonOK);
        txtpass.setEchoChar('*');

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
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { eliminarUsuario(); }
        });
        anadirUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { nuevoUsuario(); }
        });

        mostrarContraseñaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { mostrarContrasena(); }
        });
        listUsuarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) { mostrarDatos(); }
        });
    }

    /**
     * Método que crea un nuevo usuario y lo lista
     */
    private void nuevoUsuario() {

        String login = txtUser.getText();
        String password = String.valueOf(txtpass.getPassword());
        //0 para ADMIN, 1 para Standard
        int rol = cbTipoUser.getSelectedIndex();
        usuarios.add(new Usuario(login, password, rol));

        listarUsuarios();
    }

    /**
     * Método que elimina un usuario y lo lista
     */
    private void eliminarUsuario() {
        Usuario usuarioEliminar = listUsuarios.getSelectedValue();
        if(usuarioEliminar != null){
            usuarios.remove(usuarioEliminar);
            listarUsuarios();
        }
    }

    /**
     * Método que reacciona ante el botón Aceptar
     */
    private void onOK() {
        // add your code here
        guardarUsuarios();
        dispose();
    }

    /**
     *  Método que guarda los usuarios
     */
    private void guardarUsuarios()  {
        FileOutputStream fos = null;
        ObjectOutputStream serializador = null;
        try {
            fos = new FileOutputStream("data/users.dat");
            serializador = new ObjectOutputStream(fos);
            serializador.writeObject(usuarios);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(serializador != null) {
                    serializador.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que reacciona ante el botón Cancelar
     */
    private void onCancel() { dispose(); }

    /**
     * Método que carga los usuarios ya creados previamente
     */
    private void cargarUsuarios(){
        FileInputStream fileInputStream = null;
        ObjectInputStream deserializador = null;

        try {
            fileInputStream = new FileInputStream("data/users.dat");
            deserializador = new ObjectInputStream(fileInputStream);
            usuarios = (ArrayList<Usuario>) deserializador.readObject();

        } catch (Exception e){
            e.printStackTrace();
            usuarios =  new ArrayList<>();
        } finally {
            try {
                if(deserializador != null) {
                    deserializador.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
