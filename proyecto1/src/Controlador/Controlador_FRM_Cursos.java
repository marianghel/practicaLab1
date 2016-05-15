/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ArchivoCurso;
import Modelo.Curso;
import Modelo.MetodosCursos;
import Vista.FRM_Cursos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Marianghel
 */
public class Controlador_FRM_Cursos implements ActionListener{
    FRM_Cursos mantenimientoCursos;
    public MetodosCursos metodos;
    ArchivoCurso archivo;
   
    public Controlador_FRM_Cursos(FRM_Cursos mantenimientoCursos)
    {
        this.mantenimientoCursos= mantenimientoCursos;
        metodos = new MetodosCursos();
        archivo=new ArchivoCurso();
       cargaArchivoVentana();
    } 
    public void agregarInformacionAlArchivo(){
        archivo.crearArchivoCurso();
        ArrayList <Curso> arrayTemporal=metodos.getArray();
        for(int contador=0;contador<arrayTemporal.size();contador++){
          this.archivo.ingresarInfoAlArchivo(arrayTemporal.get(contador));
        }
    }
        public void cargaArchivoVentana(){
          ArrayList <Curso> arrayCargaArchivo= archivo.devolverInfoDelArchivo();
          metodos.setArray(arrayCargaArchivo);
          
          for(int contador=0;contador<arrayCargaArchivo.size();contador++){
              System.out.println("arraycargararchivo"+arrayCargaArchivo);
          }
        }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Buscar"))
        {
           buscar();
        }
        if(e.getActionCommand().equals("Agregar"))
        {
            metodos.agregarCurso(mantenimientoCursos.devolverInformacion());   
            metodos.mostrarInformacion();
            this.mantenimientoCursos.mostrarMensaje("Curso agregado");
            mantenimientoCursos.limpiarInterfaz();
            this.mantenimientoCursos.estadoInicial();
                    
        }
        
        if(e.getActionCommand().equals("Modificar"))
        {
            metodos.modificarCurso(this.mantenimientoCursos.devolverInformacion());
            this.mantenimientoCursos.mostrarMensaje("Curso modificado");
            this.mantenimientoCursos.limpiarInterfaz();
           this.mantenimientoCursos.estadoInicial();
            
        }
        if(e.getActionCommand().equals("Eliminar"))
        {
            metodos.eliminarCurso(mantenimientoCursos.devolverInformacion());
           this.mantenimientoCursos.mostrarMensaje("Curso eliminado");
            this.mantenimientoCursos.limpiarInterfaz();
           this.mantenimientoCursos.estadoInicial();
        }
    }
    public void buscar(){
       if(metodos.consultarCurso(mantenimientoCursos.devolverSigla()))
            {
                 this.mantenimientoCursos.modificaElimina();
                 this.mantenimientoCursos.mostrarMensaje("Curso encontrado");
                mantenimientoCursos.mostrarInformacion(metodos.getArregloInformacion());
            }
            else
            {
                  this.mantenimientoCursos.mostrarMensaje("Curso no encontrado");
                   this.mantenimientoCursos.agregar();

            }
    }
}
