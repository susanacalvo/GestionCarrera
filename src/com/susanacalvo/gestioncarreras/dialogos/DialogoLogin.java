package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Usuario;
import com.susanacalvo.gestioncarreras.util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Clase Login
 * @author Susana
 * @since JDK 8
 * @version 1.8
 */
public class DialogoLogin extends JDialog {
    /**
     * Atributos de la clase
     */
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private static final String USUARIO_ADMIN = "admin";
    private static final String USUARIO_ADMIN_PASSWORD = "admin";
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private ArrayList<Usuario> usuarios;
    private int estado;
    private ResourceBundle resourceBundle;

    /**
     * Constructor privado de la clase
     */
    private DialogoLogin() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourceBundle");
        setTitle(resourceBundle.getString("gestion.de.atletismo.2020"));
        setIconImage(new ImageIcon(getClass().getResource("/corriendo.png")).getImage());
        cargarUsuarios();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        initHanderls();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Método que inicializa los manejadores de eventos
     */
    private void initHanderls() {
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
    }

    /**
     * Método para poder crear un DialogoLogin
     * @return estado del rol
     */
    public static int mostrarDialogoLogin(){
        DialogoLogin login = new DialogoLogin();
        return login.estado;
    }

    /**
     * Método que reacciona cuando pulsas el boton Ok
     */
    private void onOK() { comprobarUsuarios(); }

    /**
     * Método que reacciona cuando pulsas el botón Cancel
     */
    private void onCancel() { System.exit(0); }

    /**
     * Método que comprueba el usuario, ADMIN, JUEZ, COMPETIDOR y le asigna un estado 0 ,2 ,1
     */
    private void comprobarUsuarios() {
        if(txtUsuario.getText().equals(USUARIO_ADMIN)
                && String.valueOf(txtPassword.getPassword()).equals(USUARIO_ADMIN_PASSWORD)){
            estado = Usuario.USUARIO_ADMIN;
            dispose();
        }else if(usuarios != null){
            if(comprobarUsuarioRegistrados(txtUsuario.getText(), txtPassword.getPassword())){
                dispose();

            } else {
                Util.mostrarDialogoError(resourceBundle.getString("mensaje.login.incorrecto"));
                txtUsuario.setText("");
                txtPassword.setText("");
                txtUsuario.requestFocus();
            }

        } else {
            Util.mostrarDialogoError(resourceBundle.getString("mensaje.login.incorrecto"));
            txtUsuario.setText("");
            txtPassword.setText("");
            txtUsuario.requestFocus();
        }
    }

    /**
     * Método que carga los usuarios existentes en un ArrayList
     */
    private void cargarUsuarios() {
        FileInputStream fis = null;
        ObjectInputStream deserializador = null;

        try {
            fis = new FileInputStream("data/users.dat");

            deserializador = new ObjectInputStream(fis);

            usuarios = (ArrayList<Usuario>) deserializador.readObject();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(EOFException e) {
            usuarios = new ArrayList<Usuario>();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(deserializador != null){
                try {
                    deserializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método que comprueba recorre los usuarios creados y si es todo correcto le asigna el estado
     * @param login nombre de usuario
     * @param password constraseña de usuario
     * @return True si ya existe, False si no
     */
    private boolean comprobarUsuarioRegistrados(String login, char[] password) {
        for(Usuario usuario : usuarios){
            if(usuario.getLogin().equals(login) && usuario.getPassword().equals(String.valueOf(password))){
                estado = usuario.getRol();
                return true;
            }
        }
        return false;
    }
}
