
package Controlador;

import Modelo.ArchivoUsuario;
import Modelo.MetodosUsuario;
import Modelo.Usuario;
import Vista.FRM_Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_FRM_Usuario implements ActionListener{
    FRM_Usuario usuario;
    MetodosUsuario metodos;
    ArchivoUsuario archivo;
   
    
      public Controlador_FRM_Usuario(FRM_Usuario usuario) {
        this.usuario = usuario;
        metodos=new MetodosUsuario();
       archivo=new ArchivoUsuario();
       
       cargarArchivoCuandoAbreVentana();
    }
    public void cargarArchivoCuandoAbreVentana(){
        ArrayList <Usuario> arrayTemporal= archivo.devolverArchivo();
        metodos.setArray(arrayTemporal);
    }
    public void agregarInformacionCuandoCierraVentana(){
        this.archivo.creaArchivo(); 
        ArrayList <Usuario> arrayGuarda;
        arrayGuarda=metodos.getArray();//array lleno de la ventana
          for(int contador=0;contador<arrayGuarda.size();contador++){
            this.archivo.escribeArchivo(arrayGuarda.get(contador));
        }
    }

    
      public void actionPerformed(ActionEvent e){
          
        if(e.getActionCommand().equals("Buscar")){
            
       buscar();
       }
      if(e.getActionCommand().equals("Agregar")){
         metodos.agregarUsuario(usuario.informacion());
         //usuario.agregar();
         usuario.limpiarVentana();
         System.out.println("agrego");
         usuario.estadoInicio();
      }  
      if(e.getActionCommand().equals("Modificar")){
          metodos.modificarUsuario(usuario.informacion());
          usuario.estadoInicio();
          usuario.limpiarVentana();
      }
      if(e.getActionCommand().equals("Eliminar")){
          metodos.eliminarUsuario(usuario.informacion());
          usuario.estadoInicio();
          usuario.limpiarVentana();
      }
    
    }
    public void buscar()
    {
        if(metodos.buscarUsuario(usuario.devolverNombreU())){
            usuario.mostrarInformacion(metodos.getArreglo());
           System.out.println("buscar si");
           usuario.modificarEliminar();
        }else{
        usuario.agregar();
        System.out.println("buscar no");    
        }
    }
} 
      

