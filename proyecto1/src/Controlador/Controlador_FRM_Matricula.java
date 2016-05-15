/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MetodosCursos;
import Modelo.MetodosEstudiantes;
import Modelo.MetodosMatricula;
import Vista.FRM_Cursos;
import Vista.FRM_Estudiantes;
import Vista.FRM_Matricula;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controlador_FRM_Matricula implements ActionListener{
    
    MetodosCursos metodosCursos;
    MetodosEstudiantes metodosEstudiantes;
    MetodosMatricula metodosMatricula;
    FRM_Matricula frm_Matricula;
    
    boolean encontroEstudiante=false; 
    boolean encontroCurso=false;
    
    public Controlador_FRM_Matricula(FRM_Estudiantes mantenimientoEstudiantes,FRM_Cursos mantenimientoCursos,FRM_Matricula frm_Matricula)
    {
        
        this.metodosCursos=mantenimientoCursos.controlador_FRM_Cursos.metodos;
        this.metodosEstudiantes=mantenimientoEstudiantes.controlador_FRM_Estudiantes.metodos;
        this.frm_Matricula=frm_Matricula;
        metodosMatricula=new MetodosMatricula();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("ConsultaRapidaEstudiante"))
        {
            if(metodosEstudiantes.consultarEstudiante(frm_Matricula.devolverCedula()))
            {
                String arreglo[]=metodosEstudiantes.getArregloInformacion();
                frm_Matricula.mostrarNombreEstudiante(arreglo[0]);
                encontroEstudiante=true;        
            }
            else
            {
                frm_Matricula.mostrarMensaje("El estudiante consultado no se encuentra, favor dirigirse al módulo de Mantenimiento Estudiantes");
            }
        }
        if(e.getActionCommand().equals("ConsultaRapidaCurso"))
        {
           if(metodosCursos.consultarCurso(frm_Matricula.devolverSigla()))
            {
                String arreglo[]=metodosCursos.getArregloInformacion();
                frm_Matricula.mostrarNombreCurso(arreglo[0]);
                encontroCurso=true;
            }
            else
            {
                frm_Matricula.mostrarMensaje("El curso consultado no se encuentra, favor dirigirse al módulo de Mantenimiento Cursos");
            } 
        }
        if(e.getActionCommand().equals("Agregar"))
        {  
            frm_Matricula.cargarTabla();
            encontroCurso=false;
            frm_Matricula.estadoInicial();
            frm_Matricula.limpiarCurso(); 
        }
        if(e.getActionCommand().equals("Finalizar"))
        {  
            for(int contador=0;contador<frm_Matricula.getCantidadDeCursosMatriculados();contador++)
            {
               metodosMatricula.agregarMatricula(frm_Matricula.getInformacionTabla(contador));
            }
            frm_Matricula.resetearInterfaz();
            
            metodosMatricula.mostrarInformacion();
        }
        if(encontroEstudiante && encontroCurso)
        {
            frm_Matricula.habilitarAgregar();
        }
       
    }
    public String colocarCodigo()
    {
        return metodosMatricula.devolverCodigo();
    }
}
