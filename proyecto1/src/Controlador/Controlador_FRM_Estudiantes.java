/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ArchivoEstudiante;
import Modelo.Estudiante;
import Modelo.MetodosEstudiante_XML;
import Modelo.MetodosEstudiantes;
import Vista.FRM_Estudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_FRM_Estudiantes implements ActionListener {

    public MetodosEstudiantes metodos;
    FRM_Estudiantes mantenimientoEstudiantes;
    ArchivoEstudiante archivo;
    Controlador_FRM_MenuPrincipal controladorP;
    MetodosEstudiante_XML metodosXML;

    public Controlador_FRM_Estudiantes(FRM_Estudiantes mantenimientoEstudiantes, Controlador_FRM_MenuPrincipal controladorP) {
        this.mantenimientoEstudiantes = mantenimientoEstudiantes;
        this.controladorP = controladorP;
        metodos = new MetodosEstudiantes();
        archivo = new ArchivoEstudiante();
        cargaArchivoVentana();
        metodosXML = new MetodosEstudiante_XML(mantenimientoEstudiantes);
    }
    


    public void agregarInformacionAlArchivo() {
        archivo.crearArchivoEstudiante();
        ArrayList<Estudiante> arrayTemporal = metodos.getArray();
        for (int contador = 0; contador < arrayTemporal.size(); contador++) {
            this.archivo.ingresarInfoAlArchivo(arrayTemporal.get(contador));
        }
    }

    public void cargaArchivoVentana() {
        ArrayList<Estudiante> arrayCargaArchivo = archivo.devolverInformacionDelArchivo();
        metodos.setArray(arrayCargaArchivo);

        for (int contador = 0; contador < arrayCargaArchivo.size(); contador++) {
            System.out.println("arraycargararchivo" + arrayCargaArchivo);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Consulta")) {
            buscar();
        }
        if (e.getActionCommand().equals("Buscar")) {
            buscar();
        }
        if (e.getActionCommand().equals("Agregar")) {
            //archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodos.agregarEstudiante(mantenimientoEstudiantes.devolverInformacion());
                metodos.mostrarInformacion();//muestra por consola
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante agregado al archivo");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
            //bases de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                controladorP.conexion.registrarEstudiante(mantenimientoEstudiantes.devolverInformacion());
                //metodos.mostrarInformacion();
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante agregado a la base de datos");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
            //agregar XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==3) {
                metodosXML.guardarEnXML(mantenimientoEstudiantes.devolverInformacion());
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante agregado");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
        }
        if (e.getActionCommand().equals("Modificar")) //archivo
        {
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodos.modificarEstudiante(mantenimientoEstudiantes.devolverInformacion());
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante modificado");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
            //base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                controladorP.conexion.actualizarEstudiante(mantenimientoEstudiantes.devolverCedula(), mantenimientoEstudiantes.devolverNombre(), mantenimientoEstudiantes.devolverDireccion());
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante modificado");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
            // XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                {
                    metodosXML.modificarInformacionDelXml(mantenimientoEstudiantes.devolverInformacion());
                    this.mantenimientoEstudiantes.mostrarMensaje("Estudiante modificado");
                    this.mantenimientoEstudiantes.limpiarInterfaz();
                    this.mantenimientoEstudiantes.estadoInicial();
                }
            }
        }
        if (e.getActionCommand().equals("Eliminar")) {
            //archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodos.eliminarEstudiante(mantenimientoEstudiantes.devolverInformacion());
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante eliminado");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
            //base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                controladorP.conexion.eliminarEstudiante(mantenimientoEstudiantes.devolverCedula());
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante eliminado");
                this.mantenimientoEstudiantes.limpiarInterfaz();
                this.mantenimientoEstudiantes.estadoInicial();
            }
            //XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                    metodosXML.eliminarInformacionDelXml(mantenimientoEstudiantes.devolverCedula());
                    this.mantenimientoEstudiantes.mostrarMensaje("Estudiante eliminado");
                    this.mantenimientoEstudiantes.limpiarInterfaz();
                    this.mantenimientoEstudiantes.estadoInicial();
                }
            }
        }
    

    public void buscar() {
        //archivo
        //System.out.println("Estoy en buscar");
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
            if (this.metodos.consultarEstudiante(this.mantenimientoEstudiantes.devolverCedula())) {
                this.mantenimientoEstudiantes.modificaElimina();
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante encontrado");
                this.mantenimientoEstudiantes.mostrarInformacion(metodos.getArregloInformacion());

            } else {

                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante no encontrado");
                this.mantenimientoEstudiantes.agregar();

            }
        }
        //base de datos
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
           
            if (controladorP.conexion.consultarEstudiante(mantenimientoEstudiantes.devolverCedula())) {
                this.mantenimientoEstudiantes.modificaElimina();
                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante encontrado");
                this.mantenimientoEstudiantes.mostrarInformacion(controladorP.conexion.getArreglo());

            } else {

                this.mantenimientoEstudiantes.mostrarMensaje("Estudiante no encontrado");
                this.mantenimientoEstudiantes.agregar();
            }
        }
        //xml
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
            if (controladorP.archivoUsuario.confirmarUsuario(controladorP.login.devolverInformacion())) {

                System.out.println("Entra a buscar");
                if (this.metodosXML.consultarInformacionDelXml(this.mantenimientoEstudiantes.devolverCedula())) {
                    this.mantenimientoEstudiantes.modificaElimina();
                    this.mantenimientoEstudiantes.mostrarMensaje("Estudiante encontrado");
                    this.mantenimientoEstudiantes.mostrarInformacion(metodosXML.getArregloInformacion());

                } else {

                    this.mantenimientoEstudiantes.mostrarMensaje("Estudiante no encontrado");
                    this.mantenimientoEstudiantes.agregar();

                }
            }
        }
    }
}
