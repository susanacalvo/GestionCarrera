package com.susanacalvo.gestioncarreras.dialogos;

import com.susanacalvo.gestioncarreras.base.Usuario;
import com.susanacalvo.gestioncarreras.util.Util;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Clase DialogoLogin
 * @author Susana
 */
public class DialogoLogin extends JDialog {
    private static final String USUARIO_ADMIN = "admin";
    private static final String USUARIO_ADMIN_PASSWORD = "admin";
    private static final String USUARIO_JUEZ = "juez";
    private static final String USUARIO_JUEZ_PASSWORD = "juez";
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnAcceder;
    private ArrayList<Usuario> usuarios;
    private int estado;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase
     */
    private DialogoLogin(){
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");
        cargarUsuarios();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnAcceder);
        setUndecorated(true);
        initHandlers();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static int mostrarDialogoLogin(){
        DialogoLogin login = new DialogoLogin();
        return login.estado;
    }

    private void initHandlers() {
        btnAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { comprobarUsuarios(); }
        });
    }

    private void comprobarUsuarios() {
        if(txtUsuario.getText().equals(USUARIO_ADMIN)
                && String.valueOf(txtPassword.getPassword()).equals(USUARIO_ADMIN_PASSWORD)){
            estado = Usuario.USUARIO_ADMIN;
            dispose();
        }else if(txtUsuario.getText().equals(USUARIO_JUEZ)
                && String.valueOf(txtPassword.getPassword()).equals(USUARIO_JUEZ_PASSWORD)){
            estado=Usuario.USUSARIO_JUEZ;
            dispose();

        } else if(usuarios != null){
            if(comprobarUsuarioRegistrados(txtUsuario.getText(), txtPassword.getPassword())){
                dispose();

            } else {
                Util.mostrarDialogoError(resourceBundle.getString("Login Incorecto"));
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

    private void cargarUsuarios() {
        FileInputStream fis = null;
        ObjectInputStream deserializador = null;

        try {
            fis = new FileInputStream("data/users.dat");
            deserializador = new ObjectInputStream(fis);
            usuarios = (ArrayList<Usuario>) deserializador.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(deserializador != null){
                try {
                    deserializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
