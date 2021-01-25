package com.susanacalvo.gestioncarreras.mvc.gui;

import com.susanacalvo.gestioncarreras.base.Carrera;
import com.susanacalvo.gestioncarreras.base.Competidor;
import com.susanacalvo.gestioncarreras.base.Juez;
import com.susanacalvo.gestioncarreras.dialogos.DialogoAgregarCarrerasAJuez;
import com.susanacalvo.gestioncarreras.dialogos.DialogoConfiguracion;
import com.susanacalvo.gestioncarreras.dialogos.DialogoGestionUsuarios;
import com.susanacalvo.gestioncarreras.mvc.modelo.Modelo;
import com.susanacalvo.gestioncarreras.util.Util;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class Controlador implements ActionListener, ListSelectionListener {
    /**
     * Atributos de la clase
     */
    private Vista vista;
    private Modelo modelo;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase Controlador
     * @param vista
     * @param modelo
     */
    public Controlador(Vista vista,Modelo modelo){
        this.vista=vista;
        this.modelo=modelo;
        resourceBundle=ResourceBundle.getBundle("idiomaResourceBundle");
        anadirActionListener(this);
        anadirListSelectionListener(this);
    }

    /**
     * Método que asocia componentes con el ListSelectionListener
     * @param listener
     */
    private void anadirListSelectionListener(ListSelectionListener listener) {
        vista.listaJueces.addListSelectionListener(listener);
        vista.listCarrera.addListSelectionListener(listener);
        vista.listCompetidores.addListSelectionListener(listener);
        vista.listCompetidorCarrera.addListSelectionListener(listener);
    }

    /**
     * Método que asocia componentes con el ActionListener
     * @param listener
     */
    private void anadirActionListener(ActionListener listener) {
        vista.itemCargar.addActionListener(listener);
        vista.itemGuardar.addActionListener(listener);
        vista.itemGestionUsuarios.addActionListener(listener);
        vista.itemPreferencias.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.btnNuevoJuez.addActionListener(listener);
        vista.btnEliminarJuez.addActionListener(listener);
        vista.btnModificarJuez.addActionListener(listener);
        vista.btnAnadirCompetidorCarrera.addActionListener(listener);
        vista.btnEliminarCarrera.addActionListener(listener);
        vista.btnModificarCarrera.addActionListener(listener);
        vista.btnNuevaCarrera.addActionListener(listener);
        vista.btnEliminarCompetidor.addActionListener(listener);
        vista.btnModificarCompetidor.addActionListener(listener);
        vista.btnNuevoCompetidor.addActionListener(listener);
        vista.btnEliminarImagen.addActionListener(listener);
        vista.btnNuevaImagen.addActionListener(listener);
        vista.btnAgregarCarreraAJuez.addActionListener(listener);
    }

    /**
     * Método que reaccion ante los ActionListeners
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Salir":
                break;
            case "Guardar":
                guardarDatos();
                break;
            case "Abrir":
                cargarDatos();
                break;
            case "Usuarios":
                DialogoGestionUsuarios dialogoGestionUsuarios = new DialogoGestionUsuarios();
                break;
            case "Configuracion":
                DialogoConfiguracion dialogoConfiguracion = new DialogoConfiguracion();
                break;
            case "NuevoJuez":
                nuevoJuez();
                break;
            case "EliminarJuez":
                eliminarJuez();
                break;
            case "ModificarJuez":
                modificarJuez();
                break;
            case "NuevoCompetidor":
                nuevoCompetidor();
                break;
            case "EliminarCompetidor":
                eliminarCompetidor();
                break;
            case "ModificarCompetidor":
                modificarCompetidor();
                break;
            case "NuevaCarrera":
               // nuevaCarrera();
                break;
            case "EliminarCarrera":
                //eliminarCarrera();
                break;
            case "ModificarCarrera":
                //modificarCarrera();
                break;
            case "AgregarCompetidorCarrera":
                break;
            case "AgregarCarrerasAJuez":
                agregarCarrerasAJuez();
                break;
        }
    }
    /**
     * Método que lista las carreras de un Juez
     */
    private void listarCarrerasDeJuez(Juez juez) {
        vista.dlmCarreraDeJuez.clear();
        for (Carrera carrera : juez.getCarrerasdeJuez()){
            vista.dlmCarreraDeJuez.addElement(carrera);
        }
    }

    /**
     * Método que agrega Carreras a un Juez
     */
    private void agregarCarrerasAJuez() {
        if(vista.listaJueces.isSelectionEmpty()){
            Util.mostrarDialogoError("no.se.ha.seleccionado.ningun.juez");
        }

        Juez juez=vista.listaJueces.getSelectedValue();
        LinkedList<Carrera> carreras = modelo.getCarreras();
        DialogoAgregarCarrerasAJuez d = new DialogoAgregarCarrerasAJuez(juez,carreras);

        listarCarrerasDeJuez(juez);

    }

    /**
     * Método que lista los competidores en un JList
     */
    private void listarCompetidoresEnJlist(){
        vista.dlmCompetidor.clear();
        for(Competidor competidor: modelo.getCompetidores()){
            vista.dlmCompetidor.addElement(competidor);
        }
    }

    /**
     * Método que comprueba que no estén vacíos los campos de Competidor
     * @return true/false
     */
    private boolean datosCompetidorCorrectos(){
        if (vista.txtDni.getText().isEmpty() || vista.txtNombreCompetidor.getText().isEmpty() || vista.txtApeCompetidor.getText().isEmpty()
                || vista.txtEdad.getText().isEmpty() || vista.txtAltura.getText().isEmpty()){
            return false;
        }
        return true;
    }
    private void modificarCompetidor() {
        if(vista.listCompetidores.isSelectionEmpty()){
            Util.mostrarDialogoError(resourceBundle.getString("no.se.ha.seleccionado.ningun.competidor"));
        }
        if(!datosCompetidorCorrectos()){
            Util.mostrarDialogoError(resourceBundle.getString("datos.incorrectos"));
        }
        Competidor competidor =vista.listCompetidores.getSelectedValue();
        if(!vista.txtDni.getText().trim().equalsIgnoreCase(competidor.getDni()) && modelo.existeDniCompetidor(vista.txtDni.getText().trim())){
            Util.mostrarDialogoError("el.dni.del.competidor.ya.existe");
        }

        competidor.setDni(vista.txtDni.getText().trim());
        competidor.setNombre(vista.txtNombreCompetidor.getText().trim());
        competidor.setApellidos(vista.txtApeCompetidor.getText().trim());
        competidor.setEdad(Integer.parseInt(vista.txtEdad.getText().trim()));
        competidor.setAltura(Double.parseDouble(vista.txtAltura.getText().trim()));
        competidor.setFoto(vista.lblImagen.getIcon());

    }

    /**
     * Método que elimina un Competidor del sistema
     */
    private void eliminarCompetidor() {
        if(vista.listCompetidores.isSelectionEmpty()){
            Util.mostrarDialogoError(resourceBundle.getString("no.se.ha.seleccionado.ningun.competidor"));
        }
        modelo.eliminarCompetidor(vista.listCompetidores.getSelectedValue());
        listarCompetidoresEnJlist();
    }

    /**
     * Método que anade un nuevo Competidor al Sistema
     */
    private void nuevoCompetidor() {
        if(!datosCompetidorCorrectos()){
            Util.mostrarDialogoError("datos.incorrectos");
        }
        if (modelo.existeDniCompetidor(vista.txtDni.getText().trim())){
            Util.mostrarDialogoError(resourceBundle.getString("el.dni.del.competidor.ya.existe"));
        }
        modelo.nuevoCompetidor(new Competidor(vista.txtDni.getText().trim(),vista.txtNombreCompetidor.getText().trim(),
                vista.txtApeCompetidor.getText().trim(),Integer.parseInt(vista.txtEdad.getText().trim())
                ,Double.parseDouble(vista.txtAltura.getText().trim()),vista.lblFoto.getIcon()));
    }

    /**
     * Método que borra los campos de Competidor
     */
    private void borrarCamposCompetidor(){
        vista.txtDni.setText("");
        vista.txtNombreCompetidor.setText("");
        vista.txtApeCompetidor.setText("");
        vista.txtEdad.setText("");
        vista.txtAltura.setText("");
        vista.lblImagen.setIcon(null);
    }

    /**
     * Método que refresca ls lista de Jueces en el ComboBox
     */
    private void listarJuecesEnComboBox() {
        vista.dcbmJuez.removeAllElements();
        vista.dcbmJuez.addElement(null);
        for(Juez juez: modelo.getJueces()){
            vista.dcbmJuez.addElement(juez);
        }
    }

    /**
     * Método que refresca la lista de Jueces en JList
     */
    private void listarJuecesEnJList() {
        vista.dlmJuez.clear();
        for(Juez juez: modelo.getJueces()){
            vista.dlmJuez.addElement(juez);
        }
    }

    /**
     * Método que comprueba que los datos del Juez sean correctos
     * @return true/false
     */
    private boolean datosJuezCorrectos() {
        if(vista.txtCodJuez.getText().isEmpty() || vista.txtNombreJuez.getText().isEmpty() || vista.txtApellidosJuez.getText().isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Método que elimina un Juez de la lista
     */
    private void eliminarJuez() {
        if(vista.listaJueces.isSelectionEmpty()){
            Util.mostrarDialogoError(resourceBundle.getString("no.se.ha.seleccionado.ningun.juez"));
        }

        Juez juez = vista.listaJueces.getSelectedValue();

        modelo.eliminarJuez(juez);
        listarJuecesEnJList();
        listarJuecesEnComboBox();

    }

    /**
     * Método que crea un nuevo Juez
     */
    private void nuevoJuez() {
        if(!datosJuezCorrectos()){
            Util.mostrarDialogoError(resourceBundle.getString("datos.incorrectos"));
        }
        if (modelo.existeCodigoJuez(vista.txtCodJuez.getText().trim())){
            Util.mostrarDialogoError(resourceBundle.getString("el.codigo.de.juez.ya.existe"));
        }

        Juez juez = new Juez(vista.txtCodJuez.getText().trim(),vista.txtNombreJuez.getText().trim(),vista.txtApellidosJuez.getText().trim());
        modelo.nuevoJuez(juez);
        listarJuecesEnComboBox();
        listarJuecesEnJList();

        vista.listaJueces.setSelectedValue(juez,true);
    }

    /**
     * Método que modifica todos los campos de un Juez
     */
    private void modificarJuez() {
        if(vista.listaJueces.isSelectionEmpty()){
            Util.mostrarDialogoError(resourceBundle.getString("no.se.ha.seleccionado.ningun.juez"));
        }

        if(!datosJuezCorrectos()){
            Util.mostrarDialogoError(resourceBundle.getString("datos.incorrectos"));
        }

        Juez juez =vista.listaJueces.getSelectedValue();

        if(!vista.txtCodJuez.getText().trim().equalsIgnoreCase(juez.getNumJuez()) && modelo.existeCodigoJuez(vista.txtCodJuez.getText().trim())){
            Util.mostrarDialogoError("el.codigo.de.juez.ya.existe");
        }

        juez.setNumJuez(vista.txtCodJuez.getText().trim());
        juez.setNombre(vista.txtNombreJuez.getText().trim());
        juez.setApellidos(vista.txtApellidosJuez.getText().trim());

        listarJuecesEnJList();
        listarJuecesEnComboBox();
    }

    /**
     * Método que muestra los datos de un Juez seleccionado
     */
    private void mostrarDatosJueces(){
        Juez juez = vista.listaJueces.getSelectedValue();
        if(juez==null){
            borrarCamposJuez();
        }else{
            vista.txtCodJuez.setText(juez.getNumJuez());
            vista.txtNombreJuez.setText(juez.getNombre());
            vista.txtApellidosJuez.setText(juez.getApellidos());
        }
    }

    /**
     * Método que muestra los datos de un Competidor seleccionado
     */
    private void mostrarDatosCompetidores(){
        Competidor competidor = vista.listCompetidores.getSelectedValue();
        if(competidor==null){
            borrarCamposCompetidor();
        }else{
            vista.txtDni.setText(competidor.getDni());
            vista.txtNombreCompetidor.setText(competidor.getNombre());
            vista.txtApeCompetidor.setText(competidor.getApellidos());
            vista.txtEdad.setText(String.valueOf(competidor.getEdad()));
            vista.txtAltura.setText(String.valueOf(competidor.getAltura()));
            vista.lblImagen.setIcon(competidor.getFoto());
        }
    }

    /**
     * Método que vacía todos los campos de Juez
     */
    private void borrarCamposJuez() {
        vista.txtCodJuez.setText("");
        vista.txtNombreJuez.setText("");
        vista.txtApellidosJuez.setText("");
    }

    /**
     * Método que vacía todos los campos de Carrera
     */
    private void borrarCamposCarrera(){
        vista.txtCarrera.setText("");
        vista.txtMetros.setText("");
        vista.txtLugar.setText("");
        vista.dpFecha.setDateToToday();
        vista.cbRealizado.setSelected(false);
        vista.cbJuezCarrera.setSelectedItem(null);
    }

    /**
     * Método que muestra los datos de una Carrera seleccionado
     */
    private void mostrarDatosCarrera(){
        Carrera carrera = vista.listCarrera.getSelectedValue();
        if(carrera==null){
            borrarCamposCarrera();
        }else{
            vista.txtCarrera.setText(carrera.getDenominacion());
            vista.txtMetros.setText(String.valueOf(carrera.getMetros()));
            vista.txtLugar.setText(carrera.getLugar());
            vista.dpFecha.setDate(carrera.getFecha());
            vista.cbRealizado.setSelected(carrera.isRealizado());
            vista.cbJuezCarrera.setSelectedItem(carrera.getJuezCarrera());

        }
    }




    /**
     * Método que carga datos de la aplicación
     */
    private void cargarDatos() {
        JFileChooser selector = new JFileChooser();
        int opcion = selector.showOpenDialog(vista.frame);
        if(opcion == JFileChooser.APPROVE_OPTION) {
            File fichero = selector.getSelectedFile();
            modelo.cargarDatos(fichero);
        }
    }

    /**
     * Método que guarda datos de la aplicación
     */
    private void guardarDatos() {
        JFileChooser selector = new JFileChooser();
        int opcion = selector.showSaveDialog(vista.frame);
        if(opcion == JFileChooser.APPROVE_OPTION) {
            File fichero = selector.getSelectedFile();
            modelo.guardarDatos(fichero);
        }
    }

    /**
     * Método que reacciona ante los ListSelectionListener
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == vista.listaJueces){
            mostrarDatosJueces();
        } else if( e.getSource() == vista.listCompetidores){
            mostrarDatosCompetidores();
        } else if (e.getSource() == vista.listCarrera){
            mostrarDatosCarrera();
        }
    }
}
