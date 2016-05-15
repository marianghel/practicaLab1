
package Controlador;

import Modelo.ArchivoUsuario;
import Vista.FRM_Cursos;
import Vista.FRM_Estudiantes;
import Vista.FRM_Login;
import Vista.FRM_Matricula;
import Vista.FRM_MenuPrincipal;
import Vista.FRM_Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador_FRM_MenuPrincipal implements ActionListener{
    
    FRM_Estudiantes mantenimientoEstudiantes;
    FRM_Cursos mantenimientoCursos;
    FRM_Matricula matricula;
    ArchivoUsuario archivoUsuario;
    FRM_Login login;
    FRM_MenuPrincipal ventana;
    FRM_Usuario ventanaUsuario;
    
    public Controlador_FRM_MenuPrincipal(FRM_MenuPrincipal ventana)
    {
        this.ventana=ventana;
        mantenimientoEstudiantes=new FRM_Estudiantes();
        mantenimientoCursos=new FRM_Cursos();
        matricula= new FRM_Matricula(mantenimientoEstudiantes,mantenimientoCursos);
        archivoUsuario=new ArchivoUsuario();
        login=new FRM_Login(this);
        ventanaUsuario=new FRM_Usuario();
        accesoVentana();
    }
    public void cierreUsuario(){
        login.setVisible(true);
    }
      public void confirmandoUsuario(String arreglo[])
    {
        if(archivoUsuario.confirmarUsuario(arreglo))
        {
            accesoCorrecto();
        }
        else
        {
            login.setVisible(false);
            if(accesoIncorrecto()){
             ventanaUsuario.setVisible(true);  
            System.out.println("abriendo modulo usuario");
            
            accesoVentana();
            }else{
               login.setVisible(true);
              
               JOptionPane.showMessageDialog(null,"Intente nuevamente entrar en login");
            }
            
        }
    }
    public void accesoCorrecto()
    {
        login.setVisible(false);
        ventana.setVisible(true);
    }
    public boolean accesoIncorrecto(){
       String respuesta;
       boolean acceso=false;
       respuesta= JOptionPane.showInputDialog("¿Desea hacer un nuevo usuario? Si/No");
       if(respuesta.equalsIgnoreCase("Si")){
           acceso=true;
       }else{
           acceso=false;
           
       }
        return acceso;
    }
    public void accesoVentana(){
        if(archivoUsuario.cargarArchivo()){
            login.setVisible(true);
            System.out.println("login"); 
        }else{
            ventanaUsuario.setVisible(true);
             System.out.println("usuario");
        }
    }
    
   public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Salir"))
        {
            System.exit(0);        
        }
        if(e.getActionCommand().equals("Estudiantes"))
        {
            this.mantenimientoEstudiantes.setVisible(true);
        }
        if(e.getActionCommand().equals("Cursos"))
        {
            this.mantenimientoCursos.setVisible(true);;
        }
        if(e.getActionCommand().equals("Matricula"))
        {
           this.matricula.setVisible(true);
            this.matricula.colocarCodigo();
        }
    
    }
    
}
