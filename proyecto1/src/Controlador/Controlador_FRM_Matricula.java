/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.ArchivoMatricula;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Matricula;
import Modelo.MetodosCursos;
import Modelo.MetodosEstudiantes;
import Modelo.MetodosMatricula;
import Vista.FRM_Cursos;
import Vista.FRM_Estudiantes;
import Vista.FRM_Matricula;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angelica
 */
public class Controlador_FRM_Matricula implements ActionListener {

    MetodosCursos metodosCursos;
    MetodosEstudiantes metodosEstudiantes;
    MetodosMatricula metodosMatricula;
    FRM_Matricula frm_Matricula;
    Controlador_FRM_MenuPrincipal controladorP;
    ArchivoMatricula archivo;
    
    DefaultTableModel modelo;
    
     public ArrayList <Matricula> arrayMatricula;
     public ArrayList <Estudiante> arrayEstudiantes;
     public ArrayList <Curso> arrayCursos;
    
    boolean encontroEstudiante = false;
    boolean encontroCurso = false;

    public Controlador_FRM_Matricula(FRM_Estudiantes mantenimientoEstudiantes, FRM_Cursos mantenimientoCursos, FRM_Matricula frm_Matricula, Controlador_FRM_MenuPrincipal controladorP) {
        this.metodosCursos = mantenimientoCursos.controlador_FRM_Cursos.metodos;
        this.metodosEstudiantes = mantenimientoEstudiantes.controlador_FRM_Estudiantes.metodos;
        this.frm_Matricula = frm_Matricula;
        this.metodosMatricula = new MetodosMatricula();
        this.controladorP = controladorP;
         archivo = new ArchivoMatricula();
         cargaArchivoVentana();
//        ventanaMatricula.deshabilitarNombresyCodigo();
    }
    
//Metodos para q guarde en archivos
public void agregarInformacionAlArchivo() {
        archivo.crearArchivoMatricula();
        ArrayList<Matricula> arrayTemporal = metodosMatricula.getArray();
        for (int contador = 0; contador < arrayTemporal.size(); contador++) {
            this.archivo.ingresarInfoAlArchivo(arrayTemporal.get(contador));
        }
    }
    public void cargaArchivoVentana() {
        ArrayList <Matricula> arrayCargaArchivo = archivo.devolverInformacionDelArchivo();
        metodosMatricula.setArray(arrayCargaArchivo);

        for (int contador = 0; contador < arrayCargaArchivo.size(); contador++) {
            System.out.println("arraycargararchivo" + arrayCargaArchivo);
        }
    }    

    public String colocarCodigo() {
        return metodosMatricula.devolverCodigo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    //ConsultasRapidas-------------------------------------------
        if (e.getActionCommand().equals("ConsultaRapidaEstudiante"))
        {
            //Archovo plano    
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1)
            {
                this.buscarEstudiante();
                
            }
        }
        if (e.getActionCommand().equals("ConsultaRapidaCurso")) 
        {
            //Archovo plano    
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1)
            {
                this.buscarCurso();
            }
        }
         habilitarAgregar();
//Botones--------------------------------------------------------
        if (e.getActionCommand().equals("Agregar")) {
            //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1)
            {    
                System.out.println("agregar");
                frm_Matricula.cargarTabla();
                encontroCurso = false;
                frm_Matricula.estadoInicial();
                frm_Matricula.limpiarCurso();
            }
        }

           if (e.getActionCommand().equals("Finalizar Matricula")) {
            System.out.println("finalizar");
            //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
               finalizar();
            }
        }

           if (e.getActionCommand().equals("Modificar")) {
            //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {

            }
        }
        if (e.getActionCommand().equals("Buscar")) {
            //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                if (metodosMatricula.consultarMatricula(frm_Matricula.devolverCodigo())) {
                    String arreglo[]=metodosMatricula.getArregloInformacion();
                     frm_Matricula.mostrarNombreEstudiante(arreglo[0]);
                     frm_Matricula.mostrarNombreCurso(arreglo[1]);
                     
                     //tocar
                     String cedula="";
                     String sigla="";
                     String arregloTabla[]=new String[4];
                    for (int contador = 0; contador < arrayMatricula.size(); contador++) {
                    if(arrayMatricula.get(contador).getCodigo().equals(frm_Matricula.devolverCodigo()))
                    {
                    arregloTabla[0]= this.arrayMatricula.get(contador).getCedula();
                    arregloTabla[2]=this.arrayMatricula.get(contador).getSigla();
                    cedula=arregloTabla[0];
                    sigla=arregloTabla[2];
                    }
                    }
                     for(int contador=0;contador<arrayEstudiantes.size();contador++){
                         if(arrayEstudiantes.get(contador).getCedula().equals(cedula)){
                             arregloTabla[1]=this.arrayEstudiantes.get(contador).getNombre();
                         }
                     }
                      for(int contador=0;contador<arrayCursos.size();contador++){
                          if(arrayCursos.get(contador).getSiglas().equals(sigla)){
                              arregloTabla[3]=this.arrayCursos.get(contador).getNombre();
                          }
                      }
                      modelo.addRow(arregloTabla);
                     }
            }
        }
            if (e.getActionCommand().equals("Eliminar")) {
                //Archivo plano
             if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                 frm_Matricula.eliminarTabla();
             }

            }
        }
    
        public void habilitarAgregar(){
             if (encontroEstudiante && encontroCurso) {
                frm_Matricula.habilitarAgregar();
            }
        }
        public boolean buscarEstudiante() {

            if (metodosEstudiantes.consultarEstudiante(frm_Matricula.devolverCedula())) {
                String arreglo[] = metodosEstudiantes.getArregloInformacion();
                frm_Matricula.mostrarNombreEstudiante(arreglo[1]);
                encontroEstudiante = true;
            } else {
                frm_Matricula.mostrarMensaje("El estudiante consultado no se encuentra en el archivo plano, favor dirigirse al módulo de Mantenimiento Estudiantes");
            }
            return encontroEstudiante;
        }

        public boolean buscarCurso() {
            if (metodosCursos.consultarCurso(frm_Matricula.devolverSigla())) {
                String arreglo[] = metodosCursos.getArregloInformacion();
                frm_Matricula.mostrarNombreCurso(arreglo[1]);
                encontroCurso = true;
            } else {
                frm_Matricula.mostrarMensaje("El curso consultado no se encuentra, favor dirigirse al módulo de Mantenimiento Cursos");
            }
            return encontroCurso;
        }
        
        public void finalizar() {
            for (int contador = 0; contador < frm_Matricula.getCantidadDeCursosMatriculados(); contador++) {
                metodosMatricula.agregarMatricula(frm_Matricula.getInformacionTabla(contador));
            }
            frm_Matricula.resetearInterfaz();
            metodosMatricula.mostrarInformacion();
             frm_Matricula.mostrarMensaje("Finalizó matricula");

            //habilitarAgregar();
        }
    }

    

