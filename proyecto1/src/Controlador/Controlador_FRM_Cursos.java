
package Controlador;

import Modelo.ArchivoCurso;
import Modelo.Curso;
import Modelo.MetodosCursos;
import Vista.FRM_Cursos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_FRM_Cursos implements ActionListener{
    
     FRM_Cursos frm_MantenimientoCursos;
    public MetodosCursos metodos;
    ArchivoCurso archivo;
    public Controlador_FRM_Cursos(FRM_Cursos frm_MantenimientoCursos)
    {
        this.frm_MantenimientoCursos= frm_MantenimientoCursos;
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
        if(e.getActionCommand().equals("Agregar"))
        {
            metodos.agregarCurso(frm_MantenimientoCursos.devolverInformacion());   
            metodos.mostrarInformacion();
            this.frm_MantenimientoCursos.mostrarMensaje("Curso agregado");
            frm_MantenimientoCursos.limpiar();
            this.frm_MantenimientoCursos.estadoInicial();
                    
        }
        if(e.getActionCommand().equals("Buscar"))
        {
           buscar();
        }
        if(e.getActionCommand().equals("Modificar"))
        {
            metodos.modificarCurso(this.frm_MantenimientoCursos.devolverInformacion());
            
        }
        if(e.getActionCommand().equals("Eliminar"))
        {
            
        }
    }
    public void buscar(){
       if(metodos.consultarCurso(frm_MantenimientoCursos.devolverSigla()))
            {
                frm_MantenimientoCursos.mostrarInformacion(metodos.getArregloInformacion());
            }
            else
            {
                System.out.println("No se encontró el curso");
            }
    }
}
