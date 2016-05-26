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
import Modelo.MetodosMatricula_XML;
import Vista.FRM_Cursos;
import Vista.FRM_Estudiantes;
import Vista.FRM_Matricula;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    MetodosMatricula_XML metodosXML;

    DefaultTableModel modelo = new DefaultTableModel();

    public ArrayList<Matricula> arrayMatricula;
    public ArrayList<Estudiante> arrayEstudiantes;
    public ArrayList<Curso> arrayCursos;

   public boolean encontroEstudiante = false;
   public boolean encontroCurso = false;

    public Controlador_FRM_Matricula(FRM_Estudiantes mantenimientoEstudiantes, FRM_Cursos mantenimientoCursos, FRM_Matricula frm_Matricula, Controlador_FRM_MenuPrincipal controladorP) {
        this.metodosCursos = mantenimientoCursos.controlador_FRM_Cursos.metodos;
        this.metodosEstudiantes = mantenimientoEstudiantes.controlador_FRM_Estudiantes.metodos;
        this.frm_Matricula = frm_Matricula;
        this.metodosMatricula = new MetodosMatricula();
        this.controladorP = controladorP;
        this.arrayMatricula = metodosMatricula.getArray();
        this.arrayEstudiantes = metodosEstudiantes.getArray();
        this.arrayCursos = metodosCursos.getArray();
        agregarColumnas();
        archivo = new ArchivoMatricula();
        metodosXML = new MetodosMatricula_XML(controladorP.matricula);

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
        ArrayList<Matricula> arrayCargaArchivo = archivo.devolverInformacionDelArchivo();
        metodosMatricula.setArray(arrayCargaArchivo);

        for (int contador = 0; contador < arrayCargaArchivo.size(); contador++) {
            System.out.println("arraycargararchivo" + arrayCargaArchivo);
        }
    }

    public void agregarColumnas() {
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre Estudiante");
        modelo.addColumn("Sigla");
        modelo.addColumn("Nombre Curso");
    }

    public String colocarCodigo() {
        return metodosMatricula.devolverCodigo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //ConsultasRapidas-------------------------------------------
        if (e.getActionCommand().equals("ConsultaRapidaEstudiante")) {
            //Archivo plano    
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                this.buscarEstudianteArchivoPlano();
            }
            //Bases de datos    
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
               buscarEstudianteBaseDatos();
            }
            //XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                String arregloEstudiante[]=metodosXML.getArregloInformacion();
            }
        }
        if (e.getActionCommand().equals("ConsultaRapidaCurso")) {
            //Archovo plano    
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                this.buscarCurso();
            }
            //Bases de datos    
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
               buscarCursoBaseDato();
            }
            //XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {

            }
        }
        agregar();

//Botones--------------------------------------------------------
        if (e.getActionCommand().equals("Agregar")) {
            //Archivo plano//baseDatos//Xml. solo agrega a la tabla

            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1
                    || controladorP.mantenimientoFuente.devolverOpcionFuente() == 2
                    || controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                
                   // while(revisarRepeticionEstudianteTabla(frm_Matricula.devolverCodigo())&&control==0){
                    System.out.println("agregar tabla");
                    frm_Matricula.cargarTabla();
                    encontroCurso = false;
                    frm_Matricula.estadoInicial();
                    frm_Matricula.limpiarEspaciosConsulta();
                   
               // } 
                    /*frm_Matricula.inhabilitarCedula();
                    frm_Matricula.inhabilitarAgregar();
                    frm_Matricula.soloLimpiaTabla();*/
               }
                }
            
        
        if (e.getActionCommand().equals("Finalizar Matricula")) {
            //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1)
            {
                finalizarArchivosPlanos();
            }
            //Base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) 
            {
                 finalizarBaseDatos();
            }
            //XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) 
            {
              
            }
            }

        if (e.getActionCommand().equals("Modificar")) {
             //Archivo plano//baseDatos//Xml. solo agrega a la tabla

            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1
                    || controladorP.mantenimientoFuente.devolverOpcionFuente() == 2
                    || controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                
                frm_Matricula.resetearInterfaz();
                frm_Matricula.habilitarAgregar();
                frm_Matricula.estadoInicial();
            }
        }
        if (e.getActionCommand().equals("Buscar")) {
            frm_Matricula.soloLimpiaTabla();
            //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                consultaMatriculaArchivoPlano();
            }
            //Base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
               consultaMatriculaBaseDatos();
            }
        }
        
        if (e.getActionCommand().equals("Eliminar")) {
          //Archivo Plano
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
            eliminarTabla();
            eliminarMatricula();
            frm_Matricula.resetearInterfaz();
         }
        //Base de datos
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
            eliminarTabla();
            controladorP.conexion.eliminarMatricula(frm_Matricula.devolverCodigo());
            frm_Matricula.resetearInterfaz();
        }
        }
    }
    
//Metodos-----------------------------------------------------------------
public boolean revisarRepeticionEstudianteTabla(String codigo){
            boolean revisar=false;
                for (int contador = 0; contador < arrayMatricula.size(); contador++) {
                    if (arrayMatricula.get(contador).getCodigo().equals(frm_Matricula.devolverCodigo())) {
                    revisar=true;
                    }
                }   
                return revisar;
        }   

public void agregar() {
        if (encontroEstudiante && encontroCurso) {
            frm_Matricula.habilitarAgregar();
        }
    }
    
 //Buscar rapida estudiante------------------------------------
    public boolean buscarEstudianteArchivoPlano() {

        if (metodosEstudiantes.consultarEstudiante(frm_Matricula.devolverCedula())) {
            String arreglo[] = metodosEstudiantes.getArregloInformacion();
            frm_Matricula.mostrarNombreEstudiante(arreglo[1]);
            encontroEstudiante = true;
        } else {
            frm_Matricula.mostrarMensaje("El estudiante consultado no se encuentra en el archivo plano, favor dirigirse al módulo de Mantenimiento Estudiantes");
        }
        return encontroEstudiante;
    }
    
    public boolean buscarEstudianteBaseDatos() {
        if (controladorP.conexion.consultarEstudiante(frm_Matricula.devolverCedula())) {
            String arregloEstudiante[] = controladorP.conexion.getArreglo();
            frm_Matricula.mostrarNombreEstudiante(arregloEstudiante[1]);
            encontroEstudiante = true;
        } else {
            frm_Matricula.mostrarMensaje("El estudiante consultado no se encuentra registrado en la base de datos");
        }
        return encontroEstudiante;
    }
//busqueda rapida curso--------------------------------------
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
    
    public boolean buscarCursoBaseDato() {
        if (controladorP.conexion.consultarCurso(frm_Matricula.devolverSigla())) {
            String arregloCurso[] = controladorP.conexion.getArregloCurso();
            frm_Matricula.mostrarNombreCurso(arregloCurso[1]);
        } else {
            frm_Matricula.mostrarMensaje("El curso consultado no se encuentra registrado en la base de datos");

        }
        return encontroCurso;
    }
    
    //CONSULTA MATRICULA--------------------------------------------------------------------------------------
     public void consultaMatriculaArchivoPlano(){
                if (metodosMatricula.consultarMatricula(frm_Matricula.devolverCodigo())) {
                    String arregloMatricula[] = metodosMatricula.getArregloInformacion();
                    metodosEstudiantes.consultarEstudiante(arregloMatricula[0]);
                    String arregloEstudiante[] = metodosEstudiantes.getArregloInformacion();
                    metodosCursos.consultarCurso(arregloMatricula[1]);
                    String arregloCurso[] = metodosCursos.getArregloInformacion();
                    frm_Matricula.mostrarNombreEstudiante(arregloEstudiante[1]);
                    frm_Matricula.mostrarNombreCurso(arregloCurso[1]);
                 
                    //tabla
                    String cedula = "";
                    String sigla = "";
                    String arregloTabla[] = new String[4];
                        for (int contador = 0; contador < arrayMatricula.size(); contador++)
                        {
                            if (arrayMatricula.get(contador).getCodigo().equals(frm_Matricula.devolverCodigo())) 
                            {
                            arregloTabla[0] = this.arrayMatricula.get(contador).getCedula();
                            arregloTabla[2] = this.arrayMatricula.get(contador).getSigla();
                            cedula = arregloTabla[0];
                            sigla = arregloTabla[2];
                            }
                        }
                    arregloTabla[0] = arregloMatricula[0];
                    arregloTabla[1] = arregloEstudiante[1];
                    arregloTabla[2] = arregloMatricula[1];
                    arregloTabla[3] = arregloCurso[1];

                    modelo.addRow(arregloTabla);
                    frm_Matricula.tbl_Matricula.setModel(modelo);
                        
                    frm_Matricula.modificarElimina();
                    frm_Matricula.mostrarMensaje("Matricula encontrada en el archivo plano");
                    }else{
                    frm_Matricula.estadoInicial();
                    frm_Matricula.mostrarMensaje("Matricula no encontrada en el archivo plano");
                    }
     
     }
     public void consultaMatriculaBaseDatos(){
     if (controladorP.conexion.consultarMatricula(frm_Matricula.devolverCodigo())) {
                    String arregloMatricula[] = controladorP.conexion.devolverArregloMatricula();
                    controladorP.conexion.consultarEstudiante(arregloMatricula[0]);
                    String arregloEstudiante[] = controladorP.conexion.getArreglo();
                    controladorP.conexion.consultarCurso(arregloMatricula[1]);
                    String arregloCurso[] = controladorP.conexion.getArregloCurso();
                    frm_Matricula.mostrarNombreEstudiante(arregloEstudiante[1]);
                    frm_Matricula.mostrarNombreCurso(arregloCurso[1]);
                    frm_Matricula.modificarElimina();
                    //TABLA
                    String cedula = "";
                    String sigla = "";
                    String arregloTabla[] = new String[4];
                    for (int contador = 0; contador < arrayMatricula.size(); contador++) {
                        if (arrayMatricula.get(contador).getCodigo().equals(frm_Matricula.devolverCodigo())) {
                            arregloTabla[0] = this.arrayMatricula.get(contador).getCedula();
                            arregloTabla[2] = this.arrayMatricula.get(contador).getSigla();
                            cedula = arregloTabla[0];
                            sigla = arregloTabla[2];
                        }
                    }
                    arregloTabla[0] = arregloMatricula[0];
                    arregloTabla[1] = arregloEstudiante[1];
                    arregloTabla[2] = arregloMatricula[1];
                    arregloTabla[3] = arregloCurso[1];
                    modelo.addRow(arregloTabla);
                    frm_Matricula.tbl_Matricula.setModel(modelo);
                }
                frm_Matricula.resetearInterfaz(); 
     }
//FINALIZAR---------------------------------------------------------------------
    public void finalizarArchivosPlanos() {
        for (int contador = 0; contador < frm_Matricula.getCantidadDeCursosMatriculados(); contador++) {
            metodosMatricula.agregarMatricula(frm_Matricula.getInformacionTabla(contador));
        }
        frm_Matricula.resetearInterfaz();
        metodosMatricula.mostrarInformacion();
        frm_Matricula.mostrarMensaje("Finalizó matricula archivo plano");

        //habilitarAgregar();
    }
    
    public void finalizarBaseDatos(){
         for (int contador = 0; contador < frm_Matricula.getCantidadDeCursosMatriculados(); contador++) {
                    String arregloMatricula[] = frm_Matricula.getInformacionTabla(contador);
                    controladorP.conexion.registrarMatricula(arregloMatricula[0], arregloMatricula[1]);
                    controladorP.conexion.registrarMatriculaDetallada(arregloMatricula[0], arregloMatricula[2]);
                }
                frm_Matricula.resetearInterfaz();
                metodosMatricula.mostrarInformacion();
                frm_Matricula.mostrarMensaje("Finalizó matricula en base de datos");
    }
    //eliminar------------------------------------------------------------
    public void eliminarMatricula(){
     //Archivo plano
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
                metodosMatricula.eliminarMatricula(frm_Matricula.devolverInformacion());
                frm_Matricula.estadoInicial();
            }
            //Base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
                controladorP.conexion.eliminarMatricula(frm_Matricula.devolverCodigo());
                frm_Matricula.resetearInterfaz();
            }
            //XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
                metodosXML.eliminarInformacionDelXml(frm_Matricula.devolverCodigo());
                frm_Matricula.resetearInterfaz();
            }

        }
    public void eliminarTabla() {
        int numeroFila = frm_Matricula.tbl_Matricula.getSelectedRow();

        if (numeroFila < 0) {
          
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea Eliminar el registro? ");
            if (JOptionPane.OK_OPTION == confirmar) {
                //modelo = (DefaultTableModel)tbl_Matricula.getModel();
                this.modelo.removeRow(numeroFila);
                frm_Matricula.tbl_Matricula.setModel(modelo);
                this.metodosMatricula.eliminarMatricula(frm_Matricula.devolverInformacion());
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        }
    }
}
