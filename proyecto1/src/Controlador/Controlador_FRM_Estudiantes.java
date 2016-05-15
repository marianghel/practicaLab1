/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ArchivoEstudiante;
import Modelo.Estudiante;
import Modelo.MetodosEstudiantes;
import Vista.FRM_Estudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_FRM_Estudiantes implements ActionListener{
    
    
     public MetodosEstudiantes metodos;
    FRM_Estudiantes mantenimientoEstudiantes;
    ArchivoEstudiante archivo;
    
    public Controlador_FRM_Estudiantes(FRM_Estudiantes mantenimientoEstudiantes)
    {
        this.mantenimientoEstudiantes=mantenimientoEstudiantes;
        metodos = new MetodosEstudiantes();
        archivo=new ArchivoEstudiante();
        cargaArchivoVentana();
    }
    public void agregarInformacionAlArchivo(){
        archivo.crearArchivoEstudiante();
        ArrayList <Estudiante> arrayTemporal=metodos.getArray();
        for(int contador=0;contador<arrayTemporal.size();contador++){
          this.archivo.ingresarInfoAlArchivo(arrayTemporal.get(contador));
         
        }
    }
        public void cargaArchivoVentana(){
          ArrayList <Estudiante> arrayCargaArchivo= archivo.devolverInformacionDelArchivo();
          metodos.setArray(arrayCargaArchivo);
          
          for(int contador=0;contador<arrayCargaArchivo.size();contador++){
              System.out.println("arraycargararchivo"+arrayCargaArchivo);
          }
        }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Consulta"))
        {
            buscar();
        }
        if(e.getActionCommand().equals("Buscar"))
        {
           buscar();
        }
        if(e.getActionCommand().equals("Agregar"))
        {
            metodos.agregarEstudiante(mantenimientoEstudiantes.devolverInformacion());
            metodos.mostrarInformacion();
            this.mantenimientoEstudiantes.mostrarMensaje("Estudiante agregado");
            this.mantenimientoEstudiantes.limpiarInterfaz();
           this.mantenimientoEstudiantes.estadoInicial();
        }
        if(e.getActionCommand().equals("Modificar"))
        { metodos.modificarEstudiante(mantenimientoEstudiantes.devolverInformacion());
           this.mantenimientoEstudiantes.mostrarMensaje("Estudiante modificado");
            this.mantenimientoEstudiantes.limpiarInterfaz();
           this.mantenimientoEstudiantes.estadoInicial();
            
        }
        if(e.getActionCommand().equals("Eliminar"))
        {
           metodos.eliminarEstudiante(mantenimientoEstudiantes.devolverInformacion());
           this.mantenimientoEstudiantes.mostrarMensaje("Estudiante eliminado");
            this.mantenimientoEstudiantes.limpiarInterfaz();
           this.mantenimientoEstudiantes.estadoInicial();
        }
       }
     public void buscar(){
              
              if(this.metodos.consultarEstudiante(this.mantenimientoEstudiantes.devolverCedula())){
                  this.mantenimientoEstudiantes.modificaElimina();
                  this.mantenimientoEstudiantes.mostrarMensaje("Estudiante encontrado");
                  this.mantenimientoEstudiantes.mostrarInformacion(metodos.getArregloInformacion());
                 
              }else{
                   
                   this.mantenimientoEstudiantes.mostrarMensaje("Estudiante no encontrado");
                   this.mantenimientoEstudiantes.agregar();

              }
}
}