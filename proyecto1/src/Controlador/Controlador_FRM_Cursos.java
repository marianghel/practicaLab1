/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ArchivoCurso;
import Modelo.Curso;
import Modelo.MetodosCursos;
import Modelo.MetodosCursos_XML;
import Vista.FRM_Cursos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Marianghel
 */
public class Controlador_FRM_Cursos implements ActionListener {
     
    FRM_Cursos mantenimientoCursos;
    public MetodosCursos metodos;
    ArchivoCurso archivo;
    Controlador_FRM_MenuPrincipal controladorP;
    MetodosCursos_XML metodosXML;

    public Controlador_FRM_Cursos(FRM_Cursos mantenimientoCursos,Controlador_FRM_MenuPrincipal controladorP) {
        this.mantenimientoCursos = mantenimientoCursos;
        this.controladorP = controladorP;
        metodos = new MetodosCursos();
        metodosXML = new MetodosCursos_XML(mantenimientoCursos);
        archivo = new ArchivoCurso();
        cargaArchivoVentana();
    }

    public void agregarInformacionAlArchivo() {
        archivo.crearArchivoCurso();
        ArrayList<Curso> arrayTemporal = metodos.getArray();
        for (int contador = 0; contador < arrayTemporal.size(); contador++) {
            this.archivo.ingresarInfoAlArchivo(arrayTemporal.get(contador));
        }
    }

    public void cargaArchivoVentana() {
        ArrayList<Curso> arrayCargaArchivo = archivo.devolverInfoDelArchivo();
        metodos.setArray(arrayCargaArchivo);

        for (int contador = 0; contador < arrayCargaArchivo.size(); contador++) {
            System.out.println("arraycargararchivo" + arrayCargaArchivo);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Buscar")) {
            buscar();
        }
        if (e.getActionCommand().equals("Agregar")) {
            //archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodos.agregarCurso(mantenimientoCursos.devolverInformacion());
                metodos.mostrarInformacion();
                this.mantenimientoCursos.mostrarMensaje("Curso agregado");
                mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
            //bases de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                controladorP.conexion.registrarCurso(mantenimientoCursos.devolverInformacion());
                metodos.mostrarInformacion();
                this.mantenimientoCursos.mostrarMensaje("Curso agregado");
                mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
            //agregar XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                metodosXML.guardarEnXML(mantenimientoCursos.devolverInformacion());
                metodosXML.getArregloInformacion();
                this.mantenimientoCursos.mostrarMensaje("Curso agregado");
                mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }

        }

         if (e.getActionCommand().equals("Modificar")){
            //archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodos.modificarCurso(this.mantenimientoCursos.devolverInformacion());
                this.mantenimientoCursos.mostrarMensaje("Curso modificadoen archivos");
                this.mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
            //base de datos
            System.out.println("la opcion que tiene esta cuestion que tirar como resultado tiene que ser esto para la opcion de modificar"+controladorP.mantenimientoFuente.devolverOpcionFuente());
             if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                 System.out.println("If para modificar el curso prubea de las 6 de la noche");
                controladorP.conexion.actualizarCurso(mantenimientoCursos.devolverSigla(), mantenimientoCursos.devolverNombre(), mantenimientoCursos.devolverCreditos(), mantenimientoCursos.devolverHorario());
                this.mantenimientoCursos.mostrarMensaje("Curso modificado en base de datos");
                this.mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
            //xml
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                metodosXML.modificarInformacionDelXml(mantenimientoCursos.devolverInformacion());
               
                this.mantenimientoCursos.mostrarMensaje("Curso modificado en xml");
                this.mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }

        }
        if (e.getActionCommand().equals("Eliminar")) {
            //archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodos.eliminarCurso(mantenimientoCursos.devolverInformacion());
                this.mantenimientoCursos.mostrarMensaje("Curso eliminado");
                this.mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
            //bases de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                controladorP.conexion.eliminarCurso(mantenimientoCursos.devolverSigla());
                this.mantenimientoCursos.mostrarMensaje("Curso eliminado");
                this.mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
            //XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                metodosXML.eliminarInformacionDelXml(mantenimientoCursos.devolverSigla());
                this.mantenimientoCursos.mostrarMensaje("Curso eliminado");
                this.mantenimientoCursos.limpiarInterfaz();
                this.mantenimientoCursos.estadoInicial();
            }
        }
    }

    public void buscar() {

        //archivo
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
            if (metodos.consultarCurso(mantenimientoCursos.devolverSigla())) {
                this.mantenimientoCursos.modificaElimina();
                this.mantenimientoCursos.mostrarMensaje("Curso encontrado");
                mantenimientoCursos.mostrarInformacion(metodos.getArregloInformacion());
            } else {
                this.mantenimientoCursos.mostrarMensaje("Curso no encontrado");
                this.mantenimientoCursos.agregar();

            }
        }
        //base de datos

        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
            
            System.out.println("sigla :"+mantenimientoCursos.devolverSigla());
            
            if (controladorP.conexion.consultarCurso(mantenimientoCursos.devolverSigla())) {
                System.out.println("haha");
                this.mantenimientoCursos.modificaElimina();
                this.mantenimientoCursos.mostrarMensaje("Curso encontrado");
                mantenimientoCursos.mostrarInformacion(controladorP.conexion.getArregloCurso());
            } else {
                this.mantenimientoCursos.mostrarMensaje("Curso no encontrado");
                this.mantenimientoCursos.agregar();
            }
        }
        //xml
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
            System.out.println("Entra a buscar");
                if (this.metodosXML.consultarInformacionDelXml(mantenimientoCursos.devolverSigla())) {
                    
                    this.mantenimientoCursos.modificaElimina();
                    this.mantenimientoCursos.mostrarMensaje("Curso encontrado");
                    mantenimientoCursos.mostrarInformacion(metodosXML.getArregloInformacion());
                } else {
                    this.mantenimientoCursos.mostrarMensaje("Curso no encontrado");
                    this.mantenimientoCursos.agregar();

                }
            }
        }
    }

